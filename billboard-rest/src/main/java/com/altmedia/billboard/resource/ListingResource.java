package com.altmedia.billboard.resource;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.glassfish.jersey.media.multipart.ContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;

import com.altmedia.billboard.entity.Listing;
import com.altmedia.billboard.service.ListingService;

@Path("listing")
public class ListingResource {
    private Log LOGGER = LogFactory.getLog(ListingResource.class);
    private ListingService listingService = new ListingService();

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response create(final FormDataMultiPart multiPart) {
        try {
            List<FormDataBodyPart> imageParts = multiPart.getFields("images");
            FormDataBodyPart listingPart = multiPart.getField("listing");
            Listing listing = listingPart.getEntityAs(Listing.class);
            if (imageParts != null) {
                listing.setId(UUID.randomUUID().toString());
                uploadImagesToS3(imageParts, listing);
                listingService.create(listing);
                return Response.ok().build();
            }

        }
        catch (Throwable e) {
            LOGGER.error("Error creating listing",e);
        }
        return Response.serverError().build();
    }

    @PUT
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response update(final FormDataMultiPart multiPart) {
        try {
            List<FormDataBodyPart> imageParts = multiPart.getFields("images");
            FormDataBodyPart listingPart = multiPart.getField("listing");
            Listing listing = listingPart.getEntityAs(Listing.class);
            if (imageParts != null) {
                listing.getImageUrls().clear();
                uploadImagesToS3(imageParts, listing);

            }
            listingService.update(listing);
            return Response.ok().build();

        }
        catch (Throwable e) {
            LOGGER.error("Error updating listing",e);
        }
        return Response.serverError().build();
    }

    private void uploadImagesToS3(List<FormDataBodyPart> imageParts, Listing listing) throws Exception, IOException {
        if (imageParts.size() > 4) {
            throw new Exception("too many images!");
        }
        int i = 0;
        for (FormDataBodyPart part : imageParts) {
            InputStream is = part.getEntityAs(InputStream.class);
            ContentDisposition meta = part.getContentDisposition();
            URL url = listingService.storeImageInS3(is, meta.getFileName(), listing.getId());;
            listing.getImageUrls().add(i++, url);
        }
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") String listingId) {
        listingService.delete(listingId);
        return Response.ok().build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") String listingId) {
        Listing listing = listingService.retrieve(listingId);
        return Response.ok().entity(listing).build();
    }

    @Path("user/{userId}")
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllByUserId(@PathParam("userId") String userId) {
        List<Listing> listings = listingService.getListingsByUserId(userId);
        return Response.ok().entity(listings).build();
    }

    @Path("list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response list() {
        List<Listing> listings = listingService.getAllListings();
        return Response.ok().entity(listings).build();
    }

    @Path("ping")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response ping() {
        return Response.ok().entity("hello user!").build();
    }
}

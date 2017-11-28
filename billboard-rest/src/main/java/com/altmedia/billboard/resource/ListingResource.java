package com.altmedia.billboard.resource;

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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.ContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataParam;

import com.altmedia.billboard.entity.Listing;
import com.altmedia.billboard.service.ListingService;

@Path("listing")
public class ListingResource {
    private ListingService listingService = new ListingService();

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response create(@FormDataParam("listing") Listing listing,
                           @FormDataParam("images") List<FormDataBodyPart> imageParts) {
        try {
            listing.setId(UUID.randomUUID().toString());
            for (FormDataBodyPart part : imageParts) {
                InputStream is = part.getEntityAs(InputStream.class);
                ContentDisposition meta = part.getContentDisposition();
                URL url = listingService.storeImageInS3(is, meta.getFileName(), listing.getId());;
                listing.getImageUrls().add(url);
            }
            listingService.create(listing);
            return Response.ok().build();
        }
        catch (Throwable e) {
            e.printStackTrace();
        }
        return Response.serverError().build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(Listing listing) {
        listingService.update(listing);
        return Response.ok().build();
    }

    @DELETE
    @Consumes(MediaType.TEXT_PLAIN)
    public Response delete(@PathParam("id") String listingId) {
        listingService.delete(listingId);
        return Response.ok().build();
    }

    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") String listingId) {
        Listing listing = listingService.retrieve(listingId);
        return Response.ok().entity(listing).build();
    }

    @Path("user/{userId}")
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllByUserId(@QueryParam("userId") String userId) {
        List<Listing> listings = listingService.getListingsByUserId(userId);
        return Response.ok().entity(listings).build();
    }

    @Path("ping")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response ping() {
        return Response.ok().entity("hello user!").build();
    }
}

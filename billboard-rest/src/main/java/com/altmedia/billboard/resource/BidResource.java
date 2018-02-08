package com.altmedia.billboard.resource;

import java.util.List;

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

import com.altmedia.billboard.entity.Bid;
import com.altmedia.billboard.entity.Listing;
import com.altmedia.billboard.service.BidService;
import com.altmedia.billboard.service.EmailService;
import com.altmedia.billboard.service.ListingService;

@Path("bid")
public class BidResource {
    private Log LOGGER = LogFactory.getLog(ListingResource.class);
    private BidService service = BidService.getInstance();
    private EmailService emailService = EmailService.getInstance();
    private ListingService listingService = ListingService.getInstance();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Bid bid) {
        try {
            service.create(bid);
            Listing listing = listingService.retrieve(bid.getListingId());
            emailService.sendBidCreatedEmail(bid, listing.getCreatedById());
        }
        catch (Throwable t) {
            LOGGER.error("Error creating Bid", t);
            return Response.serverError().build();
        }
        return Response.ok().build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(Bid bid) {
        try {
            service.update(bid);
            Listing listing = listingService.retrieve(bid.getListingId());
            emailService.sendBidUpdatedEmail(bid, listing.getCreatedById());
        }
        catch (Throwable t) {
            LOGGER.error("Error updating Bid", t);
            return Response.serverError().build();
        }
        return Response.ok().build();
    }

    @DELETE
    @Consumes(MediaType.TEXT_PLAIN)
    public Response delete(@PathParam("id") String bidId) {
        try {
            Bid bid = service.retrieve(bidId);
            Listing listing = listingService.retrieve(bid.getListingId());
            service.delete(bidId);
            emailService.sendBidDeletedEmail(bid, listing.getCreatedById());
        }
        catch (Throwable t) {
            LOGGER.error("Error deleting Bid", t);
            return Response.serverError().build();
        }
        return Response.ok().build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") String bidId) {
        Bid bid = null;
        try {
            bid = service.retrieve(bidId);
        }
        catch (Throwable t) {
            LOGGER.error("Error getting Bid", t);
            return Response.serverError().build();
        }
        return Response.ok().entity(bid).build();
    }

    @Path("listing/{listingId}")
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBidsForListing(@PathParam("listingId") String listingId) {
        List<Bid> bids = null;
        try {
            bids = service.getAllBidsForListing(listingId);
        }
        catch (Throwable t) {
            LOGGER.error("Error getting bids for listing", t);
            return Response.serverError().build();
        }
        return Response.ok().entity(bids).build();
    }

    @Path("user/{userId}")
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllByUserId(@PathParam("userId") String userId) {
        List<Bid> listings = null;
        try {
            listings = service.getAllBidsForUserId(userId);
        }
        catch (Throwable t) {
            LOGGER.error("Error getAllByUserId", t);
            return Response.serverError().build();
        }
        return Response.ok().entity(listings).build();
    }

}

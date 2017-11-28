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

import com.altmedia.billboard.entity.Bid;
import com.altmedia.billboard.service.BidService;

@Path("bid")
public class BidResource {
    private BidService service = new BidService();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Bid bid) {
        service.create(bid);
        return Response.ok().build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(Bid bid) {
        service.update(bid);
        return Response.ok().build();
    }

    @DELETE
    @Consumes(MediaType.TEXT_PLAIN)
    public Response delete(@PathParam("id") String bidId) {
        service.delete(bidId);
        return Response.ok().build();
    }

    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") String bidId) {
        Bid bid = service.retrieve(bidId);
        return Response.ok().entity(bid).build();
    }

    @Path("listing/{listingId}")
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBidsForListing(@PathParam("listingId") String listingId) {
        List<Bid> bids = service.getAllBidsForListing(listingId);
        return Response.ok().entity(bids).build();
    }

}

package com.altmedia.billboard.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("listing")
public class BidResource {

	@Path("create")
	@POST
	@Consumes("application/json")
	public Response create() {
		return null;
	}

	@Path("update")
	@POST
	@Consumes("application/json")
	public Response update() {
		return null;
	}

	@Path("delete")
	@POST
	@Consumes("application/json")
	public Response delete() {
		return null;
	}

	@GET
	@Produces("application/json")
	public Response get(@PathParam("id") String id) {
		return null;
	}

	@GET
	@Produces("application/json")
	public Response getAllByHoardingId(@PathParam("hoardingId") String hoardingId) {
		return null;
	}

	@GET
	@Produces("application/json")
	public Response getAllListings() {
		return null;
	}
}

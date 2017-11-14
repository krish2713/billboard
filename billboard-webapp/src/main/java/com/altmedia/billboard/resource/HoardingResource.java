package com.altmedia.billboard.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.altmedia.billboard.entity.Hoarding;
import com.altmedia.billboard.service.HoardingService;

@Path("hoarding")
public class HoardingResource {
	private HoardingService service;

	@Path("create")
	@POST
	@Consumes("application/json")
	public Response create(Hoarding hoarding) {
		service.create(hoarding);
		return Response.ok().build();
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
	public Response getAllByVendor(@PathParam("vendorId") String vendorId) {
		return null;
	}

	@GET
	@Produces("application/json")
	public Response getAll() {
		return null;
	}

}

package com.app.controller;

import java.util.List;
import java.util.NoSuchElementException;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.Partner;
import com.app.service.PartnerService;

@RestController
@Path("/partner/")
public class PartnerController {

	@Autowired
	PartnerService partnerService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAll() {
		int statusCode = 200;
		Response response = null;
		List<Partner> partner = partnerService.findAll();
		response = Response.status(statusCode).entity(partner).build();
		return response;
	}

	@GET
	@Path("/{partnerTerm}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findOne(@PathParam("partnerTerm") final String partnerTerm) {
		try {
			try {
				Long id = Long.parseLong(partnerTerm);
				Partner partner = partnerService.findById(id);
				return Response.status(200).entity(partner).build();
			} catch (NoSuchElementException e) {
				return Response.status(404).build();
			}
		} catch (NumberFormatException e) {
			List<Partner> partner = partnerService.findByNombre(partnerTerm);
			if (partner == null || partner.isEmpty())
				return Response.status(404).build();
			return Response.status(200).entity(partner).build();
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/create")
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(Partner resource) {
		resource.setId(null);
		Partner savedResource = partnerService.save(resource);
		return Response.status(201).entity(savedResource).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/update")
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(Partner resource) {
		Partner savedResource = partnerService.save(resource);
		return Response.status(201).entity(savedResource).build();
	}

	@DELETE
	@Path("/{partnerId}")
	public Response delete(@PathParam("partnerId") Long partnerId) {
		try {
			partnerService.deleteById(partnerId);
			return Response.status(201).build();
		} catch (EmptyResultDataAccessException e) {
			return Response.status(404).build();
		}
	}
}

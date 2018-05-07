package com.app.controller;

import java.util.ArrayList;
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

import com.app.model.Family;
import com.app.service.FamilyService;

@RestController
@Path("/family/")
public class FamilyController {

	@Autowired
	public FamilyService familyService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAll() {
		int statusCode = 200;
		Response response = null;
		List<Family> family = familyService.findAll();
		List<Family> fam = new ArrayList<>();
		for (Family f : family) {
			Family f1 = new Family(f.getNombre(), f.getApellidoPaterno(), f.getApellidoMaterno(), f.getTelefono(),
					f.getDomicilio());
			f1.setId(f.getId());
			fam.add(f1);
		}
		response = Response.status(statusCode).entity(fam).build();
		return response;
	}

	@GET
	@Path("/{familyTerm}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findOne(@PathParam("familyTerm") final String familyTerm) {
		try {
			try {
				Long id = Long.parseLong(familyTerm);
				Family family = familyService.findById(id);
				Family f = new Family(family.getNombre(), family.getApellidoPaterno(), family.getApellidoMaterno(),
						family.getTelefono(), family.getDomicilio());
				f.setId(family.getId());
				return Response.status(200).entity(f).build();
			} catch (NoSuchElementException e) {
				return Response.status(404).build();
			}
		} catch (NumberFormatException e) {
			List<Family> family = familyService.findByNombre(familyTerm);
			if (family == null || family.isEmpty())
				return Response.status(404).build();
			return Response.status(200).entity(family).build();
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/create")
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(Family resource) {
		resource.setId(null);
		Family savedResource = familyService.save(resource);
		return Response.status(201).entity(savedResource).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/update")
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(Family resource) {
		Family savedResource = familyService.save(resource);
		return Response.status(201).entity(savedResource).build();
	}

	@DELETE
	@Path("/{familyId}")
	public Response delete(@PathParam("familyId") Long familyId) {
		try {
			familyService.deleteById(familyId);
			return Response.status(201).build();
		} catch (EmptyResultDataAccessException e) {
			return Response.status(404).build();
		}
	}
}

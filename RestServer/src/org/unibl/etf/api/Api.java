package org.unibl.etf.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.unibl.etf.model.User;
import org.unibl.etf.service.ApiService;

@Path("/users")
public class Api {
	
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(User user) {
		User u = ApiService.login(user.getUsername(), user.getPassword());
		if(u != null) {
			return Response.status(200).entity(u).build();
		}
		return Response.status(500).build();
	}
	
	@POST
	@Path("/logout")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response logout(User user) {
		if(ApiService.logout(user)) {
			return Response.status(200).build();
		}
		return Response.status(500).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllUsers() {
		return Response.status(200).entity(ApiService.getAllUsers()).build();
	}
	
	@GET
	@Path("/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserData(@PathParam("username") String username) {
		return Response.status(200).entity(ApiService.getUserData(username)).build();
	}
	
	@PUT
	@Path("/{username}")
	@Consumes(MediaType.TEXT_PLAIN)
	public Response changePassword(@PathParam("username") String username, String newPassword) {
		if(ApiService.changePassword(username, newPassword)) {
			return Response.status(200).build();
		}
		return Response.status(500).build();
	}

}

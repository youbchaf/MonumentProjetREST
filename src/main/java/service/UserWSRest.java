package service;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import ma.monument.metiers.UserLocal;
import ma.monuments.entities.User;

@Path("/")
@Stateless
public class UserWSRest {

	@EJB
	private UserLocal service;

	@GET
	@Path("/users")
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> listUsers() {
		return service.findAll();
	}

	@GET
	@Path("/users/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public User getUser(@PathParam(value = "id") int id) {
		return service.findById(id);
	}

	@POST
	@Path("/users/delete")
	@Produces(MediaType.APPLICATION_JSON)
	public void delete(@FormParam(value = "id") int id) {
		service.delete(id);
	}

	@POST
	@Path("/users/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void ajouter(User v) {
		System.out.println(v);
		service.add(v);

	}
	
	@POST
	@Path("/users/update/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void modifier(@PathParam(value = "id") int id , @FormParam(value = "nom") String nom, @FormParam(value = "role") String role) {
		User c = service.findById(id);
		c.setNom(nom);
		c.setRole(role);
		service.update(c);

	}

	
}

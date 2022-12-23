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
import ma.monument.metiers.TypeLocal;
import ma.monuments.entities.Type;

@Path("/")
@Stateless
public class TypeWSRest {

	@EJB
	private TypeLocal service;

	@GET
	@Path("/type")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Type> listTypes() {
		return service.findAll();
	}

	@GET
	@Path("/type/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Type getType(@PathParam(value = "id") int id) {
		return service.findById(id);
	}

	@POST
	@Path("/type/delete")
	@Produces(MediaType.APPLICATION_JSON)
	public void delete(@FormParam(value = "id") int id) {
		service.delete(id);
	}

	@POST
	@Path("/type/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void ajouter(Type v) {
		System.out.println(v);
		service.add(v);

	}
	
	@POST
	@Path("/type/update/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void modifier(@PathParam(value = "id") int id , @FormParam(value = "nom") String nom) {
		Type c = service.findById(id);
		c.setLibelle(nom);
		service.update(c);

	}

	
}

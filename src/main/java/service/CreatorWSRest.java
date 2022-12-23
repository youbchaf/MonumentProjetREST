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
import ma.monument.metiers.CreatorLocal;
import ma.monuments.entities.Creator;

@Path("/")
@Stateless
public class CreatorWSRest {

	@EJB
	private CreatorLocal service;

	@GET
	@Path("/creator")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Creator> listCreators() {
		return service.findAll();
	}

	@GET
	@Path("/creator/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Creator getType(@PathParam(value = "id") int id) {
		return service.findById(id);
	}

	@POST
	@Path("/creator/delete")
	@Produces(MediaType.APPLICATION_JSON)
	public void delete(@FormParam(value = "id") int id) {
		service.delete(id);
	}

	@POST
	@Path("/creator/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void ajouter(Creator v) {
		System.out.println(v);
		service.add(v);

	}
	
	@POST
	@Path("/creator/update/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void modifier(@PathParam(value = "id") int id , Creator c) {
		Creator c1 = service.findById(id);
		c1.setDateDebut(c.getDateDebut());
		c1.setDateFin(c.getDateFin());
		c1.setDescription(c.getDescription());
		c1.setNom(c.getNom());
		service.update(c1);

	}

	
}

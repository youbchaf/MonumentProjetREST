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
import ma.monument.metiers.VilleLocal;
import ma.monuments.entities.Ville;

@Path("/")
@Stateless
public class VilleWSRest {

	@EJB
	private VilleLocal service;

	@GET
	@Path("/villes")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Ville> listVilles() {
		return service.findAll();
	}

	@GET
	@Path("/villes/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Ville getVille(@PathParam(value = "id") int id) {
		return service.findById(id);
	}

	@POST
	@Path("/villes/delete")
	@Produces(MediaType.APPLICATION_JSON)
	public void delete(@FormParam(value = "id") int id) {
		service.delete(id);
	}

	@POST
	@Path("/villes/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void ajouter(Ville v) {
		System.out.println(v);
		service.add(v);

	}
	
	@POST
	@Path("/villes/update/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void modifier(@PathParam(value = "id") int id , @FormParam(value = "nom") String nom) {
		Ville c = service.findById(id);
		c.setNom(nom);
		service.update(c);

	}

	
}

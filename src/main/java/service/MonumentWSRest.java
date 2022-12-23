package service;

import java.util.Date;
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

import dto.SearchMonument;
import ma.monument.metiers.MonumentLocal;
import ma.monument.metiers.PhotoLocal;
import ma.monuments.entities.Monument;
import ma.monuments.entities.Photo;

@Path("/")
@Stateless
public class MonumentWSRest {

	@EJB
	private MonumentLocal service;
	@EJB
	private PhotoLocal service1;
	@GET
	@Path("/Monument")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Monument> listMonuments() {
		return service.findAll();
	}

	@GET
	@Path("/Monument/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Monument getType(@PathParam(value = "id") int id) {
		return service.findById(id);
	}

	@POST
	@Path("/Monument/delete")
	@Produces(MediaType.APPLICATION_JSON)
	public void delete(@FormParam(value = "id") int id) {
		service.delete(id);
	}

	@POST
	@Path("/Monument/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void ajouter(Monument v) {
		System.out.println(v);
		service.add(v);

	}
	
	@POST
	@Path("/Monument/addDateToMonument/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void addDateToMonument(@PathParam(value = "id") int id, @FormParam(value = "dateCreation") Date dateCreation) {
		Monument m = service.findById(id);
		m.setDateCreation(dateCreation);
		System.out.println(dateCreation);
		System.out.println(m);
		//service.update(m);

	}
	
	@POST
	@Path("/Monument/addPhotoToMonument")
	@Produces(MediaType.APPLICATION_JSON)
	public void addPhotoToMonument(@FormParam(value = "id") int id, @FormParam(value = "idPhoto") int idPhoto) {
		Monument m = service.findById(id);
		Photo photo = service1.findById(idPhoto);		
		service.add(m);
		System.out.println(m);


	}
	
	
	@POST
	@Path("/Monument/search")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Monument> rechercher(SearchMonument v) {
		return service.findAllByCreatorOrTypeOrVilleOrZoneOrdateCreationOrNom(v.getIdCreator(), v.getIdType(), v.getIdVille(), v.getIdZone(), v.getDateCreation(), v.getNom());
	}
	
	@POST
	@Path("/Monument/update/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void modifier(@PathParam(value = "id") int id , Monument c) {
		Monument c1 = service.findById(id);
		c1.setCreator(c.getCreator());
		c1.setDateCreation(c.getDateCreation());
		c1.setDescription(c.getDescription());
		c1.setHeure_close(c.getHeure_close());
		c1.setHeure_open(c.getHeure_open());
		c1.setLatitude(c.getLatitude());
		c1.setLongitude(c.getLatitude());
		c1.setNom(c.getNom());
		c1.setRank(c.getRank());
		c1.setType(c.getType());
		c1.setWeek(c.isWeek());
		c1.setZone(c.getZone());
		service.update(c1);

	}

	
}

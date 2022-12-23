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
import ma.monument.metiers.PhotoLocal;
import ma.monuments.entities.Photo;

@Path("/")
@Stateless
public class PhotoWSRest {

	@EJB
	private PhotoLocal service;

	@GET
	@Path("/photos")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Photo> listPhotos() {
		return service.findAll();
	}

	@GET
	@Path("/photo/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Photo getPhoto(@PathParam(value = "id") int id) {
		return service.findById(id);
	}

	@POST
	@Path("/photo/delete")
	@Produces(MediaType.APPLICATION_JSON)
	public void delete(@FormParam(value = "id") int id) {
		service.delete(id);
	}

	@POST
	@Path("/photo/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void ajouter(Photo v) {
		System.out.println(v);
		service.add(v);

	}
	
	@POST
	@Path("/photo/update/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void modifier(@PathParam(value = "id") int id , @FormParam(value = "url") String url) {
		Photo c = service.findById(id);
		c.setUrl(url);
		service.update(c);

	}

	
}

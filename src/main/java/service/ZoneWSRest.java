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
import dto.ZoneVille;
import ma.monument.metiers.ZoneLocal;
import ma.monuments.entities.Zone;

@Path("/")
@Stateless
public class ZoneWSRest {

	@EJB
	private ZoneLocal service;

	@GET
	@Path("/zones")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Zone> listZones() {
		return service.findAll();
	}

	@GET
	@Path("/zone/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Zone getZone(@PathParam(value = "id") int id) {
		return service.findById(id);
	}

	@POST
	@Path("/zones/delete")
	@Produces(MediaType.APPLICATION_JSON)
	public void delete(@FormParam(value = "id") int id) {
		service.delete(id);
	}

	@POST
	@Path("/zone/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void ajouter(Zone v) {
		System.out.println(v);
		service.add(v);

	}
	
	@POST
	@Path("/zone/ville/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void addZoneToVille(ZoneVille o) {
		service.addZoneToVille(o);

	}
	
	@POST
	@Path("/zone/update/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void modifier(@PathParam(value = "id") int id , Zone z) {
		Zone c = service.findById(id);
		c.setNom(z.getNom());
		c.setVille(z.getVille());
		service.update(c);

	}

	
}

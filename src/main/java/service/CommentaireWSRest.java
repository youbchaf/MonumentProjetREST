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

import dto.AddComment;
import ma.monument.metiers.CommentaireLocal;
import ma.monuments.entities.Commentaire;
import ma.monuments.entities.MonumentUserPK;

@Path("/")
@Stateless
public class CommentaireWSRest {

	@EJB
	private CommentaireLocal service;

	@GET
	@Path("/Commentaire")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Commentaire> listCommentaires() {
		return service.findAll();
	}

	@GET
	@Path("/Commentaire/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Commentaire getType(@PathParam(value = "id") int id) {
		return service.findById(id);
	}

	@POST
	@Path("/Commentaire/delete")
	@Produces(MediaType.APPLICATION_JSON)
	public void delete(@FormParam(value = "id") int id) {
		service.delete(id);
	}

	@POST
	@Path("/Commentaire/add")
	@Produces(MediaType.APPLICATION_JSON)
	public void ajouter(@FormParam(value = "user") int user, @FormParam(value = "monument") int monument, @FormParam(value = "date") Date date, @FormParam(value = "vote") boolean vote, @FormParam(value = "contenu") String contenu) {
		MonumentUserPK monumentUserPK = new MonumentUserPK();
		monumentUserPK.setUser(user);
		monumentUserPK.setMonument(monument);
		monumentUserPK.setDate(date);
		Commentaire commentaire = new Commentaire();
		commentaire.setPk(monumentUserPK);
		commentaire.setContenu(contenu);
		commentaire.setVote(vote);
		service.add(commentaire);

	}
	
	@POST
	@Path("/Commentaire/update/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void modifier(@PathParam(value = "id") int id , Commentaire c) {
		Commentaire c1 = service.findById(id);
		c1.setContenu(c.getContenu());
		c1.setVote(c.getVote());
		service.update(c1);

	}

	
}

package rest;

import dao.VeterinarianDao;
import domain.persons.Veterinarian;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/veterinarians")
@Produces({"application/json", "application/xml"})
public class VeterinarianRessource {

    VeterinarianDao veterinarianDao = new VeterinarianDao();

    @GET
    public List<Veterinarian> getAllVeterinarians() {
        return veterinarianDao.findAll();
    }

    @GET
    @Path("/{veterinarianId}")
    public Veterinarian getVeterinarianById(@PathParam("veterinarianId") Long veterinarianId) {
        return (Veterinarian) veterinarianDao.findOne(veterinarianId);
    }

    @POST
    @Path("/add")
    @Consumes("application/json")
    public Response addVeterinarian(Veterinarian veterinarian) {
        try {
            veterinarianDao.save(veterinarian);
        } catch (Exception e) {
            return Response.status(400).entity("ERROR").build();
        }
        return Response.ok().entity("SUCCESS").build();
    }

}

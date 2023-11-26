package rest;

import dao.OwnerDao;
import domain.persons.Owner;
import dto.EntityMappers;
import dto.persons.OwnerDto;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.mapstruct.factory.Mappers;

@Path("/owners")
@Produces({"application/json", "application/xml"})
public class OwnerRessource {

    OwnerDao ownerDao = new OwnerDao();

    EntityMappers mapper = Mappers.getMapper(EntityMappers.class);

    @GET
    @Path("/{ownerId}")
    public OwnerDto getOwnerById(@PathParam("ownerId") Long ownerId) {
        return mapper.ownerToOwnerDto((Owner) ownerDao.findOne(ownerId));
    }

    @POST
    @Path("/add")
    @Consumes("application/json")
    public Response addOwner(Owner owner) {
        try {
            ownerDao.save(owner);
        } catch (Exception e) {
            return Response.status(400).entity("ERROR").build();
        }
        return Response.ok().entity("SUCCESS").build();
    }
}

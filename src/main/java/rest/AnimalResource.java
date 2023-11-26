package rest;

import dao.AnimalDao;
import domain.animals.Animal;
import dto.EntityMappers;
import dto.animals.AnimalDto;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Path("/animals")
@Produces({"application/json", "application/xml"})
public class AnimalRessource {

    AnimalDao animalDao = new AnimalDao();

    EntityMappers mapper = Mappers.getMapper(EntityMappers.class);

    @GET
    public List<AnimalDto> getAllAnimals() {
        List<Animal> animals = animalDao.findAll();
        List<AnimalDto> result = new ArrayList<>();
        for (Animal animal : animals) {
            result.add(mapper.animalToAnimalDto(animal));
        }
        return animalDao.findAll();
    }

    @GET
    @Path("/{animalId}")
    public AnimalDto getAnimalById(@PathParam("animalId") Long animalId) {
        return mapper.animalToAnimalDto((Animal)animalDao.findOne(animalId));
    }

    @POST
    @Path("/add")
    @Consumes("application/json")
    public Response addAnimal(@Parameter(description = "", required = true) Animal animal) {
        try {
            animalDao.save(animal);
        } catch (Exception e) {
            return Response.status(400).entity("ERROR").build();
        }
        return Response.ok().entity("SUCCESS").build();
    }

    @PUT
    @Path("/update/{animalId}")
    @Consumes("application/json")
    public Response updateAnimal(@Parameter(description = "", required = true) Animal animal, @PathParam("animalId") long id) {
        try {
            Animal animalToUpdate = (Animal) animalDao.findOne(id);
            animalToUpdate.setName(animal.getName());
            animalToUpdate.setAge(animal.getAge());
            animalToUpdate.setAnimalType(animal.getAnimalType());
            animalDao.update(animalToUpdate);
        } catch (Exception e) {
            return Response.status(400).entity("ERROR").build();
        }
        return Response.ok().entity("SUCCESS").build();
    }
}

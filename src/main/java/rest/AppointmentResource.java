package rest;

import dao.AppointmentDao;
import domain.Appointment;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/appointments")
@Produces({"application/json", "application/xml"})
public class AppointmentRessource {

    AppointmentDao appointmentDao = new AppointmentDao();

    @GET
    public List<Appointment> getAllAppointments() {
        return appointmentDao.findAll();
    }

    @GET
    @Path("/{appointmentId}")
    public Appointment getAppointmentById(@PathParam("appointmentId") Long appointmentId) {
        return (Appointment) appointmentDao.findOne(appointmentId);
    }

    @POST
    @Path("/add")
    @Consumes("application/json")
    public Response addAppointment(Appointment appointment) {
        try {
            appointmentDao.save(appointment);
        } catch (Exception e) {
            return Response.status(400).entity("ERROR").build();
        }
        return Response.ok().entity("SUCCESS").build();
    }
}

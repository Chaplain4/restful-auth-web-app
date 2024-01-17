package main.rest.service;

import main.jpa.dao.StudentDAO;
import main.jpa.dao.model.Student;
import main.rest.beans.Response;

import javax.annotation.security.PermitAll;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Date;
import java.util.List;

@Path("/students")
public class StudentServiceImpl implements StudentService {

    private StudentDAO sd = new StudentDAO();

    @PermitAll
    @POST
    @Path("/create")
    @Override
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(Student student) {
        try {
            student.setId(0);
            sd.create(student);
        } catch (Throwable t) {
            return new Response(1, false, t.getMessage());
        }
        return new Response(1, true, "Student created");
    }

    @PermitAll
    @DELETE
    @Path("/delete/{id}")
    @Override
    public Response deleteById(@PathParam("id") int id) {
        Student s = findById(id);
        if (s == null) {
            return new Response(1, false, "No such student!");
        } else sd.deleteById(id);
        return new Response(1, true, "Student deleted");
    }

    @PermitAll
    @GET
    @Override
    @Path("/find/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Student findById(@PathParam("id") int id) {
        return sd.findById(id);
    }

    @Override
    @PermitAll
    @GET
    @Path("/find_all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Student> getAllStudents() {
        System.out.println("get all students " + new Date());
        return sd.findAll();
    }

    @Override
    @PermitAll
    @POST
    @Path("/save_update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response saveOrUpdate(Student student) {
        Student s = findById(student.getId());
        if (s == null) {
            return new Response(1, false, "No such student!");
        } else
            sd.saveOrUpdate(student);
        return new Response(1, true, "Student updated");
    }

    @PermitAll
    @POST
    @Override
    @Path("/findAll_condition")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    public List<Student> findAllByCondition(String condition) {
        return sd.findAllByCondition(condition);
    }

    @PermitAll
    @POST
    @Override
    @Path("/find_condition")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    public Student findByCondition(String condition) {
        return sd.findByCondition(condition);
    }
}

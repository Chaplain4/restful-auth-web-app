package main.rest.service;

import main.jpa.dao.model.Student;
import main.rest.beans.Response;

import java.util.List;

public interface StudentService {
    public Response create(Student student);
    public Response deleteById(int id);
    public Student findById(int id);
    public List<Student> getAllStudents();
    public Response saveOrUpdate(Student student);
    public List<Student> findAllByCondition(String condition);
    public Student findByCondition(String condition);
}

package main.rest.service;


import main.rest.beans.Person;
import main.rest.beans.Response;

import java.util.List;

public interface PersonService {
    public Response createPerson(Person person);
    public Response deletePerson(int id);
    public Person getPerson(int id);
    public List<Person> getAllPersons();
    public Response updatePerson(Person person);
    public List<Person> getAllPersonsByAge(int from, int to);
}

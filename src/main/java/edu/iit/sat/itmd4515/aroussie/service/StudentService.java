/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.aroussie.service;

import edu.iit.sat.itmd4515.aroussie.domain.City;
import edu.iit.sat.itmd4515.aroussie.domain.Student;
import edu.iit.sat.itmd4515.aroussie.domain.security.User;
import edu.iit.sat.itmd4515.aroussie.domain.security.UserGroup;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author Roussiere
 */
@Stateless
public class StudentService extends AbstractService<Student> {

    private static final Logger LOG = Logger.getLogger(StudentService.class.getName());

    @EJB
    private UserService userService;

    @EJB
    private UserGroupService userGroupService;

    @EJB
    private CityService cityService;

    public StudentService() {
        super(Student.class);
    }

    /**
     * Find a Student by his firstName
     *
     * @param firstName a string
     * @return a list of Student
     */
    public List<Student> findByFirstName(String firstName) {
        TypedQuery<Student> query = em.createNamedQuery("Student.FindByFirstName", Student.class);
        query.setParameter("firstName", firstName);
        return query.getResultList();
    }

    /**
     * Find a Student by his lastName
     *
     * @param name a string
     * @return a list of Student
     */
    public List<Student> findByName(String name) {
        TypedQuery<Student> query = em.createNamedQuery("Student.FindByName", Student.class);
        query.setParameter("name", name);
        return query.getResultList();
    }

    /**
     * Find a Student thanks to his userName
     *
     * @param userName a string
     * @return a Student
     */
    public Student findByUserName(String userName) {
        TypedQuery<Student> query = em.createNamedQuery("Student.FindByUserName", Student.class);
        query.setParameter("userName", userName);
        return query.getSingleResult();
    }

    /**
     * Return a list of all the students
     *
     * @return a list
     */
    public List<Student> findAll() {

        TypedQuery<Student> query = em.createNamedQuery("Student.FindAll", Student.class);
        return query.getResultList();
    }

    /**
     * Determine if an Email adress is already associated to a Student
     *
     * @param mail String
     * @return A boolean
     */
    public boolean MailAdressExisted(String mail) {
        boolean exist = TRUE;

        TypedQuery<Student> query = em.createNamedQuery("Student.FindByEmail", Student.class);
        query.setParameter("mail", mail);

        List<Student> s = query.getResultList();
        try {

            if (s.isEmpty()) {
                exist = FALSE;
            }

        } catch (NoResultException e) {
            exist = FALSE;
        }

        return exist;
    }

    /**
     * Create a student
     *
     * @param student the student we want to create
     * @param user the user linked to the student
     * @param cityName the name of the city where the student is
     */
    public void createStudent(Student student, User user, String cityName) {

        UserGroup group = userGroupService.findGroupByName("students");
        user.addUserToGroup(group);
        student.setUser(user);
        student.addCity(cityService.findCityByName(cityName));
        this.create(student);

    }

    /**
     * Delete a student from the Database
     *
     * @param student
     */
    public void deleteStudent(Student student) {

        student = em.getReference(Student.class, student.getId());

        student.deleteCity(student.getCity());

        em.remove(student);
    }

    public void updateStudent(Student newStudent) {

        LOG.info("Dans studentService, loginNewStudent= " + newStudent.getUser().getLogin());
        Student currentStudent = em.getReference(Student.class, newStudent.getUser().getLogin());

        currentStudent.setCompany(newStudent.getCompany());
        currentStudent.setCountryOfWork(newStudent.getCountryOfWork());
        currentStudent.setFirstName(newStudent.getFirstName());
        currentStudent.setGraduate(newStudent.getGraduate());
        currentStudent.setJob(newStudent.getJob());
        currentStudent.setMail(newStudent.getMail());
        currentStudent.setName(newStudent.getName());

        //If the city changed
        if (!currentStudent.getCity().equals(newStudent.getCity())) {
            //Delete the old city
            currentStudent.deleteCity(currentStudent.getCity());
            //Put the new one
            currentStudent.setCity(newStudent.getCity());
        }

        em.merge(currentStudent);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.aroussie.bean;

import edu.iit.sat.itmd4515.aroussie.domain.City;
import edu.iit.sat.itmd4515.aroussie.domain.Student;
import edu.iit.sat.itmd4515.aroussie.domain.security.User;
import edu.iit.sat.itmd4515.aroussie.domain.security.UserGroup;
import edu.iit.sat.itmd4515.aroussie.service.CityService;
import edu.iit.sat.itmd4515.aroussie.service.StudentService;
import edu.iit.sat.itmd4515.aroussie.service.UserGroupService;
import edu.iit.sat.itmd4515.aroussie.service.UserService;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Roussiere
 */
@ManagedBean
@RequestScoped
public class CreateStudentBean {

    private static final Logger LOG = Logger.getLogger(CreateStudentBean.class.getName());

    @EJB
    private StudentService studentService;

    @EJB
    private UserService userService;

    @EJB
    private UserGroupService userGroupService;

    @EJB
    private CityService cityService;

    private Student student;
    private User user;
    private UserGroup group;
    private City city;
    private FacesContext facesContext;

    /**
     * Default Constructor
     */
    public CreateStudentBean() {
        student = new Student();
        user = new User();
        group = new UserGroup();
        city = new City();
    }

    public void CreateStudent() {

        LOG.info("Attempt to create a student");
        this.AssociateRole();
        this.AddCity(this.city.getName());
        studentService.create(this.student);
        LOG.info("Student created");
        FacesMessage message = new FacesMessage("Creation of a student succeeded !");
        FacesContext.getCurrentInstance().addMessage(null, message);

    }

    /**
     * Return the user of the request
     *
     * @return a String
     */
    public String getRemoteUser() {
        return facesContext.getExternalContext().getRemoteUser();
    }

    /**
     * Associate the role of STUDENT
     */
    private void AssociateRole() {
        this.group = userGroupService.findGroupByName("students");
        this.user.addUserToGroup(group);
        this.student.setUser(user);
    }

    /**
     * Associate the Student with the city he studies in
     */
    private void AddCity(String name) {
        student.addCity(cityService.findCityByName(name));

    }

    /**
     * Return the Student Entity
     *
     * @return Student
     */
    public Student getStudent() {
        return student;
    }

    /**
     * Return the User entity
     *
     * @return User
     */
    public User getUser() {
        return user;
    }

    /**
     * Return the UserGroup entity
     *
     * @return UserGroup
     */
    public UserGroup getGroup() {
        return group;
    }

    public City getCity() {
        return city;
    }

}

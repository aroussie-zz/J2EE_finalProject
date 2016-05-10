/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.aroussie.bean;

import edu.iit.sat.itmd4515.aroussie.domain.City;
import edu.iit.sat.itmd4515.aroussie.domain.Visitor;
import edu.iit.sat.itmd4515.aroussie.domain.security.User;
import edu.iit.sat.itmd4515.aroussie.domain.security.UserGroup;
import edu.iit.sat.itmd4515.aroussie.service.CityService;
import edu.iit.sat.itmd4515.aroussie.service.UserGroupService;
import edu.iit.sat.itmd4515.aroussie.service.UserService;
import edu.iit.sat.itmd4515.aroussie.service.VisitorService;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean; // Be cautious and take the good ONE !!!!!!
import javax.faces.bean.RequestScoped; // Be cautious and take the good ONE !!!!!!
import javax.faces.context.FacesContext;

/**
 *
 * @author Roussiere
 */
@ManagedBean
@RequestScoped //This class will be only called if the admin click on the button Create
public class CreateVisitorBean implements Serializable {

    private static final Logger LOG = Logger.getLogger(CreateVisitorBean.class.getName());

    @EJB
    private VisitorService visitorService;

    @EJB
    private CityService cityService;

    @EJB
    private UserService userService;

    @EJB
    private UserGroupService groupService;

    private Visitor visitor;
    private User user;
    private UserGroup group;

    /**
     * Default constructor
     */
    public CreateVisitorBean() {
        visitor = new Visitor();
        user = new User();
        group = new UserGroup();
    }

    /**
     * Create a new Visitor in the Database
     */
    public void Create() {
        LOG.info("Attempt to create a visitor");
        this.AddAllCities();
        this.AssociateRole();
        visitorService.create(visitor);
        LOG.info("Visitor created");
        FacesMessage message = new FacesMessage("Creation of a Visitor succeeded !");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    /**
     * *
     * Add all the cities to the Visitor
     */
    private void AddAllCities() {
        List<City> cities = cityService.findAllCities();
        for (City c : cities) {
            visitor.addCity(c);
        }
    }

    /**
     * Associate the Role of VISITOR to the Visitor Entity
     */
    private void AssociateRole() {
        this.group = groupService.findGroupByName("visitors");
        this.user.addUserToGroup(group);
        this.visitor.setUser(user);

    }

    /**
     * Return the Visitor to access to his information
     *
     * @return Visitor
     */
    public Visitor getVisitor() {
        return visitor;
    }

    /**
     * Return the User of the visitor
     *
     * @return User entity
     */
    public User getUser() {
        return user;
    }

}

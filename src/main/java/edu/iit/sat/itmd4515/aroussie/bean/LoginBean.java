/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.aroussie.bean;

import edu.iit.sat.itmd4515.aroussie.service.StudentService;
import edu.iit.sat.itmd4515.aroussie.service.UserService;
import edu.iit.sat.itmd4515.aroussie.service.VisitorService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Roussiere
 */
@RequestScoped
@ManagedBean(name = "loginBean")
public class LoginBean {

    @NotNull
    private String username;
    @NotNull
    private String password;

    private static final Logger LOG = Logger.getLogger(LoginBean.class.getName());
    private static final String FACES_REDIRECT = "?faces-redirect=true";

    private FacesContext facesContext;

    @EJB
    UserService userService;
    @EJB
    VisitorService visitorService;
    @EJB
    StudentService studentService;

    /**
     * Defaut constructor
     */
    public LoginBean() { //Just initialize the attributes
        username = "";
        password = "";
    }

    @PostConstruct
    public void setUp() {
        facesContext = FacesContext.getCurrentInstance();
    }

    /**
     * Return the user of the request
     *
     * @return a String
     */
    public String getRemoteUser() {
        return facesContext.getExternalContext().getRemoteUser();
    }

    private String getContextPath(String path) {
        return "/app/public/" + path + FACES_REDIRECT;
    }

    /**
     * Return the group of the user
     *
     * @return the name of the group
     */
    public String getUserGroup() {
        return this.userService.findGroup(this.getRemoteUser());
    }

    /**
     * Login process to access to the app
     * return the login form page or the home page
     * @throws ServletException 
     */
     public String login() throws ServletException {
        HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
     
        try {
            request.login(username, password);
        } catch (ServletException e) {
            LOG.log(Level.SEVERE, "Fail to login with " + username, e);
            facesContext.addMessage(null, new FacesMessage("You enter a wrong username or a wrong passsword"));
            return "/login.xhtml";
        }
        LOG.info("The user " + username + " is login");
        return getContextPath("home.xhtml");
    }

    /**
     * Logout the user from the application
     * @return the login page
     * @throws ServletException 
     */
    public String logout() throws ServletException {
        HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        LOG.info("the user logout");
        try {
            request.logout();
        } catch (ServletException e) {
            LOG.log(Level.SEVERE, "Error when trying to logout from " + username, e);
        }
        return "/login.xhtml";
    }

    /**
     * Return true if the user is the admin
     *
     * @return a boolean
     */
    public boolean isAdmin() {

        boolean answer = false;
        HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        if (request.isUserInRole("ADMIN")) {
            answer = true;
        }
        return answer;
    }

    /**
     * Return true if the user is a student
     *
     * @return a boolean
     */
    public boolean isStudent() {
        boolean answer = false;
        HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        if (request.isUserInRole("STUDENT")) {
            answer = true;
        }
        return answer;
    }

    /**
     * Return true if the user is a Visitor
     *
     * @return a boolean
     */
    public boolean isVisitor() {
        boolean answer = false;
        HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        if (request.isUserInRole("VISITOR")) {
            answer = true;
        }
        return answer;
    }

    /**
     * Return the attribute username
     *
     * @return a string
     */
    public String getUsername() {
        return username;
    }

    /**
     * Return the attribute Password
     *
     * @return a string
     */
    public String getPassword() {
        return password;
    }

    /**
     * Define the value of username
     *
     * @param username a String
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Define the value of password
     *
     * @param password a string
     */
    public void setPassword(String password) {
        this.password = password;
    }

}

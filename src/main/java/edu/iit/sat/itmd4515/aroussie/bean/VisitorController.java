/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.aroussie.bean;

import edu.iit.sat.itmd4515.aroussie.domain.Visitor;
import edu.iit.sat.itmd4515.aroussie.service.VisitorService;
import java.util.Map;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Roussiere
 */
@ManagedBean
@RequestScoped
public class VisitorController {

    private static final Logger LOG = Logger.getLogger(VisitorController.class.getName());

    private FacesContext facesContext;

    private Visitor visitor;

    @EJB
    VisitorService visitorService;

    @ManagedProperty(value = "#{loginBean}")
    private LoginBean loginBean;

    public VisitorController() {
    }

    @PostConstruct
    public void setUp() {
        facesContext = FacesContext.getCurrentInstance();
        //If the user is a Visitor so we refresh his information everytime
        if (loginBean.isVisitor()) {
            refresh();
        } //Useful for the admin to see the information of a student
        else if (loginBean.isAdmin()) {
            setVisitorFromAdmin();
        }
    }

    public void refresh() {
        visitor = visitorService.findByUserName(loginBean.getRemoteUser());
    }

    /**
     * Define the attribute visitor in case where the Admin want to see
     * information
     */
    public void setVisitorFromAdmin() {
        Map<String, String> param = facesContext.getExternalContext().getRequestParameterMap();
        String username = param.get("username");
        visitor = visitorService.findByUserName(username);
    }

    /**
     * Redirect the admin to the profile page of a visitor
     *
     * @return the link of the profile page
     */
    public String seeVisitorProfile() {
        if (loginBean.isAdmin()) {
            LOG.info("Admin redirected to the profil page of " + visitor.getUser().getLogin());
        }
        return "/app/public/profile.xhtml";
    }

    /**
     * Redirect the Visitor to its own profile page
     *
     * @return the link to the profile page
     */
    public String seeMyProfile() {
        LOG.info(visitor.getUser().getLogin() + " redirected to his profilPage");
        return "/app/public/profile.xhtml";
    }

    public Visitor getVisitor() {
        return visitor;
    }

    public void setVisitor(Visitor visitor) {
        this.visitor = visitor;
    }

    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }

}

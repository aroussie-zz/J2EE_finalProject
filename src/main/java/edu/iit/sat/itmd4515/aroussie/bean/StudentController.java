/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.aroussie.bean;

import edu.iit.sat.itmd4515.aroussie.domain.Student;
import edu.iit.sat.itmd4515.aroussie.service.StudentService;
import java.util.Map;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
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
public class StudentController {

    private static final Logger LOG = Logger.getLogger(StudentController.class.getName());

    private FacesContext facesContext;

    private Student student;

    @EJB
    private StudentService studentService;

    @ManagedProperty(value = "#{loginBean}")
    private LoginBean loginBean;

    public StudentController() {
    }

    @PostConstruct
    public void setUp() {
        facesContext = FacesContext.getCurrentInstance();
        //If the user is a Student so we refresh his information everytime
        if (loginBean.isStudent()) {
            refresh();
            //Useful for the admin to see the information of a student
        } else if (loginBean.isAdmin()) {
            setStudentFromAdmin();
        }
    }

    public void refresh() {
        student = studentService.findByUserName(loginBean.getRemoteUser());
    }

    /**
     * Set the attribute student if the controller is asked from the Admin (for
     * the profile)
     */
    public void setStudentFromAdmin() {
        Map<String, String> params = facesContext.getExternalContext().getRequestParameterMap();
        String username = params.get("username");
        student = studentService.findByUserName(username);
    }

    /**
     * Redirect the admin to the update profile page of the student
     *
     * @return the page to update the information
     */
    public String redirectUpdateProfilPage() {
        setStudentFromAdmin();
        LOG.info("Admin redirected to the profil page of " + student.getUser().getLogin());
        return "/app/student/updateProfil";
    }

    /**
     * Update the information about the Student
     *
     * @return the link to the profile page
     */
    public String updateProfil() {

        studentService.update(this.student);
        LOG.info("Information update successful");
        facesContext.addMessage(null, new FacesMessage("Information update succesful !"));
        return "/app/student/myProfile.xhtml";

    }

    /**
     * Redirect the admin to the profil page of a Student
     *
     * @return the link to the profil page
     */
    public String seeStudentProfile() {
        if (loginBean.isAdmin()) {
            LOG.info("Admin redirected to the profil page of " + student.getUser().getLogin());
        }
        return "/app/student/myProfile.xhtml";
    }

    /**
     * Redirect the Student to its own profile page
     *
     * @return the link to the profile page
     */
    public String seeMyProfile() {
        LOG.info(student.getUser().getLogin() + " redirected to his profilPage");
        return "/app/student/myProfile";
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.aroussie.bean;

import edu.iit.sat.itmd4515.aroussie.domain.Student;
import edu.iit.sat.itmd4515.aroussie.domain.Visitor;
import edu.iit.sat.itmd4515.aroussie.fp.LazyStudentDataModel;
import edu.iit.sat.itmd4515.aroussie.fp.LazyVisitorDataModel;
import edu.iit.sat.itmd4515.aroussie.service.StudentService;
import edu.iit.sat.itmd4515.aroussie.service.VisitorService;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author Roussiere
 */
@ManagedBean
@RequestScoped
public class AdminController {

    private static final Logger LOG = Logger.getLogger(AdminController.class.getName());
    private FacesContext facesContext;

    private List<Student> studentList;
    private List<Visitor> visitorList;

    @EJB
    StudentService studentService;
    @EJB
    VisitorService visitorService;

    /**
     * Default constructor
     */
    public AdminController() {

    }

    @PostConstruct
    protected void setUp() {
        refresh();
        facesContext = FacesContext.getCurrentInstance();
    }

    /**
     * Refresh the list of Visitors and Students
     */
    public void refresh() {
        studentList = studentService.findAll();
        visitorList = visitorService.findAll();
    }

    /**
     * Delete a Visitor
     *
     * @param username The username of the Visitor we want to delete
     * @return the link to display the visitors
     */
    public String deleteVisitor(String username) {
        LOG.info("prepare to delete " + username);
        Visitor visitor = visitorService.findByUserName(username);
        visitorService.deleteVisitor(visitor);
        LOG.info("the Visitor with the username: " + username + " has been deleted");
        refresh();
        return "/app/admin/displayVisitors";
    }

    /**
     * Delete a Student
     *
     * @param username The username of the Student we want to delete
     * @return the link to display the students
     */
    public String deleteStudent(String username) {
        LOG.info("prepare to delete " + username);
        Student student = studentService.findByUserName(username);
        studentService.deleteStudent(student);
        LOG.info("the Student with the username: " + username + " has been deleted");
        refresh();
        return "/app/admin/displayStudents";
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public List<Visitor> getVisitorList() {
        this.refresh();
        return visitorList;
    }

    public void setVisitorList(List<Visitor> visitorList) {
        this.visitorList = visitorList;
    }

}

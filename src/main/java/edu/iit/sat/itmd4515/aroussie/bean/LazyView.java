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
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author Roussiere
 */
@ManagedBean
@ViewScoped
public class LazyView {

    private static final Logger LOG = Logger.getLogger(LazyView.class.getName());

    private FacesContext facesContext;
    private List<Student> studentList;
    private List<Visitor> visitorList;

    private LazyDataModel<Student> lazyModelStudent;
    private LazyDataModel<Visitor> lazyModelVisitor;

    @EJB
    StudentService studentService;
    @EJB
    VisitorService visitorService;

    public LazyView() {
    }

    @PostConstruct
    public void setUp() {
        refresh();
        lazyModelStudent = new LazyStudentDataModel(studentList);
        lazyModelVisitor = new LazyVisitorDataModel(visitorList);
        facesContext = FacesContext.getCurrentInstance();
        LOG.info("The find page is showed");
    }

    public void refresh() {
        studentList = studentService.findAll();
        visitorList = visitorService.findAll();
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public List<Visitor> getVisitorList() {
        return visitorList;
    }

    public void setVisitorList(List<Visitor> visitorList) {
        this.visitorList = visitorList;
    }

    public LazyDataModel<Student> getLazyModelStudent() {
        return lazyModelStudent;
    }

    public void setLazyModelStudent(LazyDataModel<Student> lazyModelStudent) {
        this.lazyModelStudent = lazyModelStudent;
    }

    public LazyDataModel<Visitor> getLazyModelVisitor() {
        return lazyModelVisitor;
    }

    public void setLazyModelVisitor(LazyDataModel<Visitor> lazyModelVisitor) {
        this.lazyModelVisitor = lazyModelVisitor;
    }

}

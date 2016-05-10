/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.aroussie.bean;

import edu.iit.sat.itmd4515.aroussie.domain.City;
import edu.iit.sat.itmd4515.aroussie.domain.Post;
import edu.iit.sat.itmd4515.aroussie.domain.Student;
import edu.iit.sat.itmd4515.aroussie.domain.Topics;
import edu.iit.sat.itmd4515.aroussie.domain.Visitor;
import edu.iit.sat.itmd4515.aroussie.service.CityService;
import edu.iit.sat.itmd4515.aroussie.service.PostService;
import edu.iit.sat.itmd4515.aroussie.service.StudentService;
import edu.iit.sat.itmd4515.aroussie.service.TopicService;
import edu.iit.sat.itmd4515.aroussie.service.VisitorService;
import java.util.List;
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
public class ChicagoController {

    private static final Logger LOG = Logger.getLogger(ChicagoController.class.getName());

    private FacesContext facesContext;
    @EJB
    TopicService topicService;
    @EJB
    CityService cityService;
    @EJB
    StudentService studentService;
    @EJB
    VisitorService visitorService;
    @EJB
    PostService postService;

    @ManagedProperty(value = "#{loginBean}")
    private LoginBean loginBean;

    private City chicago;
    private Topics topic;
    private List<Topics> listTopics;
    private Post post;
    private List<Post> listPosts;

    private Student student;
    private Visitor visitor;

    /**
     * Default Constructor
     */
    public ChicagoController() {
    }

    @PostConstruct
    public void setUp() {
        facesContext = FacesContext.getCurrentInstance();
        chicago = cityService.findCityByName("Chicago");
        listTopics = chicago.getTopics();
        listPosts = chicago.getPosts();
        topic = new Topics();
        post = new Post();
        if (loginBean.isStudent()) {
            student = studentService.findByUserName(facesContext.getExternalContext().getRemoteUser());
        }
        if (loginBean.isVisitor()) {
            visitor = visitorService.findByUserName(facesContext.getExternalContext().getRemoteUser());
        }

    }

    /**
     * Refresh the list of Topics and Posts for the page Chicago
     */
    public void refresh() {
        chicago = cityService.findCityByName("Chicago");
        listTopics = chicago.getTopics();
        listPosts = chicago.getPosts();
    }

    /**
     * Create a topic
     *
     * @return the home page of the city
     */
    public String createTopic() {
        LOG.info("Attempt to create a Topic");
        topicService.createTopic(this.topic, this.chicago, this.student);
        LOG.info("Topic created !");
        refresh();
        return ("/app/public/chicago/index.xhtml");
    }

    public List<Topics> getListTopics() {
        return listTopics;
    }

    public void setListTopics(List<Topics> listTopics) {
        this.listTopics = listTopics;
    }

    public City getChicago() {
        return chicago;
    }

    public void setChicago(City chicago) {
        this.chicago = chicago;
    }

    public Topics getTopic() {
        return topic;
    }

    public void setTopic(Topics topic) {
        this.topic = topic;
    }

    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Visitor getVisitor() {
        return visitor;
    }

    public void setVisitor(Visitor visitor) {
        this.visitor = visitor;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public List<Post> getListPosts() {
        return listPosts;
    }

    public void setListPosts(List<Post> listPosts) {
        this.listPosts = listPosts;
    }

}

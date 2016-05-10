/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.aroussie.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

/**
 * Represents the different sections of the web site as cities.
 *
 * @author Roussiere
 */
@Entity
@NamedQueries(
        {
            @NamedQuery(name = "City.findByName", query = "select c from City c where c.name=:name"),
            @NamedQuery(name = "City.findAll", query = "select c from City  c")
        }
)
public class City implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CITY_ID")
    private Long id;
    @NotNull(message = "Please select a city")
    private String name;

    @OneToMany(mappedBy = "city_topic")
    private List<Topics> topics = new ArrayList<>();

    @OneToMany(mappedBy = "city_post")
    private List<Post> posts = new ArrayList<>();

    @ManyToMany(mappedBy = "cities")
    private List<Visitor> visitors = new ArrayList<>();

    @OneToMany(mappedBy = "city")
    private List<Student> students = new ArrayList<>();

    @ManyToOne
    private Administrator admin;

    /**
     * Default constructor
     */
    public City() {
    }

    /**
     * Add a section with a name
     *
     * @param name the name of the section
     */
    public City(String name) {
        this.name = name;
    }

    /**
     * Add a visitor in the section
     *
     * @param visitor a user
     */
    public void addVisitor(Visitor visitor) {
        if (!visitors.contains(visitor)) {
            visitors.add(visitor);
        }
        if (!visitor.getCities().contains(this)) {
            visitor.getCities().add(this);
        }
    }

    /**
     * Make the link between the city and its admin
     *
     * @param admin Administrator entity
     */
    public void addAdmin(Administrator admin) {
        if (this.admin != admin) {
            this.setAdmin(admin);
        }
        if (!admin.getCities().contains(this)) {
            admin.getCities().add(this);
        }
    }

    /**
     * Delete a Visitor from a section
     *
     * @param visitor a user
     */
    public void deleteVisitor(Visitor visitor) {
        if (visitors.contains(visitor)) {
            visitors.remove(visitor);
        }
        if (visitor.getCities().contains(this)) {
            visitor.getCities().remove(this);
        }
    }

    /**
     * Return a list with all the students
     *
     * @return students
     */
    public List<Student> getStudents() {
        return students;
    }

    /**
     * Define a list of students
     *
     * @param students a list
     */
    public void setStudents(List<Student> students) {
        this.students = students;
    }

    /**
     * Return a list with all the visitors
     *
     * @return visitors
     */
    public List<Visitor> getVisitors() {
        return visitors;
    }

    /**
     * set a list of visitors in the section's one
     *
     * @param visitors a user
     */
    public void setVisitors(List<Visitor> visitors) {
        this.visitors = visitors;
    }

    /**
     * Add a post in the section
     *
     * @param post a post like a question in a forum
     */
    public void addPost(Post post) {
        if (!posts.contains(post)) {
            posts.add(post);
        }
        post.setCity_post(this);
    }

    /**
     * Delete a post from the section
     *
     * @param post a post like a question in a forum
     */
    public void deletePost(Post post) {
        if (posts.contains(post)) {
            posts.remove(post);
        }
    }

    /**
     * Return a list with all the posts n the section
     *
     * @return posts
     */
    public List<Post> getPosts() {
        return posts;
    }

    /**
     * Set a list of posts in the section's one
     *
     * @param posts a list of posts
     */
    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    /**
     * Add a topic in the section
     *
     * @param topic a topic
     */
    public void addTopic(Topics topic) {
        if (!topics.contains(topic)) {
            topics.add(topic);
        }
        topic.setCity_topic(this);
    }

    /**
     * Delete a topic from the section
     *
     * @param topic a topic
     */
    public void deleteTopic(Topics topic) {
        if (topics.contains(topic)) {
            topics.remove(topic);
        }
    }

    /**
     * Return a list with all the topics of the section
     *
     * @return topics
     */
    public List<Topics> getTopics() {
        return topics;
    }

    /**
     * Set a list of topics in the section's one
     *
     * @param topics a Topic object
     */
    public void setTopics(List<Topics> topics) {
        this.topics = topics;
    }

    @Override
    /**
     * Show in the output windows the attriuts of the section
     */
    public String toString() {
        return "Country{" + "id=" + id + ", name=" + name + '}';
    }

    /**
     * Return the admin of the city
     *
     * @return an Administrator entity
     */
    public Administrator getAdmin() {
        return admin;
    }

    /**
     * Define the admin of the city
     *
     * @param admin Administrator entity
     */
    public void setAdmin(Administrator admin) {
        this.admin = admin;
    }

    /**
     * Return the ID of the section
     *
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Return the name of the section
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Define the name of the section
     *
     * @param name the name of the section
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Define the ID of the section
     *
     * @param id the id of the section
     */
    public void setId(Long id) {
        this.id = id;
    }

}

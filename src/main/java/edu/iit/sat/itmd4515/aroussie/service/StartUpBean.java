/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.aroussie.service;

import edu.iit.sat.itmd4515.aroussie.domain.Administrator;
import edu.iit.sat.itmd4515.aroussie.domain.City;
import edu.iit.sat.itmd4515.aroussie.domain.Post;
import edu.iit.sat.itmd4515.aroussie.domain.Student;
import edu.iit.sat.itmd4515.aroussie.domain.Topics;
import edu.iit.sat.itmd4515.aroussie.domain.Visitor;
import edu.iit.sat.itmd4515.aroussie.domain.security.User;
import edu.iit.sat.itmd4515.aroussie.domain.security.UserGroup;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Roussiere
 */
@Startup
@Singleton //Means it's unique
public class StartUpBean {

    @EJB
    StudentService studentService;
    @EJB
    VisitorService visitorService;
    @EJB
    AdminService adminService;
    @EJB
    CityService cityService;
    @EJB
    PostService postService;
    @EJB
    TopicService topicService;

    @PersistenceContext(name = "aroussiePU")
    EntityManager em;

    /**
     * Default constructor
     */
    public StartUpBean() {
    }

    @PostConstruct
    public void setUpDataBase() {

        //Let's start with the groups and users
        UserGroup visitorsGroup = new UserGroup("visitors", "You are a visitor. "
                + "That means that you are able to see any cities you want, with their topics. "
                + "If you have questions, you can submit them in the FAQ section of the city you are interested in.");

        UserGroup studentsGroup = new UserGroup("students", "You are a student who is/was in an abroad school."
                + " The interest of this web site depends on you. You are free to go seeing every city you want. However,"
                + " you can only add photos, or topics in the section according to the city where you are/were studying."
                + " Please, try to regularly check the FAQ section of your city to answer to the questions of the visitors.");

        UserGroup adminGroup = new UserGroup("admin", "You are the admin. You are able to create/delete/update any user, "
                + "delete any contents and manage all the application actually");

        em.persist(visitorsGroup);
        em.persist(studentsGroup);
        em.persist(adminGroup);

        User visitor1 = new User("visitor1", "visitorun");
        User visitor2 = new User("visitor2", "visitordeux");
        User student1 = new User("student1", "studentun");
        User student2 = new User("student2", "studentdeux");
        User adminUser = new User("admin", "adminpassword");

        visitor1.addUserToGroup(visitorsGroup);
        visitor2.addUserToGroup(visitorsGroup);
        student1.addUserToGroup(studentsGroup);
        student2.addUserToGroup(studentsGroup);
        adminUser.addUserToGroup(adminGroup);

        em.persist(visitor1);
        em.persist(visitor2);
        em.persist(student1);
        em.persist(student2);
        em.persist(adminUser);

        // Now let's do the association between the users and their entities
        Visitor v1 = new Visitor("Alexandre", "Roussiere", "aroussie@hawk.iit.edu");
        Visitor v2 = new Visitor("Alexandre", "Castro da Costa", "aaa@itworks.cool");
        Student s1 = new Student("Dupoy", "Simon", "aaa@test.student");
        Student s2 = new Student("Lieb", "Leopold", "aaa@bbb.com");
        Administrator admin = new Administrator();

        v1.setUser(visitor1);
        v2.setUser(visitor2);
        s1.setUser(student1);
        s2.setUser(student2);
        admin.setUser(adminUser);

        // Now we create the cities
        City chicago = new City("Chicago");
        City plymouth = new City("Plymouth");
        City hongkong = new City("Hong-Kong");
        City wollongong = new City("Wollongong");
        City sherbrooke = new City("Sherbrooke");

        cityService.create(chicago);
        cityService.create(plymouth);
        cityService.create(hongkong);
        cityService.create(wollongong);
        cityService.create(sherbrooke);

        //We connect the users with the cities
        List<City> citiesList = cityService.findAllCities();

        for (City cities : citiesList) {

            v1.addCity(cities);
            v2.addCity(cities);
            admin.addCity(cities);

        }
        s1.addCity(chicago);
        s2.addCity(wollongong);

        //We create the visitors and students in the DB
        visitorService.create(v1);
        visitorService.create(v2);
        studentService.create(s1);
        studentService.create(s2);

        adminService.create(admin);

        Post post1 = new Post();
        post1.setContent("Hi this post is just a test to know if it works");
        post1.setSubject("Test");
        post1.setAuthorName("Roussière");
        post1.setAuthorFirstname("Alexandre");
        chicago.addPost(post1);

        postService.create(post1);

        Post post2 = new Post();
        post2.setContent("Hi this post is designed for Hong-Kong page");
        post2.setSubject("Test for Hong-Kong");
        post2.setAuthorName("Roussière");
        post2.setAuthorFirstname("Alexandre");
        hongkong.addPost(post2);

        postService.create(post2);

        Topics topic1 = new Topics();
        topic1.setContent("Hi this is just a test to know if it's working");
        topic1.setTitle("Test");
        topic1.setAuthorName("Roussière");
        topic1.setAuthorFirstname("Alexandre");
        chicago.addTopic(topic1);

        topicService.create(topic1);

        Topics topic2 = new Topics();
        topic2.setContent("Hi this topic is designed for Hong-Kong page");
        topic2.setTitle("Test for Hong-Kong");
        topic2.setAuthorName("Roussière");
        topic2.setAuthorFirstname("Alexandre");
        hongkong.addTopic(topic2);

        topicService.create(topic2);

        Topics topic3 = new Topics();
        topic3.setContent("Hi this topic is designed for Plymouth page");
        topic3.setTitle("Test for Plymouth");
        topic3.setAuthorName("Roussière");
        topic3.setAuthorFirstname("Alexandre");
        plymouth.addTopic(topic3);

        topicService.create(topic3);

        Topics topic4 = new Topics();
        topic4.setContent("Hi this topic is designed for Sherbrooke page");
        topic4.setTitle("Test for Sherbrooke");
        topic4.setAuthorName("Roussière");
        topic4.setAuthorFirstname("Alexandre");
        sherbrooke.addTopic(topic4);

        topicService.create(topic4);

        Topics topic5 = new Topics();
        topic5.setContent("Hi this topic is designed for Wollongong page");
        topic5.setTitle("Test for Wollongong");
        topic5.setAuthorName("Roussière");
        topic5.setAuthorFirstname("Alexandre");
        wollongong.addTopic(topic5);

        topicService.create(topic5);
    }

}

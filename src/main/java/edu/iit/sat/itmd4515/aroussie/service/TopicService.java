/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.aroussie.service;

import edu.iit.sat.itmd4515.aroussie.domain.City;
import edu.iit.sat.itmd4515.aroussie.domain.Student;
import edu.iit.sat.itmd4515.aroussie.domain.Topics;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 *
 * @author Roussiere
 */
@Stateless
public class TopicService extends AbstractService<Topics> {

    public TopicService() {
        super(Topics.class);
    }

    /**
     * Find all the topics for a city
     *
     * @param city the City where we want to find the topics
     * @return a list of Topics
     */
    public List<Topics> findAllByCity(City city) {
        TypedQuery<Topics> query = em.createNamedQuery("Topics.FindAllByCity", Topics.class);
        query.setParameter(city.getName(), "cityName");
        return query.getResultList();
    }

    /**
     * Persist a topic in the database
     *
     * @param topic the Topic to persist
     * @param city the City where belongs the topic
     * @param student the Student who creates the topic
     */
    public void createTopic(Topics topic, City city, Student student) {
        city = em.getReference(City.class, city.getId());
        student = em.getReference(Student.class, student.getId());
        topic.setAuthorName(student.getName());
        topic.setAuthorFirstname(student.getFirstName());
        city.addTopic(topic);
        em.persist(topic);
    }
}

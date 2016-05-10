/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.aroussie.service;

import edu.iit.sat.itmd4515.aroussie.domain.City;
import edu.iit.sat.itmd4515.aroussie.domain.Visitor;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import java.util.List;
import javax.ejb.EJB;

import javax.ejb.Stateless;

import javax.persistence.NoResultException;

import javax.persistence.TypedQuery;

/**
 *
 * @author Roussiere
 */
@Stateless
public class VisitorService extends AbstractService<Visitor> {

    @EJB
    CityService cityService;

    /**
     * Default Constructor
     */
    public VisitorService() {
        super(Visitor.class);
    }

    /**
     * Find a Visitor by his firstName
     *
     * @param firstName a string
     * @return a list of visitor
     */
    public List<Visitor> findByFirstName(String firstName) {
        TypedQuery<Visitor> query = em.createNamedQuery("Visitor.FindByFirstName", Visitor.class);
        query.setParameter("firstName", firstName);
        return query.getResultList();
    }

    /**
     * Find a visitor by his lastName
     *
     * @param name a string
     * @return a list of visitor
     */
    public List<Visitor> findByName(String name) {
        TypedQuery<Visitor> query = em.createNamedQuery("Visitor.FindByName", Visitor.class);
        query.setParameter("name", name);
        return query.getResultList();
    }

    /**
     * Find a Visitor thanks to his userName
     *
     * @param userName a string
     * @return
     */
    public Visitor findByUserName(String userName) {
        TypedQuery<Visitor> query = em.createNamedQuery("Visitor.FindByUserName", Visitor.class);
        query.setParameter("userName", userName);
        return query.getSingleResult();
    }

    /**
     * Return a list of all the visitors
     *
     * @return a list
     */
    public List<Visitor> findAll() {

        TypedQuery<Visitor> query = em.createNamedQuery("Visitor.FindAll", Visitor.class);
        return query.getResultList();
    }

    /**
     * Find all the mail adresses of the Visitors
     *
     * @return List of Strings
     */
    public List<String> FindAllMailAdress() {
        TypedQuery<String> query = em.createNamedQuery("Visitor.FindMailAdresses", String.class);
        return query.getResultList();
    }

    /**
     * Determine if an Email adress is already associated to a Visitor
     *
     * @param mail String
     * @return A boolean
     */
    public boolean MailAdressExisted(String mail) {
        TypedQuery<Visitor> query = em.createNamedQuery("Visitor.FindByMail", Visitor.class);
        query.setParameter("mail", mail);
        boolean exist = TRUE;
        List<Visitor> v = query.getResultList();
        try {

            if (v.isEmpty()) {
                exist = FALSE;
            }

        } catch (NoResultException e) {
            exist = FALSE;
        }
        return exist;
    }

    /**
     * Delete a Visitor
     *
     * @param visitor
     */
    public void deleteVisitor(Visitor visitor) {
        visitor = em.getReference(Visitor.class, visitor.getId());

        List<City> cities = cityService.findAllCities();
        for (City city : cities) {
            visitor.removeCity(city);
        }
        em.remove(visitor);
    }
}

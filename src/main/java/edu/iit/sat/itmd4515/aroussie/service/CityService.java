/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.aroussie.service;

import edu.iit.sat.itmd4515.aroussie.domain.City;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 *
 * @author Roussiere
 */
@Stateless
public class CityService extends AbstractService<City> {

    /**
     * Default constructor
     */
    public CityService() {
        super(City.class);
    }

    /**
     * Return a list with all the cities
     *
     * @return a List of City entities
     */
    public List<City> findAllCities() {
        TypedQuery<City> query = em.createNamedQuery("City.findAll", City.class);
        return query.getResultList();
    }

    /**
     * Return a city found by its name
     *
     * @param name Sring
     * @return City
     */
    public City findCityByName(String name) {
        TypedQuery<City> query = em.createNamedQuery("City.findByName", City.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }
}

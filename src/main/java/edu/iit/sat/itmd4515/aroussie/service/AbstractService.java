/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.aroussie.service;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Roussiere
 */
public abstract class AbstractService<T> {

    @PersistenceContext(name = "aroussiePU")
    protected EntityManager em;

    private Class<T> entityClass;

    /**
     * Default Constructor
     */
    public AbstractService(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    /**
     * Create an entity
     *
     * @param entity
     */
    public void create(T entity) {
        em.persist(entity);
    }

    /**
     * Find an entity by its ID
     *
     * @param id an object
     * @return an entity
     */
    public T findById(Object id) {
        return em.find(entityClass, id);
    }

    /**
     * Update an existing entity
     *
     * @param entity an entity
     */
    public void update(T entity) {
        em.merge(entity);
    }

    /**
     * Delete an entity from the database
     *
     * @param entity an entity
     */
    public void delete(T entity) {
        em.remove(entity);
    }

}

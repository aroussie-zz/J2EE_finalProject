/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.aroussie.service;

import edu.iit.sat.itmd4515.aroussie.domain.security.User;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.TypedQuery;

/**
 *
 * @author Roussiere
 */
@Stateless
public class UserService extends AbstractService<User> {

    public UserService() {
        super(User.class);
    }

    /**
     * Return the name of the group of a user
     *
     * @param name the name of the user
     * @return the name of the group where belongs the user
     */
    public String findGroup(String name) {
        TypedQuery<User> query = em.createNamedQuery("User.findByName", User.class);
        query.setParameter("name", name);
        User u = query.getSingleResult();
        return u.getUserGroups().get(0).getGroupName();
    }
}

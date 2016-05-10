/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.aroussie.service;

import edu.iit.sat.itmd4515.aroussie.domain.security.UserGroup;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.TypedQuery;

/**
 *
 * @author Roussiere
 */
@Stateless
@Named
public class UserGroupService extends AbstractService<UserGroup> {

    public UserGroupService() {
        super(UserGroup.class);
    }

    /**
     * Retourn the group of the user thanks to his name
     *
     * @param groupName
     * @return
     */
    public UserGroup findGroupByName(String groupName) {
        TypedQuery<UserGroup> query = em.createNamedQuery("UserGroup.findGroupByName", UserGroup.class);
        query.setParameter("groupName", groupName);
        return query.getSingleResult();
    }

}

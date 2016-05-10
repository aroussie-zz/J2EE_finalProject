/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.aroussie.service;

import edu.iit.sat.itmd4515.aroussie.domain.Administrator;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 *
 * @author Roussiere
 */
@Stateless
public class AdminService extends AbstractService<Administrator> {

    public AdminService() {
        super(Administrator.class);
    }

    public Administrator findByUserName(String userName) {
        TypedQuery<Administrator> query = em.createNamedQuery("Administrator.FindByUserName", Administrator.class);
        query.setParameter("userName", userName);
        return query.getSingleResult();

    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.aroussie.service;

import edu.iit.sat.itmd4515.aroussie.domain.City;
import edu.iit.sat.itmd4515.aroussie.domain.Post;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 *
 * @author Roussiere
 */
@Stateless
public class PostService extends AbstractService<Post> {

    public PostService() {
        super(Post.class);
    }

    /**
     * Return the list of Posts for a City
     *
     * @param city a City object
     * @return a list of Posts
     */
    public List<Post> findAllPosts(City city) {
        TypedQuery<Post> query = em.createNamedQuery("Post.findAllByCity", Post.class);
        query.setParameter(city.getName(), "cityName");
        return query.getResultList();
    }

}

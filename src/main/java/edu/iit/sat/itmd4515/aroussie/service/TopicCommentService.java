/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.aroussie.service;

import edu.iit.sat.itmd4515.aroussie.domain.TopicComment;
import javax.ejb.Stateless;

/**
 *
 * @author Roussiere
 */
@Stateless
public class TopicCommentService extends AbstractService<TopicComment> {

    public TopicCommentService() {
        super(TopicComment.class);
    }

}

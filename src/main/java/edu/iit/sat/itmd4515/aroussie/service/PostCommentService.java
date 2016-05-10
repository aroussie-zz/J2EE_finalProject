/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.aroussie.service;

import edu.iit.sat.itmd4515.aroussie.domain.PostComment;
import javax.ejb.Stateless;

/**
 *
 * @author Roussiere
 */
@Stateless
public class PostCommentService extends AbstractService<PostComment> {

    public PostCommentService() {
        super(PostComment.class);
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.aroussie.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import javax.persistence.Column;

/**
 * A comment made in a post
 *
 * @author Roussiere
 */
@Entity
public class PostComment extends ObjectEntity implements Serializable {

    private String content;

    private Post post;

    private String authorName;
    private String authorFirstnme;

    /**
     * Default Constructor
     */
    public PostComment() {
    }

    /**
     * Create a Post comment with a content
     *
     * @param content a content
     */
    public PostComment(String content) {
        this.content = content;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    /**
     * Return the content of the post comment
     *
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * Define the content of the post comment
     *
     * @param content the content
     */
    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorFirstnme() {
        return authorFirstnme;
    }

    public void setAuthorFirstnme(String authorFirstnme) {
        this.authorFirstnme = authorFirstnme;
    }

}

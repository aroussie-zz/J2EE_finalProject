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

/**
 * A comment made in a topic
 *
 * @author Roussiere
 */
@Entity
public class TopicComment extends ObjectEntity implements Serializable {

    private String content;
    private Topics topic;
    private String authorName;
    private String authorFistname;

    /**
     * Default Constructor
     */
    public TopicComment() {
    }

    /**
     * Create a Post comment with a content
     *
     * @param content a content
     */
    public TopicComment(String content) {
        this.content = content;
    }

    public Topics getTopic() {
        return topic;
    }

    public void setTopic(Topics topic) {
        this.topic = topic;
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

    public String getAuthorFistname() {
        return authorFistname;
    }

    public void setAuthorFistname(String authorFistname) {
        this.authorFistname = authorFistname;
    }

}

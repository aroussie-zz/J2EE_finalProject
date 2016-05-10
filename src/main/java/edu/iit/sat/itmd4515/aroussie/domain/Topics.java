/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.aroussie.domain;

import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 * A topic that can be add in a section(city) of the website.
 *
 * @author Roussiere
 */
@Entity
@NamedQueries(
        {
            @NamedQuery(name = "Topics.FindByTitle", query = "select t from Topics t where t.title=:title"),
            @NamedQuery(name = "Topics.FindAllByCity", query = "select t from Topics t where t.city_topic.name=:cityName")

        }
)
public class Topics extends ObjectEntity implements Serializable {

    private String title;
    @Lob
    private String content;

    private String authorName;
    private String authorFirstname;

    @OneToMany(mappedBy = "topic", cascade = CascadeType.REMOVE)
    private List<TopicComment> topic_comment = new ArrayList<>();

    @ManyToOne(optional = false)
    @JoinColumn(name = "ID_CITY", referencedColumnName = "CITY_ID")
    private City city_topic;

    /**
     * Default constructor
     */
    public Topics() {
    }

    /**
     * Create a topic with a title and some content
     *
     * @param title the title of the topic
     * @param content its content
     */
    public Topics(String title, String content) {
        this.title = title;
        this.content = content;
    }

    /**
     * Add a comment to the Topic
     *
     * @param comment a comment
     */
    public void addCommment(TopicComment comment) {
        if (!topic_comment.contains(comment)) {
            topic_comment.add(comment);
        }
        comment.setTopic(this);
    }

    /**
     * Delete a comment from a Topic
     *
     * @param comment a comment
     */
    public void deleteComment(TopicComment comment) {
        if (topic_comment.contains(comment)) {
            topic_comment.remove(comment);
        }
    }

    /**
     * Return a list with all the comments made on in the topic
     *
     * @return topic_comment
     */
    public List<TopicComment> getTopic_comment() {
        return topic_comment;
    }

    /**
     * Set a list of comments for the topic's one
     *
     * @param topic_comment a list of comments for a topic
     */
    public void setTopic_comment(List<TopicComment> topic_comment) {
        this.topic_comment = topic_comment;
    }

    /**
     * Return the section where the topic is
     *
     * @return city_topic
     */
    public City getCity_topic() {
        return city_topic;
    }

    /**
     * Define the section where the topic belongs
     *
     * @param city_topic the section
     */
    public void setCity_topic(City city_topic) {
        this.city_topic = city_topic;
    }

    /**
     * Return the title of the topic
     *
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Define the title of the topic
     *
     * @param title the title of the topic
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Return the content of the topic
     *
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * Define the content of a topic
     *
     * @param content the content of the topic
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

    public String getAuthorFirstname() {
        return authorFirstname;
    }

    public void setAuthorFirstname(String authorFirstname) {
        this.authorFirstname = authorFirstname;
    }

}

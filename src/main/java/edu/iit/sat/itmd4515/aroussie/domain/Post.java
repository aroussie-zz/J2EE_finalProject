/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.aroussie.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 * A post as a question that can be made in a section(city)
 *
 * @author Roussiere
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Post.findBySubject", query = "select p FROM Post p where p.subject=:subject"),
    @NamedQuery(name = "Post.findAllByCity", query = "select p FROM Post p where p.city_post.name=:cityName")
}
)
public class Post extends ObjectEntity implements Serializable {

    private String content;
    private String subject;

    private String authorName;
    private String authorFirstname;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ID_CITY", referencedColumnName = "CITY_ID")
    private City city_post;

    @OneToMany(mappedBy = "post")
    private List<PostComment> post_comments = new ArrayList<>();

    /**
     * Default constructor
     */
    public Post() {
    }

    /**
     * Create a Post with a subject and a content
     *
     * @param subject the subject of the post
     * @param content the content of the post
     */
    public Post(String subject, String content) {
        this.content = content;
        this.subject = subject;
    }

    /**
     * Add a comment into the Post
     *
     * @param comment a comment
     */
    public void addCommment(PostComment comment) {
        if (!post_comments.contains(comment)) {
            post_comments.add(comment);
        }
        comment.setPost(this);
    }

    /**
     * Delete a comment from a post
     *
     * @param comment a comment
     */
    public void deleteComment(PostComment comment) {
        if (post_comments.contains(comment)) {
            post_comments.remove(comment);
        }
    }

    /**
     * Return the list of the comments made on the post
     *
     * @return post_comments
     */
    public List<PostComment> getPost_comments() {
        return post_comments;
    }

    /**
     * set a list of comments of the post
     *
     * @param post_comments a list of comments
     */
    public void setPost_comments(List<PostComment> post_comments) {
        this.post_comments = post_comments;
    }

    /**
     * Return the section where the post have been made
     *
     * @return city_post
     */
    public City getCity_post() {
        return city_post;
    }

    /**
     * define the section where the post belongs
     *
     * @param city_post the section
     */
    public void setCity_post(City city_post) {
        this.city_post = city_post;

    }

    /**
     * Return the subject of a post
     *
     * @return subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Define the subject of a post
     *
     * @param subject the subject of a post
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * return the content of a post
     *
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * Define the content of a post
     *
     * @param content
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

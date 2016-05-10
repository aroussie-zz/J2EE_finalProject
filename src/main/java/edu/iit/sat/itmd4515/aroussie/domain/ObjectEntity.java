/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.aroussie.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 * The super class for all the object that users can add like a Post, a comment,
 * a topic
 *
 * @author Roussiere
 */
@MappedSuperclass
public class ObjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @Column(nullable = false)
    @NotNull
    @JoinColumn(name = "DatePosted")
    @Temporal(value = TemporalType.DATE)
    protected Date datePosted;

    public ObjectEntity() {
    }

    /**
     * Set the date at each update or persist in the Database
     */
    @PrePersist
    @PreUpdate
    protected void ActualDate() {
        this.datePosted = new Date();
    }

    /**
     * return the date when the post have been made
     *
     * @return datePosted
     */
    public Date getDatePosted() {
        return datePosted;
    }

    /**
     * return the ID of a post
     *
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Define the ID of a post
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.aroussie.domain.security;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Text;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;

/**
 * Represent a group of user
 *
 * @author Roussiere
 */
@Entity
@NamedQuery(name = "UserGroup.findGroupByName", query = "select g from UserGroup g where g.groupName=:groupName")

public class UserGroup implements Serializable {

    @Id

    private String groupName;
    @Column(length = 500)
    private String groupDescription;

    @ManyToMany(mappedBy = "listUserGroups")
    @JoinTable(name = "User_UserGroup",
            joinColumns = @JoinColumn(name = "groupName"),
            inverseJoinColumns = @JoinColumn(name = "login"))
    List<User> users = new ArrayList<>();

    /**
     * Default Constructor of a group
     */
    public UserGroup() {
    }

    /**
     * Create a group of user wih a name and a description
     *
     * @param groupName a string
     * @param groupDescrption a string
     */
    public UserGroup(String groupName, String groupDescrption) {
        this.groupName = groupName;
        this.groupDescription = groupDescrption;
    }

    /**
     * Return the list of user who belongs to the group
     *
     * @return a list of users
     */
    public List<User> getUsers() {
        return users;
    }

    /**
     * Define the list of users who belongs to the group
     *
     * @param users a list of users
     */
    public void setUsers(List<User> users) {
        this.users = users;
    }

    /**
     * Get the value of groupDescription
     *
     * @return the value of groupDescription
     */
    public String getGroupDescription() {
        return groupDescription;
    }

    /**
     * Set the value of groupDescription
     *
     * @param groupDescription new value of groupDescription
     */
    public void setGroupDescription(String groupDescription) {
        this.groupDescription = groupDescription;
    }

    /**
     * Get the value of string
     *
     * @return the value of string
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * Set the value of groupName
     *
     * @param name new value of groupName
     */
    public void setGroupName(String name) {
        this.groupName = name;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.aroussie.domain.security;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotNull;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * Represent a user of the web app
 *
 * @author Roussiere
 */
@Entity
@NamedQueries(
        {
            @NamedQuery(name = "User.findByName", query = "select u from User u where u.login=:name")

        }
)
public class User implements Serializable {

    @Id
    @NotNull(message = "Please write a login")
    private String login;
    @NotNull(message = "Please write a password")
    private String password;

    @ManyToMany
    @JoinTable(name = "User_UserGroup",
            joinColumns = @JoinColumn(name = "login"),
            inverseJoinColumns = @JoinColumn(name = "groupName"))
    List<UserGroup> listUserGroups = new ArrayList<>();

    /**
     * Default constructor of User
     */
    public User() {
    }

    /**
     * Create a user with a login and a password
     *
     * @param login a string
     * @param password a string
     */
    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    /**
     * Hash the password before put it in the Database
     */
    @PrePersist
    private void hashPassword() {
        String hashedPassword = DigestUtils.sha256Hex(this.password);
        this.password = hashedPassword;
    }

    /**
     * Add a user to a Group
     *
     * @param group the group you want to add the user to
     */
    public void addUserToGroup(UserGroup group) {
        if (!this.listUserGroups.contains(group)) {
            listUserGroups.add(group);
        }
        if (!group.getUsers().contains(this)) {
            group.getUsers().add(this);
        }
    }

    /**
     * Remove a user from a group
     *
     * @param group
     */
    public void removeUserToGroup(UserGroup group) {
        if (this.getUserGroups().contains(group)) {
            if (group.getUsers().contains(this)) {
                group.getUsers().remove(this);
            }
        }
    }

    /**
     * Return the list of the groups where belongs the user
     *
     * @return a list listUserGroups
     */
    public List<UserGroup> getUserGroups() {
        return listUserGroups;
    }

    /**
     * Define the list of groups where belongs the user
     *
     * @param userGroups a list of groups
     */
    public void setUserGroups(List<UserGroup> userGroups) {
        this.listUserGroups = userGroups;
    }

    /**
     * Return the login of the User
     *
     * @return login
     */
    public String getLogin() {
        return login;
    }

    /**
     * Define the login of the user
     *
     * @param login the login of the user
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Retur the password of the user
     *
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Define the password for a user
     *
     * @param password un string
     */
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" + "login=" + login + ", password=" + password + '}';
    }

}

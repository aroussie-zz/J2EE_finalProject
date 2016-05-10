/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.aroussie.domain;

import edu.iit.sat.itmd4515.aroussie.domain.security.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Represent the user of the application, of the web site
 *
 * @author Roussiere
 */
@Entity
@NamedQueries(
        {
            @NamedQuery(name = "Visitor.FindByFirstName", query = "select v from Visitor v where v.firstName=:firstName"),
            @NamedQuery(name = "Visitor.FindByName", query = "select v from Visitor v where v.name=:name"),
            @NamedQuery(name = "Visitor.FindByUserName", query = "select v from Visitor v where v.user.login=:userName"),
            @NamedQuery(name = "Visitor.FindAll", query = "select v FROM Visitor v"),
            @NamedQuery(name = "Visitor.FindMailAdresses", query = "select v.mail FROM Visitor v"),
            @NamedQuery(name = "Visitor.FindByMail", query = "select v From Visitor v where v.mail=:mail")

        }
)
public class Visitor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Please set a Firstname")
    @Size(min = 3, max = 30, message = "The firstName must be from 3 characters to 30")
    private String firstName;

    @NotNull(message = "Please set a Name")
    @Size(min = 3, max = 40, message = "The name must be from 3 characters to 40")
    private String name;

    @NotNull(message = "Please set an email adress ")
    @Pattern(regexp = "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)", message = "Please set a VALID adress mail")
    private String mail;

    @ManyToMany
    private List<City> cities = new ArrayList<>();

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private User user;

    /**
     * Default constructor
     */
    public Visitor() {
    }

    /**
     * Create a Visitor with a firstName, a lastname and a mail adress
     *
     * @param firstName the firstName
     * @param name the lastname
     * @param mail the mail adress
     */
    public Visitor(String firstName, String name, String mail) {
        this.firstName = firstName;
        this.name = name;
        this.mail = mail;
    }

    /**
     * Return the user profil of the visitor
     *
     * @return a user entity
     */
    public User getUser() {
        return user;
    }

    /**
     * Define the user entity of the admin
     *
     * @param user a user entity
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Return the list with the sections where the visitor is allowed
     *
     * @return
     */
    public List<City> getCities() {
        return cities;
    }

    /**
     * Set a list of cities
     *
     * @param cities the list of the sections
     */
    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    /**
     * Add the visitor to a city
     *
     * @param c a city
     */
    public void addCity(City c) {
        if (!c.getVisitors().contains(this)) {
            c.getVisitors().add(this);
        }
        if (!this.getCities().contains(c)) {
            this.getCities().add(c);
        }
    }

    /**
     * Remove a visitor from a city
     *
     * @param c a city
     */
    public void removeCity(City c) {
        if (c.getVisitors().contains(this)) {
            c.getVisitors().remove(this);
        }
        if (this.getCities().contains(c)) {
            this.getCities().remove(c);
        }
    }

    /**
     * Return he firstName of a visitor
     *
     * @return firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Define the firstName f a visitor
     *
     * @param firstName his firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Return the lastname of a visitor
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Define the lastname of a visitor
     *
     * @param name his lastname
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Return the mail adress of the visitor
     *
     * @return mail
     */
    public String getMail() {
        return mail;
    }

    /**
     * Define the mail adress of the Visitor
     *
     * @param mail his mail adress
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * Retrn the ID of the visitor
     *
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * Defne the ID of a visitor
     *
     * @param id his ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Visitor{" + "id=" + id + ", firstName=" + firstName + ", name=" + name + ", mail=" + mail + ", cities=" + cities + ", user=" + user + '}';
    }

}

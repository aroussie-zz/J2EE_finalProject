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
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Roussiere
 */
@Entity
@NamedQueries(
        {
            @NamedQuery(name = "Administrator.FindByUserName", query = "select a from Administrator a where a.user.login=:userName")
        }
)
public class Administrator implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name = "Admin";

    @OneToOne
    private User user;

    /**
     * Default Constructor
     */
    public Administrator() {
    }

    @OneToMany(mappedBy = "admin")
    private List<City> cities = new ArrayList<>();

    /**
     * Add a city linked to the admin
     *
     * @param city a City entity
     */
    public void addCity(City city) {
        if (!this.getCities().contains(city)) {
            this.getCities().add(city);
        }
        if (city.getAdmin() != this) {
            city.setAdmin(this);
        }
    }

    /**
     * Return the list of the cities where the admin is linked
     *
     * @return a list of City
     */
    public List<City> getCities() {
        return cities;
    }

    /**
     * Define the list of cities linked with the admin
     *
     * @param cities a list
     */
    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    /**
     * Return the user profil of the admin
     *
     * @return a user entity
     */
    public User getUser() {
        return user;
    }

    /**
     * Define the user entity of the admin
     *
     * @param user a User entity
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Return the name of the admin
     *
     * @return a String
     */
    public String getName() {
        return name;
    }

}

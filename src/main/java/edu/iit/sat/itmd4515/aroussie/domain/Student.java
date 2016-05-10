/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.aroussie.domain;

import edu.iit.sat.itmd4515.aroussie.domain.security.User;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author Roussiere
 */
@Entity
@NamedQueries(
        {
            @NamedQuery(name = "Student.FindByFirstName", query = "select s from Student s where s.firstName=:firstName"),
            @NamedQuery(name = "Student.FindByName", query = "select s from Student s where s.name=:name"),
            @NamedQuery(name = "Student.FindByUserName", query = "select s from Student s where s.user.login=:userName"),
            @NamedQuery(name = "Student.FindAll", query = "select s FROM Student s"),
            @NamedQuery(name = "Student.FindByEmail", query = "select s From Student s where s.mail=:mail")
        }
)
public class Student implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull(message = "please set a Name")
    @Size(min = 3, max = 30, message = "The name must be from 3 characters to 30")
    private String name;

    @NotNull(message = "please set a First Name")
    @Size(min = 3, max = 20, message = "The first name must be from 3 characters to 20")
    private String firstName;

    @NotNull(message = "Please set an Email adress")
    @Pattern(regexp = "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)", message = "Please set a VALID adress mail")
    private String mail;

    private String job;
    private String company;
    private String countryOfWork;
    private String graduate;

    @ManyToOne()
    @JoinColumn(name = "CityOfStudy")
    @NotNull(message = "Please select the city where the student studies/studied")
    private City city;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private User user;

    /**
     * Default constructor of Student
     */
    public Student() {

    }

    /**
     * Create a student with a name a firstname and an email adress
     *
     * @param name a string
     * @param firstName a string
     * @param mail a string
     */
    public Student(String name, String firstName, String mail) {
        this.name = name;
        this.firstName = firstName;
        this.mail = mail;
    }

    /**
     * Add the city to the student
     *
     * @param c a City
     */
    public void addCity(City c) {
        if (!c.getStudents().contains(this)) {
            c.getStudents().add(this);
        }

        this.setCity(c);
    }

    /**
     * Remove a student from a city
     *
     * @param c a City
     */
    public void deleteCity(City c) {
        if (c.getStudents().contains(this)) {
            c.getStudents().remove(this);
        }
        this.city = null;
    }

    /**
     * Return the user profil of the student
     *
     * @return a user entity
     */
    public User getUser() {
        return user;
    }

    /**
     * Define the user entity of the student
     *
     * @param user a User entity
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Return the ID of the student
     *
     * @return Id
     */
    public long getId() {
        return id;
    }

    /**
     * Define the ID of the student
     *
     * @param id a long
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Return the city where the student belong
     *
     * @return city
     */
    public City getCity() {
        return city;
    }

    /**
     * Define the city where the student belongs
     *
     * @param city a City
     */
    public void setCity(City city) {
        this.city = city;
    }

    /**
     * Get the value of graduate
     *
     * @return the value of graduate
     */
    public String getGraduate() {
        return graduate;
    }

    /**
     * Set the value of graduate
     *
     * @param graduate new value of graduate
     */
    public void setGraduate(String graduate) {
        this.graduate = graduate;
    }

    /**
     * Get the value of countryOfWork
     *
     * @return the value of countryOfWork
     */
    public String getCountryOfWork() {
        return countryOfWork;
    }

    /**
     * Set the value of countryOfWork
     *
     * @param countryOfWork new value of countryOfWork
     */
    public void setCountryOfWork(String countryOfWork) {
        this.countryOfWork = countryOfWork;
    }

    /**
     * Get the value of company
     *
     * @return the value of company
     */
    public String getCompany() {
        return company;
    }

    /**
     * Set the value of company
     *
     * @param company new value of company
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * Get the value of job
     *
     * @return the value of job
     */
    public String getJob() {
        return job;
    }

    /**
     * Set the value of job
     *
     * @param job new value of job
     */
    public void setJob(String job) {
        this.job = job;
    }

    /**
     * Get the value of mail
     *
     * @return the value of mail
     */
    public String getMail() {
        return mail;
    }

    /**
     * Set the value of mail
     *
     * @param mail new value of mail
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * Get the value of firstName
     *
     * @return the value of firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set the value of firstName
     *
     * @param firstName new value of firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Get the value of name
     *
     * @return the value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the value of name
     *
     * @param name new value of name
     */
    public void setName(String name) {
        this.name = name;
    }

}

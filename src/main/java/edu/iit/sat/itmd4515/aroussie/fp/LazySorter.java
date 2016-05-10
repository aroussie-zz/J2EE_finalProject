/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.aroussie.fp;

import edu.iit.sat.itmd4515.aroussie.domain.Student;
import java.util.Comparator;
import java.util.logging.Logger;
import org.primefaces.model.SortOrder;

/**
 *
 * @author Roussiere
 */
public class LazySorter implements Comparator<Student> {

    private static final Logger LOG = Logger.getLogger(LazySorter.class.getName());

    private String sortField;
    private SortOrder sortOrder;

    public LazySorter(String sortField, SortOrder sortOrder) {
        this.sortField = sortField;
        this.sortOrder = sortOrder;
    }

    @Override
    /**
     * Compare two students
     */
    public int compare(Student st1, Student st2) {

        Object value1 = "";
        Object value2 = "";

        if (this.sortField.equals("firstName")) {
            value1 = st1.getFirstName();
            value2 = st2.getFirstName();
        } else if (this.sortField.equals("user.login")) {
            value1 = st1.getUser().getLogin();
            value2 = st2.getUser().getLogin();
        } else if (this.sortField.equals("name")) {
            value1 = st1.getName();
            value2 = st2.getName();
        } else if (this.sortField.equals("mail")) {
            value1 = st1.getMail();
            value2 = st2.getMail();
        } else if (this.sortField.equals("city")) {
            value1 = st1.getCity().getName();
            value2 = st2.getCity().getName();
        } else if (this.sortField.equals("graduate")) {
            value1 = st1.getGraduate();
            value2 = st2.getGraduate();
        } else if (this.sortField.equals("countryOfWork")) {
            value1 = st1.getCountryOfWork();
            value2 = st2.getCountryOfWork();
        } else if (this.sortField.equals("company")) {
            value1 = st1.getCompany();
            value2 = st2.getCompany();
        } else if (this.sortField.equals("job")) {
            value1 = st1.getJob();
            value2 = st2.getJob();
        } else {
            LOG.info("Sortfield doesn't match with an attribute of the Class Student");
        }

        // If a field is blank we have to put a default value. Otherwise it would
        // throw an expection...
        if (value1 == null) {
            value1 = "";
        }
        if (value2 == null) {
            value2 = "";
        }

        try {
            int value = ((Comparable) value1).compareTo(value2);
            //Make a student be above or below the other one in the datatable
            return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;  // a ? b: c => If a then b else c

        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

}

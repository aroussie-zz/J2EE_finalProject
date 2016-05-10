/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.aroussie.fp;

import edu.iit.sat.itmd4515.aroussie.domain.Student;
import edu.iit.sat.itmd4515.aroussie.domain.Visitor;
import java.util.Comparator;
import java.util.logging.Logger;
import org.primefaces.model.SortOrder;

/**
 *
 * @author Roussiere
 */
public class LazySorterVisitor implements Comparator<Visitor> {

    private static final Logger LOG = Logger.getLogger(LazySorter.class.getName());

    private String sortField;
    private SortOrder sortOrder;

    public LazySorterVisitor(String sortField, SortOrder sortOrder) {
        this.sortField = sortField;
        this.sortOrder = sortOrder;
    }

    @Override
    /**
     * Compare two visitors
     */
    public int compare(Visitor v1, Visitor v2) {

        Object value1 = "";
        Object value2 = "";

        if (this.sortField.equals("firstName")) {
            value1 = v1.getFirstName();
            value2 = v2.getFirstName();
        } else if (this.sortField.equals("user.login")) {
            value1 = v1.getUser().getLogin();
            value2 = v2.getUser().getLogin();
        } else if (this.sortField.equals("name")) {
            value1 = v1.getName();
            value2 = v2.getName();
        } else if (this.sortField.equals("mail")) {
            value1 = v1.getMail();
            value2 = v2.getMail();
        } else {
            LOG.info("Sortfield doesn't match with an attribute of the Class Visitor");
        }

        try {

            int value = ((Comparable) value1).compareTo(value2);
            //Make a student be above or below the other one in the datatable
            return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;// a ? b: c => If a then b else c
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}

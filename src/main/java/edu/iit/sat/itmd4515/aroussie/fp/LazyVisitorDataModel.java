/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.aroussie.fp;

import edu.iit.sat.itmd4515.aroussie.domain.Visitor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author Roussiere
 */
public class LazyVisitorDataModel extends LazyDataModel<Visitor> {

    private List<Visitor> datasource;
    private static final Logger LOG = Logger.getLogger(LazyStudentDataModel.class.getName());

    public LazyVisitorDataModel(List<Visitor> datasource) {
        this.datasource = datasource;
    }

    @Override
    public Visitor getRowData(String rowKey) {
        for (Visitor visitor : datasource) {
            if (visitor.getUser().getLogin().equals(rowKey)) {
                return visitor;
            }
        }
        return null;

    }

    @Override
    public Object getRowKey(Visitor visitor) {
        return visitor.getUser().getLogin();
    }

    @Override
    public List<Visitor> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {

        List<Visitor> data = new ArrayList<Visitor>();
        String fieldValue = "";
        //filter
        for (Visitor visitor : datasource) {
            boolean match = true;

            if (filters != null) {
                for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
                    try {
                        //We look what is the colomn where the user wants to find something
                        String filterProperty = it.next();

                        Object filterValue = filters.get(filterProperty);
                        //Depends on the colomn, we get the acording attributes of all the visitors of the database
                        if (filterProperty.equals("user.login")) {
                            fieldValue = visitor.getUser().getLogin();
                        } else if (filterProperty.equals("name")) {
                            fieldValue = visitor.getName();
                        } else if (filterProperty.equals("firstName")) {
                            fieldValue = visitor.getFirstName();
                        } else if (filterProperty.equals("mail")) {
                            fieldValue = visitor.getMail();
                        } else {
                            LOG.info("filterValue has no value...");
                        }
                        //Don't forget to LowerCase the fieldValue to don't have to put capital letters when needed (increase usability)
                        if (filterValue == null || fieldValue.toLowerCase().startsWith(filterValue.toString().toLowerCase())) {
                            match = true;
                        } else {
                            match = false;
                            break;
                        }
                    } catch (Exception e) {
                        match = false;
                    }
                }
            }
            if (match) {
                //If there is a match between the value asking and a Visitor we add the visitor to the datalist
                data.add(visitor);
            }
        }

        //sort
        if (sortField != null) {
            //Make the comparison between the visitors
            Collections.sort(data, new LazySorterVisitor(sortField, sortOrder));
        }

        //rowCount
        int dataSize = data.size();
        this.setRowCount(dataSize);

        //paginate
        if (dataSize > pageSize) {
            try {
                return data.subList(first, first + pageSize);
            } catch (IndexOutOfBoundsException e) {
                return data.subList(first, first + (dataSize % pageSize));
            }
        } else {
            return data;
        }
    }
}

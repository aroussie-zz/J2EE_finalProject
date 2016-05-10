/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.aroussie.fp;

import edu.iit.sat.itmd4515.aroussie.domain.Student;
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
public class LazyStudentDataModel extends LazyDataModel<Student> {

    private List<Student> datasource;
    private static final Logger LOG = Logger.getLogger(LazyStudentDataModel.class.getName());

    public LazyStudentDataModel(List<Student> datasource) {
        this.datasource = datasource;
    }

    @Override
    public Student getRowData(String rowKey) {
        for (Student student : datasource) {
            if (student.getUser().getLogin().equals(rowKey)) {
                return student;
            }
        }
        return null;

    }

    @Override
    public Object getRowKey(Student student) {
        return student.getUser().getLogin();
    }

    @Override
    public List<Student> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {

        List<Student> data = new ArrayList<Student>();
        String fieldValue = "";
        //filter (Represents a colomn of the Datatable)
        for (Student student : datasource) {
            boolean match = true;

            if (filters != null) {
                for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
                    try {
                        //We look what is the colomn where the user wants to find something
                        String filterProperty = it.next();

                        Object filterValue = filters.get(filterProperty);
                        //Depends on the colomn, we get the acording attributes of all the students of the database
                        if (filterProperty.equals("user.login")) {
                            fieldValue = student.getUser().getLogin();
                        } else if (filterProperty.equals("name")) {
                            fieldValue = student.getName();
                        } else if (filterProperty.equals("firstName")) {
                            fieldValue = student.getFirstName();
                        } else if (filterProperty.equals("mail")) {
                            fieldValue = student.getMail();
                        } else if (filterProperty.equals("city")) {
                            fieldValue = student.getCity().getName();
                        } else if (filterProperty.equals("graduate")) {
                            fieldValue = student.getGraduate();
                        } else if (filterProperty.equals("countryOfWork")) {
                            fieldValue = student.getCountryOfWork();
                        } else if (filterProperty.equals("company")) {
                            fieldValue = student.getCompany();
                        } else if (filterProperty.equals("job")) {
                            fieldValue = student.getJob();
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
                //If there is a match between the value asking and a Student we add the student to the datalist
                data.add(student);
            }
        }

        //sort
        if (sortField != null) {
            //Make the comparison between the students
            Collections.sort(data, new LazySorter(sortField, sortOrder));
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

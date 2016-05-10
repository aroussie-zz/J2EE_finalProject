/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.aroussie.validators;

import edu.iit.sat.itmd4515.aroussie.service.StudentService;
import edu.iit.sat.itmd4515.aroussie.service.VisitorService;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.persistence.NoResultException;

/**
 *
 * @author Roussiere
 */
@ManagedBean
@RequestScoped
public class EmailUnicityValidator implements Validator {

    @EJB
    private VisitorService visitorService;
    @EJB
    private StudentService studentService;

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        String email = (String) value;
        //We check if the email adress already exist
        try {

            if (visitorService.MailAdressExisted(email) || studentService.MailAdressExisted(email)) {

                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "This adress already exists", null));

            }

        } catch (NoResultException e) {

            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), null);
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage(component.getClientId(facesContext), message);

        }
    }

}

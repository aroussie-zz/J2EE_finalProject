/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.aroussie.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.print.attribute.standard.Severity;

/**
 *
 * @author Roussiere
 */
@FacesValidator(value = "confirmationPasswordValidator")
public class ConfirmationPasswordValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        //We get what the user puts in the Password field
        UIInput passwordComponent = (UIInput) component.getAttributes().get("firstPassword");
        String password = (String) passwordComponent.getValue();

        //We get what the user puts in the confirmationPassword field
        String confirmationPassword = (String) value;

        //If the confirmation ans the password are different we throw an exception
        if (confirmationPassword != null && !password.equals(confirmationPassword)) {

            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "The password and the confirmation are different", null));
        }

    }

}

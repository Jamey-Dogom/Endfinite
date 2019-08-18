package com.jd.endfinite.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.jd.endfinite.models.User;

// component - base annotation that @repo @controller @service come from 
@Component
public class UserValidator implements Validator {
    
    // 1 says we are using the User model
    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }
    
    // 2 checks if passwords are the same - password and confirm password
    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        
        if (!user.getPasswordConfirmation().equals(user.getPassword())) {
            // 3
            errors.rejectValue("passwordConfirmation", "Match");
        }         
    }
}
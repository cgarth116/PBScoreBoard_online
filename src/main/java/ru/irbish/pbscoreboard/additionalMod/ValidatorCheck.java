package ru.irbish.pbscoreboard.additionalMod;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public interface ValidatorCheck {
     default Set<ConstraintViolation<Object>> validatorCheck(Object obj){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Object>> violations = validator.validate(obj);
        return violations;
    }
}

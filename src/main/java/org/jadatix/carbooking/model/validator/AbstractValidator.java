package org.jadatix.carbooking.model.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


public class AbstractValidator<T> implements Validator {
    private final Validator validator;
    private Class<T> clazz;

    public AbstractValidator(Validator validator, Class<T> clazz) {
        this.validator = validator;
        this.clazz = clazz;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return this.clazz.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        validator.validate(target,errors);

    }
}

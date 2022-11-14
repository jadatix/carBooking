package org.jadatix.carbooking.model;

import org.junit.jupiter.api.Test;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import static org.junit.jupiter.api.Assertions.assertFalse;

public abstract class AbstractValidatorTest<T extends IdentifierEntity> {
    @Test
    public void testValidator() {
        T entity = getEntity();
        BindException errors = new BindException(entity, entity.getClass().toString());
        ValidationUtils.invokeValidator(getValidator(), entity, errors);
        assertFalse(errors.hasErrors());
    }

    protected abstract T getEntity();

    protected abstract EntityValidator<T> getValidator();

    protected static abstract class EntityValidator<T> implements Validator {
        private Validator validator;

        public EntityValidator(Validator validator) {
            this.validator = validator;
        }

        @Override
        public boolean supports(Class<?> clazz) {
            return getClazz().equals(clazz);
        }

        @Override
        public void validate(Object target, Errors errors) {
            validator.validate(target, errors);
        }

        protected abstract Class<T> getClazz();
    }
}

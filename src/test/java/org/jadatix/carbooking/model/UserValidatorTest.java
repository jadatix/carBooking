package org.jadatix.carbooking.model;

import org.springframework.validation.Validator;

public class UserValidatorTest extends AbstractValidatorTest<User> {
    private final UserValidator validator;

    public UserValidatorTest(UserValidator validator) {
       this.validator = validator;
    }

    @Override
    protected User getEntity() {
        return null;
    }

    @Override
    protected EntityValidator<User> getValidator() {
        return validator;
    }

    protected static class UserValidator extends EntityValidator<User> {
        public UserValidator(Validator validator) {
            super(validator);
        }

        @Override
        protected Class<User> getClazz() {
            return User.class;
        }
    }
}

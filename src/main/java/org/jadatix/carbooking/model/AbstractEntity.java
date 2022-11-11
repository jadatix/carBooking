package org.jadatix.carbooking.model;

import java.security.SecureRandom;

public abstract class AbstractEntity<T> implements IdentifierEntity {
    protected interface Builder<T> {
        T build();
    }

    protected abstract static class AbstractBuilder<T> implements Builder<T> {
        protected AbstractBuilder() {
        }

        protected String getRandomString() {
            SecureRandom rand = new SecureRandom();
            return rand.ints(48, 123)
                    .filter(Character::isAlphabetic)
                    .limit(15)
                    .mapToObj(c -> (char) c)
                    .collect(StringBuffer::new, StringBuffer::append, StringBuffer::append)
                    .toString();
        }
    }
}

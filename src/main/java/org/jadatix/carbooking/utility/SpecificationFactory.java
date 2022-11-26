package org.jadatix.carbooking.utility;

import org.jadatix.carbooking.model.IdentifierEntity;
import org.springframework.data.jpa.domain.Specification;

public class SpecificationFactory {
    public enum Operator {
        EQUALS,
        NOT_EQUALS
    }

    public static <T extends IdentifierEntity> Specification<T> getSpecification(Class<T> clazz, String fieldName,
                                                                                 Object value, Operator operator) {
        return (root, query, criteriaBuilder) -> {
            if (Operator.EQUALS.equals(operator)) {
                return criteriaBuilder.equal(root.get(fieldName), value);
            } else if (Operator.NOT_EQUALS.equals(operator)) {
                return criteriaBuilder.notEqual(root.get(fieldName), value);
            }
            return null;
        };
    }
}

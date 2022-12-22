package org.jadatix.carbooking.api.v1.request;

import org.jadatix.carbooking.model.IdentifierEntity;

public abstract class AbstractRequest<T extends IdentifierEntity> {

    public abstract T create();
    public abstract T update(T t);
}

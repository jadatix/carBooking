package org.jadatix.carbooking.controller;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class ResultCaptor<Entity> implements Answer<Entity> {
    private Entity result = null;

    public Entity getResult() {
        return result;
    }

    public void setResult(Entity entity) {
        this.result = entity;
    }

    @Override
    public Entity answer(InvocationOnMock invocationOnMock) throws Throwable {
        result = (Entity) invocationOnMock.callRealMethod();
        return result;
    }
}

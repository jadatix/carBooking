package org.jadatix.carbooking.controller;

import org.jadatix.carbooking.api.v1.request.AbstractRequest;
import org.jadatix.carbooking.api.v1.response.AbstractResponse;
import org.jadatix.carbooking.config.BasicAuthWebSecurityConfiguration;
import org.jadatix.carbooking.config.BasicDummySecurityConfiguration;
import org.jadatix.carbooking.model.IdentifierEntity;
import org.jadatix.carbooking.service.AbstractService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WithMockUser(roles = {"MANAGER"})
@ActiveProfiles(profiles = {"develop"})
@Import({BasicDummySecurityConfiguration.class, BasicAuthWebSecurityConfiguration.class})
public abstract class AbstractControllerTest<Entity extends IdentifierEntity,
        Request extends AbstractRequest<Entity>,
        Response extends AbstractResponse> {
    @Autowired
    protected MockMvc mvc;

    @Test
    public void testDelete() throws Exception {
        Entity entity = getNewEntity();
        // TODO: how to properly set id?
        entity.setId(1L);

        mockServiceGetEntity(entity);

        mvc.perform(delete(getControllerPath() + "/{id}", entity.getId()))
                .andExpect(status().isNoContent());

        verify(getService(), times(1)).delete(eq(entity.getId()));
    }

    protected void mockServiceGetEntity(Entity entity) {
        when(getService().get(entity.getId())).thenReturn(entity);
    }

    protected abstract String getControllerPath();

    protected abstract Class<Entity> getEntityClass();

    protected abstract AbstractService<Entity> getService();

    protected abstract Entity getNewEntity();
}

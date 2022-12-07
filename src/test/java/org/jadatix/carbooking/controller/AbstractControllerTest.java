package org.jadatix.carbooking.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jadatix.carbooking.AuthRequireable;
import org.jadatix.carbooking.api.v1.request.AbstractRequest;
import org.jadatix.carbooking.api.v1.response.AbstractResponse;
import org.jadatix.carbooking.model.IdentifierEntity;
import org.jadatix.carbooking.model.Role;
import org.jadatix.carbooking.service.AbstractService;
import org.jadatix.carbooking.service.AuthenticateUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public abstract class AbstractControllerTest<T extends IdentifierEntity, Req extends AbstractRequest, Res extends AbstractResponse> extends AuthRequireable {



    @Autowired
    protected MockMvc mvc;


    protected T entityToDb(){
        Role role = AuthenticateUserService.getCurrent().getRole();
        logoutUser();
        T entity = getRandomEntity();
        getService().create(entity);
        loginAs(role);
        return entity;
    }

    protected abstract AbstractService getService();

    protected abstract String getMapping();

    protected String toJson(Object object){
        return new ObjectMapper().findAndRegisterModules().valueToTree(object).toString();
    }

    protected abstract Res toResponse(T t);

    protected abstract T getRandomEntity();


    @Test
    void testGetAsManager() throws Exception {
        T t = entityToDb();
        Res expected = toResponse(t);
        mvc.perform(get(getMapping()+"/"+ t.getId()))
                .andExpect(status().isOk())
                .andExpect(content().json(toJson(expected)));
    }

    @Test
    void testPostAsManager() throws Exception {
        T t = getRandomEntity();
        t.setId(123L);
        Res expected = toResponse(t);
        mvc.perform(post(getMapping()).contentType(MediaType.APPLICATION_JSON).content(toJson(t)))
                .andExpect(status().isOk())
                .andExpect(content().json(toJson(expected)));
    }

    @Test
    void testPutAsManager() throws Exception {
        T t = entityToDb();
        T newT = getRandomEntity();
        Res expected = toResponse(newT);
        mvc.perform(put(getMapping()+"/"+t.getId()).contentType(MediaType.APPLICATION_JSON).content(toJson(t)))
                .andExpect(status().isOk())
                .andExpect(content().json(toJson(expected)));
    }

    @Test
    void testDeleteAsManager() throws Exception {
        T t = entityToDb();
        mvc.perform(delete(getMapping()+"/"+t.getId()).contentType(MediaType.APPLICATION_JSON).content(toJson(t)))
                .andExpect(status().isOk());
    }

}

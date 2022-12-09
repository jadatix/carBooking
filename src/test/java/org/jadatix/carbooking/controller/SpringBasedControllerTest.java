package org.jadatix.carbooking.controller;

import org.jadatix.carbooking.config.BasicAuthWebSecurityConfiguration;
import org.jadatix.carbooking.config.BasicDummySecurityConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.util.stream.Stream;

@WithMockUser(roles = {"MANAGER"})
@ActiveProfiles(profiles = {"develop"})
@Import({BasicDummySecurityConfiguration.class, BasicAuthWebSecurityConfiguration.class,
        MappingJackson2HttpMessageConverter.class})
public class SpringBasedControllerTest {
    private HttpMessageConverter<Object> mappingJackson2HttpMessageConverter;

    @Autowired
    protected MockMvc mvc;

    @Autowired
    protected void setConverters(HttpMessageConverter<Object>[] converters) {
        this.mappingJackson2HttpMessageConverter = Stream.of(converters)
                .filter(converter -> converter instanceof MappingJackson2HttpMessageConverter)
                .findAny().get();
    }

    protected String getJson(Object object) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(object, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }
}

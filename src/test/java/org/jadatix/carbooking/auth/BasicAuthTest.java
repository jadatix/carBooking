package org.jadatix.carbooking.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jadatix.carbooking.model.Office;
import org.jadatix.carbooking.model.Role;
import org.jadatix.carbooking.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.time.Month;
import java.util.concurrent.Callable;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Disabled
public class BasicAuthTest {
    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;
    private String managerEmail = "tkachuk.ivan.v@chnu.edu.ua";
    private String userEmail = "andrii.liashenko@chnu.edu.ua";
    String secret = "secret";

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    private ResultActions assertRequest(String email, Callable<MockHttpServletRequestBuilder> method) throws Exception {
        return mvc.perform(method.call().with(httpBasic(email,secret)));
    }


    @ParameterizedTest
    @MethodSource("managerGetAccessProvider")
    void managerGetAccessTest(String urlTemplate, ResultMatcher expectedResult) throws Exception {
        assertRequest(managerEmail,() -> get(urlTemplate)).andExpect(expectedResult);
    }

    static Object[][] managerGetAccessProvider(){
        return new Object[][]{
                {"/api/v1/offices",status().isOk()},
                {"/api/v1/users",status().isOk()}
        };
    }

    @ParameterizedTest
    @MethodSource("managerPostAccessProvider")
    void managerPostAccessTest(String urlTemplate, ResultMatcher expectedResult, Object object) throws Exception {
        String body = new ObjectMapper().findAndRegisterModules().valueToTree(object).toString();
        assertRequest(managerEmail,() -> post(urlTemplate).contentType(MediaType.APPLICATION_JSON_VALUE).content(body)).andExpect(expectedResult);
    }

    static Object[][] managerPostAccessProvider(){
        Office officeJson = new Office("Kyiv","Holovna 21");
        User userJson = new User();
        userJson.setRole(Role.MANAGER);
        userJson.setPassport("passport3");
        userJson.setFullName("Ivan Tkachuk");
        userJson.setEmail("example@chnu.edu.ua");
        userJson.setSecret("secret");
        userJson.setBirthday(LocalDate.of(2005, Month.SEPTEMBER, 10));
        return new Object[][]{
                {"/api/v1/offices",status().isCreated(),officeJson},
                {"/api/v1/users",status().isCreated(), userJson}
        };
    }

    @ParameterizedTest
    @MethodSource("managerPutAccessProvider")
    void managerPutAccessTest(String urlTemplate, ResultMatcher expectedResult,  Object object) throws Exception {
        String body = new ObjectMapper().findAndRegisterModules().valueToTree(object).toString();
        assertRequest(managerEmail,() -> put(urlTemplate).contentType(MediaType.APPLICATION_JSON_VALUE).content(body)).andExpect(expectedResult);
    }

    static Object[][] managerPutAccessProvider(){
        Office officeJson = new Office("Kyiv","Holovna 21");
        User userJson = new User();
        userJson.setRole(Role.MANAGER);
        userJson.setFullName("Ivan Tkachuk");
        userJson.setPassport("passport4");
        userJson.setEmail("test@chnu.edu.ua");
        userJson.setSecret("secret");
        userJson.setBirthday(LocalDate.of(2005, Month.SEPTEMBER, 10));
        return new Object[][]{
                {"/api/v1/offices/1",status().isOk(),officeJson},
                {"/api/v1/users/3",status().isOk(), userJson}
        };
    }


    @ParameterizedTest
    @MethodSource("managerDeleteAccessProvider")
    void managerDeleteAccessTest(String urlTemplate, ResultMatcher expectedResult) throws Exception {
        assertRequest(managerEmail,() -> delete(urlTemplate)).andExpect(expectedResult);
    }

    static Object[][] managerDeleteAccessProvider(){
        return new Object[][]{
                {"/api/v1/offices/2",status().isNoContent()},
                {"/api/v1/users/4",status().isNoContent()}
        };
    }

}

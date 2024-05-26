package id.ac.ui.cs.advprog.hoomgroomcommerce.controller;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HomepageControllerTest {

    private HomepageController homepageController;

    @BeforeEach
    public void setUp() {
        homepageController = new HomepageController();
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
    }

    @Test
    @DisplayName("Should return homepage message")
    public void homepageReturnsHomepageMessage() {
        ResponseEntity<String> responseEntity = homepageController.homepage();

        assertEquals("homepage", responseEntity.getBody());
        assertEquals(200, responseEntity.getStatusCodeValue());
    }
}

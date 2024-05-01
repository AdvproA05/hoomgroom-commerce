package id.ac.ui.cs.advprog.hoomgroomcommerce.controller;

import id.ac.ui.cs.advprog.hoomgroomcommerce.model.Pengiriman;
import id.ac.ui.cs.advprog.hoomgroomcommerce.service.PengirimanService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PengirimanController.class)
@AutoConfigureMockMvc
public class PengirimanControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PengirimanService pengirimanService;

    @Test
    public void testUpdateStatusPengiriman() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/pengiriman/updateStatus/123/DIKIRIM")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

 
}

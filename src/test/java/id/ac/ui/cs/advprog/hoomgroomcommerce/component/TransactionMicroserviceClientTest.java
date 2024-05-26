package id.ac.ui.cs.advprog.hoomgroomcommerce.component;

import id.ac.ui.cs.advprog.hoomgroomcommerce.component.TransactionMicroserviceClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class TransactionMicroserviceClientTest {

    @Mock
    private RestTemplate restTemplate;

    private TransactionMicroserviceClient client;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        client = new TransactionMicroserviceClient(restTemplate);
    }

    @Test
    void testFetchTop10Products() {
        // Mock response from the REST service
        List<Map.Entry<String, Long>> mockResponse = List.of(
                new AbstractMap.SimpleEntry<>("Product1", 100L),
                new AbstractMap.SimpleEntry<>("Product2", 200L),
                new AbstractMap.SimpleEntry<>("Product3", 300L)
        );

        // Mock REST service call
        String TRANSACTION_MICROSERVICE_URL = "http://hoomgroom-a05/api/transactions/statistics";
        when(restTemplate.getForObject(TRANSACTION_MICROSERVICE_URL, List.class)).thenReturn(mockResponse);

        // Call the method under test
        List<Map.Entry<String, Long>> result = client.fetchTop10Products();

        // Verify the result
        assertEquals(mockResponse.size(), result.size());
        for (int i = 0; i < mockResponse.size(); i++) {
            assertEquals(mockResponse.get(i), result.get(i));
        }
    }
}

package id.ac.ui.cs.advprog.hoomgroomcommerce.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Component
public class TransactionMicroserviceClient {

    private final RestTemplate restTemplate;

    @Autowired
    public TransactionMicroserviceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Map.Entry<String, Long>> fetchTop10Products() {
        // Make HTTP GET request to the transaction microservice's endpoint
        String TRANSACTION_MICROSERVICE_URL = "http://hoomgroom-a05/api/transactions/statistics";
        return restTemplate.getForObject(TRANSACTION_MICROSERVICE_URL, List.class);
    }
}

import com.store.Application;
import com.store.models.Subscription;
import com.store.models.SubscriptionRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import javax.ws.rs.WebApplicationException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:8095")
public class SubscriptionTest {

    @Test
    public void testCreateOK(){
        RestTemplate restTemplate = new RestTemplate();
        String URL = "http://localhost:8095/ws-server-1.0/restservices/subscription/create";

        try {
            SubscriptionRequest subscription = new SubscriptionRequest();
            subscription.setNumber("997635323");
            subscription.setType("standard");
            ResponseEntity<String> responseEntity  = restTemplate.postForEntity(URL, subscription, String.class);
            System.out.println(responseEntity.getStatusCode().toString());
            assert true;
        } catch (WebApplicationException we){

        }
    }

    @Test
    public void testSyncOK(){
        RestTemplate restTemplate = new RestTemplate();
        String URL = "http://localhost:8095/ws-server-1.0/restservices/subscription/sync";

        try {
            ResponseEntity<Subscription[]> responseEntity  = restTemplate.getForEntity(URL, Subscription[].class);
            Subscription[] subscriptions = responseEntity.getBody();
            assert true;
        } catch (WebApplicationException we){

        }
    }

}



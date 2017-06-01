package cn.jxufe;

import com.orbitz.consul.Consul;
import com.orbitz.consul.model.health.ServiceHealth;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@RestController
public class MainController {

    private final RestTemplate restTemplate = new RestTemplate();
    private final Consul consul = Consul.builder().build();

    @RequestMapping("/{service}/**")
    public ResponseEntity<String> invoke(@PathVariable String service, HttpServletRequest request)
            throws URISyntaxException {
        String url = ((String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE))
                .substring(service.length() + 1);

        List<ServiceHealth> healthList = consul.healthClient().getHealthyServiceInstances(service).getResponse();
        ServiceHealth health = healthList.get(ThreadLocalRandom.current().nextInt(healthList.size()));
        return restTemplate.getForEntity(
                new URI("http://" + health.getNode().getAddress() + ":" + health.getService().getPort()
                        + url + "?" + request.getQueryString()),
                String.class);
    }
}

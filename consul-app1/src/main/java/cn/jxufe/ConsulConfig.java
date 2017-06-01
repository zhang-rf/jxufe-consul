package cn.jxufe;

import com.orbitz.consul.AgentClient;
import com.orbitz.consul.Consul;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConsulConfig {

    @Bean
    public Consul consul(@Value("${server.port:8080}") int port, @Value("${service.name}") String serviceName) {
        Consul consul = Consul.builder().build();
        AgentClient agentClient = consul.agentClient();
        agentClient.register(port, 3L, serviceName, serviceName);
        new Thread(() -> {
            try {
                while (true) {
                    Thread.sleep(3000);
                    agentClient.pass(serviceName);
                }
            } catch (Exception ignored) {
            }
        }).start();
        return consul;
    }
}

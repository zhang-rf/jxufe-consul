package cn.jxufe;

import com.orbitz.consul.AgentClient;
import com.orbitz.consul.Consul;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConsulConfig {

    @Bean
    public Consul consul() {
        Consul consul = Consul.builder().build();
        AgentClient agentClient = consul.agentClient();
        agentClient.register(4399, 3L, "center", "center");
        new Thread(() -> {
            try {
                while (true) {
                    Thread.sleep(3000);
                    agentClient.pass("center");
                }
            } catch (Exception ignored) {
            }
        }).start();
        return consul;
    }
}

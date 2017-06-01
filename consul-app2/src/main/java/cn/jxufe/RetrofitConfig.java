package cn.jxufe;

import com.orbitz.consul.Consul;
import com.orbitz.consul.model.health.ServiceHealth;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Configuration
public class RetrofitConfig {

    @Bean
    public Demo demo() {
        Consul consul = Consul.builder().build();
        List<ServiceHealth> healthList = consul.healthClient().getHealthyServiceInstances("center").getResponse();
        ServiceHealth health = healthList.get(ThreadLocalRandom.current().nextInt(healthList.size()));
        return new Retrofit.Builder()
                .addConverterFactory(ScalarsConverterFactory.create())
                .baseUrl("http://" + health.getNode().getAddress() + ":" + health.getService().getPort() + "/app1/").build()
                .create(Demo.class);
    }
}

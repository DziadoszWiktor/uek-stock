package pl.wiktordziadosz.uekstock.payment;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@Data
@ConfigurationProperties(prefix = "payu")
public class PayUConfiguration {

    String merchantPosId;
    String apiUrl;

    @Bean
    PayU createPayUClient() {
        return new PayU(
                new RestTemplate(),
                new PayUApiConfiguration(merchantPosId, apiUrl)
        );
    }
}

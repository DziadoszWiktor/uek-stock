package pl.wiktordziadosz.uekstock.payment;

import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class PayUTest {

    @Test
    void itAllowsRegisterPaymentViaPayUClient() {
        RegisterPaymentRequest request = thereIsExampleRequest();
        PayU payU = thereIsPayUClient();

        RegisterPaymentResponse response = payU.handle(request);

        assertTrue(response.isSuccess());
        assertNotNull(response.getOrderId());
        assertNotNull(response.getRedirectUri());
    }

    private PayU thereIsPayUClient() {
        return new PayU(new RestTemplate(), new PayUApiConfiguration("300746", "https://secure.snd.payu.com"));
    }

    private RegisterPaymentRequest thereIsExampleRequest() {
        return RegisterPaymentRequest.builder()
                .notifyUrl("https://your.eshop.com/notify")
                .customerIp("127.0.0.1")
                .description("https://your.eshop.com/notify")
                .currencyCode("PLN")
                .totalAmount(BigDecimal.valueOf(10.10).multiply(BigDecimal.valueOf(100)).intValue())
                .extOrderId(UUID.randomUUID().toString())
                .buyer(new RegisterPaymentRequest.Buyer("john.doe@example.com", "john", "doe"))
                .products(Collections.emptyList())
                .build();
    }
}

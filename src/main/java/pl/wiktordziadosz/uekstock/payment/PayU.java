package pl.wiktordziadosz.uekstock.payment;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class PayU {
    private final RestTemplate http;
    private final PayUApiConfiguration payUApiConfiguration;

    public PayU(RestTemplate restTemplate, PayUApiConfiguration payUApiConfiguration) {
        this.http = restTemplate;
        this.payUApiConfiguration = payUApiConfiguration;
    }

    public RegisterPaymentResponse handle(RegisterPaymentRequest request) {
        request.setMerchantPosId(payUApiConfiguration.getMerchantPosId());
        request.setNotifyUrl("https://your.eshop.com/notify");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", getAuthToken());

        HttpEntity<RegisterPaymentRequest> httpRequest = new HttpEntity<>(
                request,
                headers
        );

        ResponseEntity<RegisterPaymentResponse> response = http.postForEntity(getUrl("/api/v2_1/orders"), httpRequest, RegisterPaymentResponse.class);

        return response.getBody();
    }

    private String getUrl(String uri) {
        return String.format("%s%s", payUApiConfiguration.getApiUrl(), uri);
    }

    private String getAuthToken() {
        return "Bearer d9a4536e-62ba-4f60-8017-6053211d3f47";
    }
}

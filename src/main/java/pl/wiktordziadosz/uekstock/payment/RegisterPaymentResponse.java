package pl.wiktordziadosz.uekstock.payment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class RegisterPaymentResponse {
    public static final String STATUS_CODE_SUCCESS = "SUCCESS";

    private Status status;
    private String redirectUri;
    private String orderId;
    private String extOrderId;

    public boolean isSuccess() {
        return status.statusCode.equals(STATUS_CODE_SUCCESS);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class Status {
        private String statusCode;
    }
}

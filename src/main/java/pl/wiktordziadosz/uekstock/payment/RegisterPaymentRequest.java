package pl.wiktordziadosz.uekstock.payment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RegisterPaymentRequest {
    /*
{
    "notifyUrl": "https://your.eshop.com/notify",
    "customerIp": "127.0.0.1",
    "merchantPosId": "145227",
    "description": "RTV market",
    "currencyCode": "PLN",
    "totalAmount": "15000",
    "extOrderId":"9wahve9x0li71lv818mumt",
    "buyer": {
        "email": "john.doe@example.com",
        "phone": "654111654",
        "firstName": "John",
        "lastName": "Doe"
    },
    "products": [
        {
            "name": "Wireless Mouse for Laptop",
            "unitPrice": "15000",
            "quantity": "1"
        }
    ]
}
     */
    private String notifyUrl;
    private String customerIp;
    private String merchantPosId;
    private String description;
    private String currencyCode;
    private Integer totalAmount;
    private String extOrderId;
    private Buyer buyer;
    private List<Product> products;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Buyer {
        private String email;
        private String firstName;
        private String lastName;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Product {
        private String name;
        private Integer unitPrice;
        private Integer quantity;
    }
}

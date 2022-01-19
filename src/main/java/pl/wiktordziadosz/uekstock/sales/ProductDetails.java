package pl.wiktordziadosz.uekstock.sales;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class ProductDetails {
    private String id;
    private BigDecimal price;
}

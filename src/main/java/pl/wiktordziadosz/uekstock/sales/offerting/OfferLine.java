package pl.wiktordziadosz.uekstock.sales.offerting;

import java.math.BigDecimal;

public class OfferLine {
    private final String productId;
    private final int quantity;
    private final BigDecimal price;

    public OfferLine(String productId, int quantity, BigDecimal price) {

        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }

    public BigDecimal getTotal() {
        return price.multiply(BigDecimal.valueOf(quantity));
    }
}

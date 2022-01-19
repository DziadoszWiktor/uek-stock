package pl.wiktordziadosz.uekstock.sales.offerting;

import java.math.BigDecimal;
import java.util.List;

public class Offer {

    private BigDecimal total;
    private List<OfferLine> offerLines;

    private Offer(BigDecimal total, List<OfferLine> itemCount) {
        this.total = total;
        this.offerLines = itemCount;
    }
    public static Offer of(BigDecimal total, List<OfferLine> itemsCount) {
        return new Offer(total, itemsCount);
    }

    public BigDecimal getTotal() {
        return total;
    }

    public int getLinesCount() {
        return offerLines.size();
    }
}

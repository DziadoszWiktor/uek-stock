package pl.wiktordziadosz.uekstock.sales.offerting;

import pl.wiktordziadosz.uekstock.sales.Basket;
import pl.wiktordziadosz.uekstock.sales.BasketItem;
import pl.wiktordziadosz.uekstock.sales.ProductDetailsProvider;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class OfferMaker {
    private ProductDetailsProvider productDetailsProvider;

    public OfferMaker(ProductDetailsProvider productDetailsProvider) {
        this.productDetailsProvider = productDetailsProvider;
    }

    public Offer makeAnOffer(Basket basket) {
        List<OfferLine> lines = basket.getBasketItems()
                .stream()
                .map(this::createOfferLine)
                .collect(Collectors.toList());

        BigDecimal offerTotal = lines
                .stream()
                .map(offerLine -> offerLine.getTotal())
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);

        return Offer.of(offerTotal, lines);

    }

    private OfferLine createOfferLine(BasketItem basketItem) {
        return new OfferLine(basketItem.getProductId(), basketItem.getQuantity(), productDetailsProvider.getProductDetails(basketItem.getProductId()).getPrice());
    }
}

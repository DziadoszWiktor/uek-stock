package pl.dziadoszwiktor.stock.productcatalog;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class ProductCatalogFacade {

    ImagesStorage imagesStorage;

    public ProductCatalogFacade() {
        this.imagesStorage = new ImagesStorage();
    }

    public String addPicture(String title, BigDecimal price, List<String> keywords, String filePath) {
        Image image = new Image(UUID.randomUUID(), title, price, keywords, filePath);
        imagesStorage.save(image);
        return image.getId();
    }

    public boolean isProductExists(String id) {
        return imagesStorage.load(id).isPresent();
    }
}
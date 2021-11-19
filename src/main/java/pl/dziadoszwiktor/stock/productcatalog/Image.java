package pl.dziadoszwiktor.stock.productcatalog;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class Image {
    private final String id;
    private final String title;
    private final BigDecimal price;
    private final List<String> keywords;
    private final String filePath;

    public Image(UUID id, String title, BigDecimal price, List<String> keywords, String filePath) {
        this.id = id.toString();
        this.title = title;
        this.price = price;
        this.keywords = keywords;
        this.filePath = filePath;
    }

    public String getTitle() {
        return title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getId() {
        return id;
    }
}

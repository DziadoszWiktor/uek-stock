package pl.wiktordziadosz.uekstock.productcatalog;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    private String id;
    private String title;
    private BigDecimal price;
    @Transient
    private List<String> keywords;
    private String mediaPath;
    private boolean published;

    public Product(UUID id, String title, BigDecimal price, List<String> keywords, String mediaPath) {
        this.id = id.toString();
        this.title = title;
        this.price = price;
        this.keywords = keywords;
        this.mediaPath = mediaPath;
        this.published = false;
    }

    public void publish() {
        this.published = true;
    }
}


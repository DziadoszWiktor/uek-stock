package pl.dziadoszwiktor.stock.productcatalog;

import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Image, String> {
}
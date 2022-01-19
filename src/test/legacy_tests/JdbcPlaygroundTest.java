package pl.wiktordziadosz.uekstock;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest
public class JdbcPlaygroundTest {
    @Autowired
    JdbcTemplate jdbcTemplate;


    @BeforeEach
    void clearDb() {
        jdbcTemplate.execute("DROP TABLE `product_catalog__products` IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE `product_catalog__products` (" +
                "`id` varchar(100) NOT NULL," +
                "description varchar(255)," +
                "PRIMARY KEY (`id`)" +
                ");");
    }

    @Test
    void itCountDummyProduct() {
        int productsCount = 1;

        assertEquals(1 ,productsCount);
    }

    @Test
    void itCountProduct() {
        int productsCount = jdbcTemplate
                .queryForObject("select 1", Integer.class);
        assertEquals(1 ,productsCount);
    }

    @Test
    void itCountsRealProducts() {


        int productsCount = jdbcTemplate
                .queryForObject("select count(*) from `product_catalog__products`", Integer.class);
        assertEquals(0 ,productsCount);
    }

    @Test
    void itAddsRealProducts() {
        jdbcTemplate.execute("INSERT INTO `product_catalog__products` (`id`, `description`) " +
                "values " +
                "('product1', 'desc 1')," +
                "('product2', 'desc 2')" +
                ";");


        int productsCount = jdbcTemplate
                .queryForObject("select count(*) from `product_catalog__products`", Integer.class);
        assertEquals(2 ,productsCount);
    }
}

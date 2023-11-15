package bj.nazonhou.quickstart.dao;

import bj.nazonhou.quickstart.domain.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDaoImpl implements ProductDao {
    private final JdbcTemplate jdbcTemplate;

    public ProductDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void create(Product product) {
        jdbcTemplate.update("INSERT INTO products (id, name, order_id) VALUES (?, ?, ?)", product.getId(),
                product.getName(), product.getOrderId());
    }

    public Optional<Product> findOne(long l) {
        List<Product> products = jdbcTemplate.query(
                "SELECT id, name, order_id FROM products WHERE id = ?",
                new ProductRowMapper(),
                l);

        return products.stream().findFirst();
    }

    public static class ProductRowMapper implements RowMapper<Product> {

        @Override
        @Nullable
        public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Product.builder()
                    .id(rs.getLong("id"))
                    .name(rs.getString("name"))
                    .orderId(rs.getLong("order_id"))
                    .build();
        }

    }

    public List<Product> find() {
        return jdbcTemplate.query("SELECT id, name, order_id FROM products", new ProductRowMapper());
    }
}

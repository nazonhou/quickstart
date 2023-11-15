package bj.nazonhou.quickstart.dao;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import bj.nazonhou.quickstart.dao.ProductDaoImpl.ProductRowMapper;
import bj.nazonhou.quickstart.domain.Product;

@ExtendWith(MockitoExtension.class)
public class ProductDaoImplTests {
  @Mock
  JdbcTemplate jdbcTemplate;

  @InjectMocks
  ProductDaoImpl productDaoImpl;

  @Test
  public void testThatCreateProductGeneratesCorrectSqlStatement() {
    Product product = new Product(1L, "Java, beginner to pro", 1L);

    productDaoImpl.create(product);

    verify(jdbcTemplate).update(
        eq("INSERT INTO products (id, name, order_id) VALUES (?, ?, ?)"), eq(product.getId()), eq(product.getName()),
        eq(product.getOrderId()));
  }

  @Test
  public void testThatFindOneProductGeneratesCorrectSqlStatement() {
    productDaoImpl.findOne(1L);

    verify(jdbcTemplate).query(
        eq("SELECT id, name, order_id FROM products WHERE id = ?"),
        ArgumentMatchers.<ProductRowMapper>any(),
        eq(1L));
  }

  @Test
  public void testThatFindProductsGeneratesCorrectSqlStatement() {
    productDaoImpl.find();

    verify(jdbcTemplate).query(
        eq("SELECT id, name, order_id FROM products"),
        ArgumentMatchers.<ProductRowMapper>any());
  }
}

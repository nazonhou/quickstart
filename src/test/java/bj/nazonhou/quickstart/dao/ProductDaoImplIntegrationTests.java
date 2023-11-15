package bj.nazonhou.quickstart.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import bj.nazonhou.quickstart.domain.Order;
import bj.nazonhou.quickstart.domain.Product;

@SpringBootTest
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class ProductDaoImplIntegrationTests {
  private final OrderDao orderDao;
  private final ProductDaoImpl productDaoImpl;

  @Autowired
  public ProductDaoImplIntegrationTests(OrderDao orderDao, ProductDaoImpl productDaoImpl) {
    this.orderDao = orderDao;
    this.productDaoImpl = productDaoImpl;
  }

  @Test
  public void testThatProductCanBeCreatedAndRecalled() {
    Order order = Order.builder().id(1L).description("Order Description").build();
    orderDao.create(order);

    Product product = new Product(1L, "Java, beginner to pro", order.getId());
    productDaoImpl.create(product);

    Optional<Product> result = productDaoImpl.findOne(product.getId());

    assertTrue(result.isPresent());
    assertEquals(product, result.get());
  }

  @Test
  void testThatMultipleProductsCanBeCreatedAndRecalled() {
    List<Product> products = new ArrayList<>();

    int size = new Random().nextInt(1, 11);

    for (long i = 1; i <= size; i++) {
      Order order = Order.builder().id(i).description("Description " + i).build();
      orderDao.create(order);
      Product product = new Product(i, "Product " + i, order.getId());
      productDaoImpl.create(product);

      products.add(product);
    }

    List<Product> result = productDaoImpl.find();

    products.forEach(product -> {
      assertTrue(result.contains(product));
    });
  }
}

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

@SpringBootTest
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class OrderDaoImplIntegrationTests {
  private OrderDaoImpl orderDaoImpl;

  @Autowired
  public OrderDaoImplIntegrationTests(OrderDaoImpl orderDaoImpl) {
    this.orderDaoImpl = orderDaoImpl;
  }

  @Test
  void testThatOrderCanBeCreatedAndRecalled() {
    Order order = Order.builder().id(1L).description("Order Description").build();

    orderDaoImpl.create(order);

    Optional<Order> result = orderDaoImpl.findOne(1L);

    assertTrue(result.isPresent());

    assertEquals(order, result.get());
  }

  @Test
  void testThatMultipleOrdersCanBeCreatedAndRecalled() {
    int size = new Random().nextInt(1, 11);
    List<Order> orders = new ArrayList<>();
    for (Long i = 1L; i <= size; i++) {
      Order order = Order.builder().id(i).description("Description " + i).build();
      orderDaoImpl.create(order);
      orders.add(order);
    }

    List<Order> result = orderDaoImpl.find();

    assertEquals(size, result.size());

    orders.forEach((order) -> {
      assertTrue(result.contains(order));
    });
  }
}

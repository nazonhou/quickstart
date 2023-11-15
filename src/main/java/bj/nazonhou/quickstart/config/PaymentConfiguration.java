package bj.nazonhou.quickstart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import bj.nazonhou.quickstart.services.OrderService;
import bj.nazonhou.quickstart.services.OrderServiceImpl;
import bj.nazonhou.quickstart.services.PaymentService;
import bj.nazonhou.quickstart.services.PaypalService;

// @Configuration
public class PaymentConfiguration {

  @Bean
  public PaymentService paymentService() {
    return new PaypalService();
  }

  @Bean
  public OrderService orderService(PaymentService paymentService) {
    return new OrderServiceImpl(paymentService);
  }
}

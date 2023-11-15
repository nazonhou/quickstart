package bj.nazonhou.quickstart.services;

import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {
  private PaymentService paymentService;

  public OrderServiceImpl(PaymentService paymentService) {
    this.paymentService = paymentService;
  }

  @Override
  public String order(String product, int amount) {
    return "Want to order " + product + ". " + paymentService.pay(amount);
  }
}

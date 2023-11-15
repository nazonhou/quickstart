package bj.nazonhou.quickstart.services;

import org.springframework.stereotype.Component;

@Component
public class PaypalService implements PaymentService {

  @Override
  public String pay(int amount) {
    return "Debit $" + amount + " from paypal account";
  }

}

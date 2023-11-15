package bj.nazonhou.quickstart.dao;

import bj.nazonhou.quickstart.domain.Order;

import java.util.List;
import java.util.Optional;

public interface OrderDao {
    public void create(Order order);

    Optional<Order> findOne(long l);

    public List<Order> find();
}

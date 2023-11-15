package bj.nazonhou.quickstart.dao;

import java.util.List;
import java.util.Optional;

import bj.nazonhou.quickstart.domain.Product;

public interface ProductDao {
  public void create(Product product);

  public Optional<Product> findOne(long l);

  public List<Product> find();
}

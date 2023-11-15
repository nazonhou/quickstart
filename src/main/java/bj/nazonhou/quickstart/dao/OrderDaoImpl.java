package bj.nazonhou.quickstart.dao;

import bj.nazonhou.quickstart.domain.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class OrderDaoImpl implements OrderDao {
    private final JdbcTemplate jdbcTemplate;

    public OrderDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void create(Order order) {
        jdbcTemplate.update("INSERT INTO orders (id, description) VALUES (?, ?)", order.getId(),
                order.getDescription());
    }

    @Override
    public Optional<Order> findOne(long l) {
        List<Order> orders = jdbcTemplate.query("SELECT id, description FROM orders WHERE id = ?", new OrderRowMapper(),
                l);
        return orders.stream().findFirst();
    }

    public static class OrderRowMapper implements RowMapper<Order> {

        @Override
        public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Order.builder().id(rs.getLong("id")).description(rs.getString("description")).build();
        }
    }

    public List<Order> find() {
        return jdbcTemplate.query("SELECT id, description FROM orders", new OrderRowMapper());
    }
}

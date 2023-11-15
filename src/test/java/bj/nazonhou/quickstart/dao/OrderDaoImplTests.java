package bj.nazonhou.quickstart.dao;

import bj.nazonhou.quickstart.dao.OrderDaoImpl.OrderRowMapper;
import bj.nazonhou.quickstart.domain.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class OrderDaoImplTests {
    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private OrderDaoImpl orderDaoImpl;

    @Test
    public void testThatCreateOrderGeneratesCorrectSqlStatement() {
        Order order = Order.builder().id(1L).description("Order Description").build();

        orderDaoImpl.create(order);

        verify(jdbcTemplate).update(eq("INSERT INTO orders (id, description) VALUES (?, ?)"), eq(order.getId()),
                eq(order.getDescription()));
    }

    @Test
    public void testThatFindOneOrderGeneratesCorrectSqlStatement() {
        orderDaoImpl.findOne(1L);

        verify(jdbcTemplate)
                .query(eq("SELECT id, description FROM orders WHERE id = ?"),
                        ArgumentMatchers.<OrderDaoImpl.OrderRowMapper>any(), eq(1L));
    }

    @Test
    public void testThatFindOrderGeneratesCorrectSqlStatement() {
        orderDaoImpl.find();

        verify(jdbcTemplate)
                .query(
                        eq("SELECT id, description FROM orders"),
                        ArgumentMatchers.<OrderRowMapper>any());
    }
}

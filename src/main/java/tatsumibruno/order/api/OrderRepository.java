package tatsumibruno.order.api;

import org.springframework.data.repository.Repository;

import java.util.Optional;
import java.util.UUID;

public interface OrderRepository extends Repository<Order, Long> {

    void save(Order order);
    Optional<Order> findByCode(UUID code);
}

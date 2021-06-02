package tatsumibruno.order.api;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.Repository;

import java.util.Optional;
import java.util.UUID;

public interface OrderRepository extends Repository<Order, Long> {

    void save(Order order);
    @EntityGraph(attributePaths = "history", type = EntityGraph.EntityGraphType.FETCH)
    Optional<Order> findByCode(UUID code);
}

package tatsumibruno.order.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderStatusChangesListener {

    private final OrderRepository orderRepository;

    @Transactional
    @StreamListener(OrderChannels.ORDERS_CHANGES)
    public void listen(OrderStatusChange statusChange) {
        log.info("Receiving status update on order {}. New status: {}", statusChange.getCode(), statusChange.getStatus());
        orderRepository.findByCode(statusChange.getCode())
                .ifPresentOrElse(order -> {
                    order.update(statusChange.getStatus(), statusChange.getTimestamp());
                    log.info("Order {} updated successfully with status {}", statusChange.getCode(), statusChange.getStatus());
                }, () -> log.warn("Not found order {}", statusChange.getCode()));
    }
}

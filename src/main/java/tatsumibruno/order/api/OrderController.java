package tatsumibruno.order.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(path = "/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderRepository orderRepository;
    private final OrderChannels orderChannels;

    @PostMapping
    @Transactional
    public ResponseEntity<OrderDTO> post(@RequestBody @Valid CreateOrderRequest orderRequest) {
        log.info("Receiving new order: {}", orderRequest);
        final OrderCustomer orderCustomer = orderRequest.toOrderCustomer();
        final Order order = Order.from(orderCustomer);
        orderRepository.save(order);
        final OrderDTO responseData = OrderDTO.from(order);
        orderChannels.ordersCreated().send(MessageBuilder.withPayload(responseData).build());
        return ResponseEntity.ok(responseData);
    }

    @GetMapping(path = "/{code}")
    public ResponseEntity<OrderDTO> get(@PathVariable UUID code) {
        log.info("Searching for order {}", code);
        return orderRepository.findByCode(code)
                .map(order -> ResponseEntity.ok(OrderDTO.from(order)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}

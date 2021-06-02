package tatsumibruno.order.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
        final OrderCustomer customer = order.getCustomer();
        final OrderDTO responseData = new OrderDTO(order.getCode(), order.getStatus(), order.getCreatedAt(),
                customer.getName(), customer.getEmail(), customer.getDeliveryAddress());
        orderChannels.ordersCreated().send(MessageBuilder.withPayload(responseData).build());
        return ResponseEntity.ok(responseData);
    }
}

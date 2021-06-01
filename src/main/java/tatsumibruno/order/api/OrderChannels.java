package tatsumibruno.order.api;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

interface OrderChannels {
    String ORDERS_CREATED = "orders-created";
    String ORDERS_CHANGES = "orders-changes";

    @Output(ORDERS_CREATED)
    MessageChannel ordersCreated();

    @Input(ORDERS_CHANGES)
    SubscribableChannel ordersChanges();
}

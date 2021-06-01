package tatsumibruno.order.api;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CreateOrderRequest {
    private String customerName;
    private String customerEmail;
    private String deliveryAddress;

    public OrderCustomer toOrderCustomer() {
        return new OrderCustomer(customerName, customerEmail, deliveryAddress);
    }
}

package tatsumibruno.order.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@ToString
@AllArgsConstructor
public class OrderDTO {
    private UUID code;
    private OrderStatus status;
    private ZonedDateTime createdAt;
    private String customerName;
    private String customerEmail;
    private String deliveryAddress;
}

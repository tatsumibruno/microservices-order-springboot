package tatsumibruno.order.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import tatsumibruno.order.api.util.Constants;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@ToString
@AllArgsConstructor
public class OrderDTO {
    private UUID code;
    private OrderStatus status;
    @JsonFormat(pattern = Constants.ISO_DATE_TIME)
    private ZonedDateTime createdAt;
    private String customerName;
    private String customerEmail;
    private String deliveryAddress;
}

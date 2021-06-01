package tatsumibruno.order.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.ToString;
import tatsumibruno.order.api.util.Constants;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@ToString
public class OrderStatusChange {
    private UUID code;
    private OrderStatus status;
    @JsonFormat(pattern = Constants.ISO_DATE_TIME)
    private ZonedDateTime timestamp;
}

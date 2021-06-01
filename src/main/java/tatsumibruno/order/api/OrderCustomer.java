package tatsumibruno.order.api;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Embeddable
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class OrderCustomer {
    @Column(name = "customer_name")
    String name;
    @Column(name = "customer_email")
    String email;
    @Column(name = "delivery_address")
    String deliveryAddress;
}

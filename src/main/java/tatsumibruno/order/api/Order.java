package tatsumibruno.order.api;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class Order {

    @Id
    @SequenceGenerator(name = "orders_seq", sequenceName = "orders_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orders_seq")
    private Long id;
    @Getter
    @NotNull
    private UUID code;
    @Getter
    @NotNull
    private ZonedDateTime createdAt = ZonedDateTime.now();
    @Getter
    @NotNull
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    @Getter
    @Embedded
    private OrderCustomer customer;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderHistory> history = new ArrayList<>();

    private Order(@NonNull UUID code, @NonNull OrderCustomer customer) {
        this.code = code;
        this.customer = customer;
    }

    public static Order from(@NonNull OrderCustomer customer) {
        final Order order = new Order(UUID.randomUUID(), customer);
        order.update(OrderStatus.OPENED, ZonedDateTime.now());
        return order;
    }

    public void update(@NonNull OrderStatus status, @NonNull ZonedDateTime updatedAt) {
        this.status = status;
        this.history.add(new OrderHistory(status, this, updatedAt));
    }
}

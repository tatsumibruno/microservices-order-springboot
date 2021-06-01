package tatsumibruno.order.api;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@Entity
@Table(name = "orders_history")
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class OrderHistory {

    @Id
    @SequenceGenerator(name = "orders_history_seq", sequenceName = "orders_history_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orders_history_seq")
    private Long id;
    @NotNull
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    @NotNull
    private ZonedDateTime createdAt;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orders_id")
    private Order order;

    OrderHistory(@NonNull OrderStatus status, @NonNull Order order, @NonNull ZonedDateTime createdAt) {
        this.status = status;
        this.order = order;
        this.createdAt = createdAt;
    }
}

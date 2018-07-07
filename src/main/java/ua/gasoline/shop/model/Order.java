package ua.gasoline.shop.model;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "order_time"}, name = "orders_unique_user_order_time_idx")})
public class Order extends AbstractBaseEntity {

    @Column(name = "order_time", nullable = false)
    @NotNull
    private LocalDateTime orderTime;

    @Column(name = "order_address", nullable = false)
    @NotBlank
    @Size(min = 5, max = 20)
    private String orderAddress;

    @Column(name = "amount", nullable = false)
    @NotBlank
    @Digits(integer=13, fraction=2)
    private BigDecimal amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull
    private User user;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "order_status", joinColumns = @JoinColumn(name = "order_id"))
    @Column(name = "status")
    @ElementCollection(fetch = FetchType.EAGER)
    private Status status;

    public Order() {
    }

    public Order(Integer id, LocalDateTime orderTime, String orderAddress, BigDecimal amount, User user, Status status) {
        super(id);
        this.orderTime = orderTime;
        this.orderAddress = orderAddress;
        this.amount = amount;
        this.user = user;
        this.status = status;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }

    public String getOrderAddress() {
        return orderAddress;
    }

    public void setOrderAddress(String orderAddress) {
        this.orderAddress = orderAddress;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderTime=" + orderTime +
                ", orderAddress='" + orderAddress + '\'' +
                ", amount=" + amount +
                ", user=" + user +
                ", status=" + status +
                '}';
    }
}

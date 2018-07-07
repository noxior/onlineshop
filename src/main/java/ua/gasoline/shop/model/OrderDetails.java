package ua.gasoline.shop.model;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
@Table(name = "order_details", uniqueConstraints = {@UniqueConstraint(columnNames = {"order_id", "fuel_id"}, name = "order_details_order_name_idx")})
public class OrderDetails extends AbstractBaseEntity {

    @Column(name = "amount", nullable = false)
    @NotBlank
    @Digits(integer=13, fraction=2)
    private BigDecimal amount;

    @Column(name = "last_name", nullable = false)
    @NotBlank
    @Size(min = 5, max = 20)
    private int quantity;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fuel_id", nullable = false)
    @NotNull
    private Fuel fuel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    @NotNull
    private Order order;

    public OrderDetails() {
    }

    public OrderDetails(Integer id, BigDecimal amount, int quantity, Fuel fuel, Order order) {
        super(id);
        this.amount = amount;
        this.quantity = quantity;
        this.fuel = fuel;
        this.order = order;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Fuel getFuel() {
        return fuel;
    }

    public void setFuel(Fuel fuel) {
        this.fuel = fuel;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "OrderDetails{" +
                "id=" + id +
                ", amount=" + amount +
                ", quantity=" + quantity +
                ", fuel=" + fuel +
                ", order=" + order +
                '}';
    }
}

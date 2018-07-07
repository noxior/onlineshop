package ua.gasoline.shop.model;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "fuel_stations", uniqueConstraints = {@UniqueConstraint(columnNames = "name", name = "fuels_unique_name_idx")})
public class Fuel extends AbstractNamedEntity {

    @Column(name = "price", nullable = false)
    @NotBlank
    @Digits(integer=13, fraction=2)
    private BigDecimal price;

    @Column(name = "enabled", nullable = false, columnDefinition = "bool default true")
    private boolean enabled = true;

    @ManyToMany(mappedBy="fuels")
    private Set<FuelStation> fuelStations = new HashSet<>();

    public Fuel(Integer id, String name, BigDecimal price, boolean enabled) {
        super(id, name);
        this.price = price;
        this.enabled = enabled;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<FuelStation> getFuelStations() {
        return fuelStations;
    }

    public void setFuelStations(Set<FuelStation> fuelStations) {
        this.fuelStations = fuelStations;
    }

    @Override
    public String toString() {
        return "Fuel{" +
                "id=" + id +
                ", name='" + name +
                ", price=" + price +
                ", enabled=" + enabled +
                ", fuelStations=" + fuelStations +
                '}';
    }
}

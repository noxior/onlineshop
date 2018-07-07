package ua.gasoline.shop.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "fuel_stations", uniqueConstraints = {@UniqueConstraint(columnNames = "order_time", name = "fuel_station_unique_name_idx")})
public class FuelStation extends AbstractNamedEntity {

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE})
    @JoinTable(name = "fuel_stations_fuels",
            joinColumns = @JoinColumn(name = "fuel_id"),
            inverseJoinColumns = @JoinColumn(name = "fuel_station_id"))
    private Set<Fuel> fuels = new HashSet<>();

    public FuelStation() {
    }

    public FuelStation(Integer id, String name) {
        super(id, name);
    }

    public Set<Fuel> getFuels() {
        return fuels;
    }

    public void setFuels(Set<Fuel> fuels) {
        this.fuels = fuels;
    }

    @Override
    public String toString() {
        return "FuelStation{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", fuels=" + fuels +
                '}';
    }
}

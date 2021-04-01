package io.github.tryolingo;

import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "car_model")
public class CarModel {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Integer year;
    private String sku;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CarMaker maker;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public CarMaker getMaker() {
        return maker;
    }

    public void setMaker(CarMaker maker) {
        this.maker = maker;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarModel carModel = (CarModel) o;
        return Objects.equals(id, carModel.id) &&
                Objects.equals(name, carModel.name) &&
                Objects.equals(year, carModel.year) &&
                Objects.equals(sku, carModel.sku) &&
                Objects.equals(maker, carModel.maker);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, year, sku, maker);
    }
}

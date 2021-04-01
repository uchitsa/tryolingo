package io.github.tryolingo;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="car_maker")
public class CarMaker {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "maker", orphanRemoval = true,cascade = CascadeType.ALL)
    private List<CarModel> models;

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

    public List<CarModel> getModels() {
        return models;
    }

    public void setModels(List<CarModel> models) {
        this.models = models;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarMaker carMaker = (CarMaker) o;
        return id == carMaker.id &&
                Objects.equals(name, carMaker.name) &&
                Objects.equals(models, carMaker.models);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, models);
    }
}

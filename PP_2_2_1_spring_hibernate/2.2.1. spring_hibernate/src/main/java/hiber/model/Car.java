package hiber.model;

import javax.persistence.*;

@Entity
@Table(name="cars")
public class Car {

    @Column(name="model")
    private String model;

    @Column(name="series")
    private int series;

    @Id
    @Column(name="id")
    private Long id;

    public Car() {
    }

    public Car(String model, int series, Long id) {
        this.model = model;
        this.series = series;
        this.id = id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }
}

package cat.itacademy.s04.t02.n02.fruit.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
public class Provider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String country;

    @OneToMany(mappedBy = "provider", cascade = CascadeType.ALL)
    private List<Fruit> fruits;

    public Provider() {}

    public Provider(String name, String country) {
        this.name = name;
        this.country = country;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
    public List<Fruit> getFruits() { return fruits; }
    public void setFruits(List<Fruit> fruits) { this.fruits = fruits; }
}

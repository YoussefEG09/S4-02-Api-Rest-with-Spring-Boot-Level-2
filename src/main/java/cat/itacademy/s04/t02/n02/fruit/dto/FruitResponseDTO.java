package cat.itacademy.s04.t02.n02.fruit.dto;

public class FruitResponseDTO {

    private Long id;
    private String name;
    private int weightInKg;


    public FruitResponseDTO() {
    }


    public FruitResponseDTO(Long id, String name, int weightInKg) {
        this.id = id;
        this.name = name;
        this.weightInKg = weightInKg;
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getWeightInKg() {
        return weightInKg;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWeightInKg(int weightInKg) {
        this.weightInKg = weightInKg;
    }
}

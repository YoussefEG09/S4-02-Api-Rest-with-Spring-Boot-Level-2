package cat.itacademy.s04.t02.n02.fruit.dto;

public class ProviderResponseDTO {

    private Long id;
    private String name;
    private String country;

    public ProviderResponseDTO() {}

    public ProviderResponseDTO(Long id, String name, String country) {
        this.id = id;
        this.name = name;
        this.country = country;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
}

package cat.itacademy.s04.t02.n02.fruit.dto;

public class FruitResponseDTO {

    private Long id;
    private String name;
    private int weightInKg;

    private Long providerId;
    private String providerName;
    private String providerCountry;

    public FruitResponseDTO() {
    }


    public FruitResponseDTO(Long id, String name, int weightInKg,
                            Long providerId, String providerName, String providerCountry) {
        this.id = id;
        this.name = name;
        this.weightInKg = weightInKg;
        this.providerId = providerId;
        this.providerName = providerName;
        this.providerCountry = providerCountry;

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

    public Long getProviderId() {return providerId;}

    public void setProviderId(Long providerId) {this.providerId = providerId;}

    public String getProviderName() {return providerName;}

    public void setProviderName(String providerName) {this.providerName = providerName;}

    public String getProviderCountry() {return providerCountry;}

    public void setProviderCountry(String providerCountry) {this.providerCountry = providerCountry;}
}

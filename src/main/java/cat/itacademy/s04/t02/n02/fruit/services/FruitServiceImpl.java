package cat.itacademy.s04.t02.n02.fruit.services;

import cat.itacademy.s04.t02.n02.fruit.dto.FruitDTO;
import cat.itacademy.s04.t02.n02.fruit.dto.FruitResponseDTO;
import cat.itacademy.s04.t02.n02.fruit.exception.FruitNotFoundException;
import cat.itacademy.s04.t02.n02.fruit.exception.ProviderNotFoundException;
import cat.itacademy.s04.t02.n02.fruit.model.Fruit;
import cat.itacademy.s04.t02.n02.fruit.model.Provider;
import cat.itacademy.s04.t02.n02.fruit.repository.FruitRepository;
import cat.itacademy.s04.t02.n02.fruit.repository.ProviderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FruitServiceImpl implements FruitService {

    private final FruitRepository fruitRepository;
    private final ProviderRepository providerRepository;

    public FruitServiceImpl(FruitRepository fruitRepository, ProviderRepository providerRepository) {
        this.fruitRepository = fruitRepository;
        this.providerRepository = providerRepository;
    }



    @Override
    public FruitResponseDTO createFruit(FruitDTO fruitDTO) {
        Provider provider = providerRepository.findById(fruitDTO.getProviderId())
                .orElseThrow(() -> new ProviderNotFoundException(fruitDTO.getProviderId()));
        Fruit fruit = new Fruit(fruitDTO.getName(), fruitDTO.getWeightInKg(), provider);
        Fruit saved = fruitRepository.save(fruit);
        return mapToDTO(saved);
    }

    @Override
    public FruitResponseDTO updateFruit(Long id, FruitDTO fruitDTO) {
        Fruit fruit = fruitRepository.findById(id)
                .orElseThrow(() -> new FruitNotFoundException(id));
        fruit.setName(fruitDTO.getName());
        fruit.setWeightInKg(fruitDTO.getWeightInKg());
        Fruit updated = fruitRepository.save(fruit);
        return mapToDTO(updated);
    }

    @Override
    public void removeFruit(Long id) {
        if (!fruitRepository.existsById(id)) {
            throw new FruitNotFoundException(id);
        }
        fruitRepository.deleteById(id);
    }

    @Override
    public FruitResponseDTO getFruitById(Long id) {
        Fruit fruit = fruitRepository.findById(id)
                .orElseThrow(() -> new FruitNotFoundException(id));
        return mapToDTO(fruit);
    }

    @Override
    public List<FruitResponseDTO> getAllFruits() {
        return fruitRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<FruitResponseDTO> getFruitsByProvider(Long providerId) {
        Provider provider = providerRepository.findById(providerId)
                .orElseThrow(() -> new RuntimeException("Provider not found"));
        return fruitRepository.findByProvider(provider).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }


    private FruitResponseDTO mapToDTO(Fruit fruit) {
        FruitResponseDTO dto = new FruitResponseDTO();
        dto.setId(fruit.getId());
        dto.setName(fruit.getName());
        dto.setWeightInKg(fruit.getWeightInKg());
        dto.setProviderName(fruit.getProvider().getName());
        dto.setProviderCountry(fruit.getProvider().getCountry());
        return dto;
    }
}

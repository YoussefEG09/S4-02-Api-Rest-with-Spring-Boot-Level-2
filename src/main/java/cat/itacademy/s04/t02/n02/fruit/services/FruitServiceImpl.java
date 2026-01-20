package cat.itacademy.s04.t02.n02.fruit.services;

import cat.itacademy.s04.t02.n02.fruit.dto.FruitDTO;
import cat.itacademy.s04.t02.n02.fruit.dto.FruitResponseDTO;
import cat.itacademy.s04.t02.n02.fruit.exception.FruitNotFoundException;
import cat.itacademy.s04.t02.n02.fruit.model.Fruit;
import cat.itacademy.s04.t02.n02.fruit.model.Provider;
import cat.itacademy.s04.t02.n02.fruit.repository.FruitRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FruitServiceImpl implements FruitService {

    private final FruitRepository fruitRepository;

    public FruitServiceImpl(FruitRepository fruitRepository) {
        this.fruitRepository = fruitRepository;
    }

    @Override
    public FruitResponseDTO createFruit(FruitDTO fruitDTO) {
        Fruit fruit = new Fruit(fruitDTO.getName(), fruitDTO.getWeightInKg());
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
        return new FruitResponseDTO(fruit.getId(), fruit.getName(), fruit.getWeightInKg());
    }
}

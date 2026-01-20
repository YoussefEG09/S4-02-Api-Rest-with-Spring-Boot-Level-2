package cat.itacademy.s04.t02.n02.fruit.services;

import cat.itacademy.s04.t02.n02.fruit.dto.ProviderDTO;
import cat.itacademy.s04.t02.n02.fruit.dto.ProviderResponseDTO;
import cat.itacademy.s04.t02.n02.fruit.model.Provider;
import cat.itacademy.s04.t02.n02.fruit.repository.ProviderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProviderServiceImpl implements ProviderService {

    private final ProviderRepository providerRepository;

    public ProviderServiceImpl(ProviderRepository providerRepository) {
        this.providerRepository = providerRepository;
    }

    private ProviderResponseDTO mapToDTO(Provider provider) {
        return new ProviderResponseDTO(provider.getId(), provider.getName(), provider.getCountry());
    }

    @Override
    public ProviderResponseDTO createProvider(ProviderDTO providerDTO) {

        if (providerRepository.findByName(providerDTO.getName()).isPresent()) {
            throw new RuntimeException("Provider with this name already exists");
        }

        Provider provider = new Provider(providerDTO.getName(), providerDTO.getCountry());
        Provider saved = providerRepository.save(provider);
        return mapToDTO(saved);
    }

    @Override
    public ProviderResponseDTO updateProvider(Long id, ProviderDTO providerDTO) {
        Provider provider = providerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Provider not found"));


        providerRepository.findByName(providerDTO.getName())
                .filter(p -> !p.getId().equals(id))
                .ifPresent(p -> { throw new RuntimeException("Provider with this name already exists"); });

        provider.setName(providerDTO.getName());
        provider.setCountry(providerDTO.getCountry());

        return mapToDTO(providerRepository.save(provider));
    }

    @Override
    public void deleteProvider(Long id) {
        Provider provider = providerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Provider not found"));

        if (provider.getFruits() != null && !provider.getFruits().isEmpty()) {
            throw new RuntimeException("Cannot delete provider with associated fruits");
        }

        providerRepository.deleteById(id);
    }

    @Override
    public ProviderResponseDTO getProviderById(Long id) {
        Provider provider = providerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Provider not found"));
        return mapToDTO(provider);
    }

    @Override
    public List<ProviderResponseDTO> getAllProviders() {
        return providerRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
}

package cat.itacademy.s04.t02.n02.fruit.services;

import cat.itacademy.s04.t02.n02.fruit.dto.ProviderDTO;
import cat.itacademy.s04.t02.n02.fruit.dto.ProviderResponseDTO;

import java.util.List;

public interface ProviderService {
    ProviderResponseDTO createProvider(ProviderDTO providerDTO);
    ProviderResponseDTO updateProvider(Long id, ProviderDTO providerDTO);
    void deleteProvider(Long id);
    ProviderResponseDTO getProviderById(Long id);
    List<ProviderResponseDTO> getAllProviders();
}

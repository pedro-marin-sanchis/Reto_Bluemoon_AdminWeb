package com.uguinformatica.bluemoon_adminweb.service.silvertype;

import com.uguinformatica.bluemoon_adminweb.model.SilverType;
import com.uguinformatica.bluemoon_adminweb.service.APIConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SilverTypeServiceImpl implements ISilverTypeService{

    private final RestTemplate restTemplate;

    @Autowired
    public SilverTypeServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Optional<List<SilverType>> getAllSilverTypes() {
        ParameterizedTypeReference<List<SilverType>> responseType = new ParameterizedTypeReference<List<SilverType>>() {};
        Optional<List<SilverType>> silverTypes = Optional.ofNullable(restTemplate.exchange(APIConstants.API_URL + "/silver-types/", HttpMethod.GET, null, responseType).getBody());
        if (silverTypes.isPresent()) {
            List<SilverType> filteredAndSortedSilverTypes = silverTypes.get().stream()
                    .filter(silverType -> !silverType.getDisabled())
                    .sorted(Comparator.comparingLong(SilverType::getId))
                    .collect(Collectors.toList());
            return Optional.of(filteredAndSortedSilverTypes);
        }
        return silverTypes;
    }

    @Override
    public Optional<SilverType> getSilverTypeByID(long id) {
        return Optional.ofNullable(restTemplate.getForEntity(APIConstants.API_URL + "/silver-types/" + id, SilverType.class).getBody());
    }

    @Override
    public void updateSilverType(SilverType silverType) {
        HttpEntity<SilverType> requestEntity = new HttpEntity<>(silverType);
        restTemplate.put(APIConstants.API_URL + "/silver-types/" + silverType.getId(), requestEntity);
    }

    @Override
    public void createSilverType(SilverType silverType) {
        HttpEntity<SilverType> requestEntity = new HttpEntity<>(silverType);
        restTemplate.exchange(
                APIConstants.API_URL + "/silver-types/",
                HttpMethod.POST,
                requestEntity,
                SilverType.class
        );
    }

    @Override
    public void deleteSilverType(long id) {
        restTemplate.delete(APIConstants.API_URL + "/silver-types/" + id);
    }

}

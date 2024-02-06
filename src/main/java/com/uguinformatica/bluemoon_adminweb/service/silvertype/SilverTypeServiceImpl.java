package com.uguinformatica.bluemoon_adminweb.service.silvertype;

import com.uguinformatica.bluemoon_adminweb.model.SilverType;
import com.uguinformatica.bluemoon_adminweb.service.APIConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
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
    public Optional<List<SilverType>> getAllSilverTypes(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        ParameterizedTypeReference<List<SilverType>> responseType = new ParameterizedTypeReference<List<SilverType>>() {};
        Optional<List<SilverType>> silverTypes = Optional.ofNullable(restTemplate.exchange(APIConstants.API_URL + "/silver-types", HttpMethod.GET, entity, responseType).getBody());
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
    public Optional<SilverType> getSilverTypeByID(long id, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        return Optional.ofNullable(restTemplate.exchange(APIConstants.API_URL + "/silver-types/" + id, HttpMethod.GET, entity, SilverType.class).getBody());
    }

    @Override
    public void updateSilverType(SilverType silverType, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<SilverType> requestEntity = new HttpEntity<>(silverType, headers);
        restTemplate.put(APIConstants.API_URL + "/silver-types/" + silverType.getId(), requestEntity);
    }

    @Override
    public void createSilverType(SilverType silverType, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<SilverType> requestEntity = new HttpEntity<>(silverType, headers);
        restTemplate.exchange(
                APIConstants.API_URL + "/silver-types",
                HttpMethod.POST,
                requestEntity,
                SilverType.class
        );
    }

    @Override
    public void deleteSilverType(long id, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        restTemplate.exchange(APIConstants.API_URL + "/silver-types/" + id, HttpMethod.DELETE, entity, Object.class);
    }

}

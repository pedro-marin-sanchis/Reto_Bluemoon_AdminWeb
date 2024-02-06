package com.uguinformatica.bluemoon_adminweb.service.silvertype;

import com.uguinformatica.bluemoon_adminweb.model.SilverType;

import java.util.List;
import java.util.Optional;

public interface ISilverTypeService {
    Optional<List<SilverType>> getAllSilverTypes(String token);
    Optional<SilverType> getSilverTypeByID(long id, String token);
    void updateSilverType(SilverType silverType, String token);
    void createSilverType(SilverType silverType, String token);
    void deleteSilverType(long id, String token);
}

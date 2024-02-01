package com.uguinformatica.bluemoon_adminweb.service.silvertype;

import com.uguinformatica.bluemoon_adminweb.model.SilverType;

import java.util.List;
import java.util.Optional;

public interface ISilverTypeService {
    Optional<List<SilverType>> getAllSilverTypes();
    Optional<SilverType> getSilverTypeByID(long id);
    void updateSilverType(SilverType silverType);
    void createSilverType(SilverType silverType);
    void deleteSilverType(long id);
}

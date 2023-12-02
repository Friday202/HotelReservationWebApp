package si.petek.rso.catalogservice.service;

import org.springframework.stereotype.Service;
import si.petek.rso.catalogservice.dto.CatalogResponse;
import si.petek.rso.catalogservice.model.Hotel;
import si.petek.rso.catalogservice.repository.CatalogRepository;

import java.util.ArrayList;
import java.util.List;

import static si.petek.rso.catalogservice.dto.CatalogResponse.mapToCatalogResponse;

@Service
public class CatalogService {

    private final CatalogRepository catalogRepository;

    public CatalogService(CatalogRepository catalogRepository) {
        this.catalogRepository = catalogRepository;
    }

    public List<CatalogResponse> getAllHotels(){
        // Get all hotels from database and map them to CatalogResponse
        return mapToCatalogResponse(catalogRepository.findAll());
    }
}

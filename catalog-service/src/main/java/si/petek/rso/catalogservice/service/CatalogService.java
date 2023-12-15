package si.petek.rso.catalogservice.service;

import org.springframework.stereotype.Service;
import si.petek.rso.catalogservice.dto.CatalogResponse;
import si.petek.rso.catalogservice.model.Hotel;
import si.petek.rso.catalogservice.repository.CatalogRepository;

import java.util.List;
import java.util.Optional;

import static si.petek.rso.catalogservice.dto.CatalogResponse.createCatalogResponseFromHotel;
import static si.petek.rso.catalogservice.dto.CatalogResponse.mapToCatalogResponses;

@Service
public class CatalogService {

    private final CatalogRepository catalogRepository;

    public CatalogService(CatalogRepository catalogRepository) {
        this.catalogRepository = catalogRepository;
    }

    public List<CatalogResponse> getAllHotels(){
        return mapToCatalogResponses(catalogRepository.findAll());
    }

    public CatalogResponse getHotelByID(Long id){

        Optional<Hotel> foundHotel = catalogRepository.findById(id);
        CatalogResponse catalogResponse = new CatalogResponse();
        if (foundHotel.isPresent()){
            catalogResponse = createCatalogResponseFromHotel(foundHotel.get());
        }
        return catalogResponse;
    }

    public CatalogRepository getCatalogRepository(){
        return catalogRepository;
    }
}

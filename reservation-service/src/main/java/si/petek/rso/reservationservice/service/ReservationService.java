package si.petek.rso.reservationservice.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.*;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import si.petek.rso.reservationservice.dto.PostServiceRequest;
import si.petek.rso.reservationservice.dto.RoomDto;

import java.io.IOException;
import java.util.List;

@Service
public class ReservationService {

    private final RestTemplate restTemplate;

    public ReservationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<RoomDto> getAvailableRoomsFromHotel(long hotelId, String startDate, String endDate){
        // Make an API call to inventory service
        String url = "http://localhost:8081/api/v1/inventory/" + String.valueOf(hotelId) + "/" + startDate + "/" + endDate;
        String inventoryResponse = restTemplate.getForObject(url, String.class);

        //System.out.println(inventoryResponse);
        // TODO: PRICE???

        // parse to service dto's
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        try{
            List<RoomDto> roomDtos = objectMapper.readValue(inventoryResponse, new TypeReference<List<RoomDto>>() {});
            return roomDtos;

        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    public void reserveRoom(PostServiceRequest postServiceRequest){
        // make API call to add a new reservation to database in inventory
        String url = "http://localhost:8081/api/v1/inventory";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        PostServiceRequest requestData = new PostServiceRequest();
        requestData.setHotelId(postServiceRequest.getHotelId());
        requestData.setStartDate(postServiceRequest.getStartDate());
        requestData.setEmail(postServiceRequest.getEmail());
        requestData.setName(postServiceRequest.getName());
        requestData.setSurname(postServiceRequest.getSurname());
        requestData.setEndDate(postServiceRequest.getEndDate());
        requestData.setRoomId(postServiceRequest.getRoomId());

        // Use HttpEntity to set headers and request body
        HttpEntity<PostServiceRequest> requestEntity = new HttpEntity<>(requestData, headers);

        // Make the POST request
        restTemplate.postForObject(url, requestEntity, PostServiceRequest.class);
    }
}

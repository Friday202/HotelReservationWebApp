package si.petek.rso.reservationservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import si.petek.rso.reservationservice.dto.PostServiceRequest;
import si.petek.rso.reservationservice.dto.RoomDto;
import si.petek.rso.reservationservice.service.ReservationService;

import java.util.List;


@RestController
@RequestMapping("api/v1/reservation")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/{hotelId}/{startDate}/{endDate}")
    @ResponseStatus(HttpStatus.OK)
    public List<RoomDto> getAvailableRoomsFromHotel(@PathVariable long hotelId, @PathVariable String startDate, @PathVariable String endDate){
        return reservationService.getAvailableRoomsFromHotel(hotelId, startDate, endDate);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void reserveRoom(@RequestBody PostServiceRequest postServiceRequest){
        reservationService.reserveRoom(postServiceRequest);
    }

}

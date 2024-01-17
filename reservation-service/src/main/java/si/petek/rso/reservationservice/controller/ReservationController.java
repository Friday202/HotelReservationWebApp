package si.petek.rso.reservationservice.controller;

import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "Get available rooms from hotel", description = "Gets all available rooms from hotel given an ID, start and end date.")
    public List<RoomDto> getAvailableRoomsFromHotel(@PathVariable long hotelId, @PathVariable String startDate, @PathVariable String endDate){
        return reservationService.getAvailableRoomsFromHotel(hotelId, startDate, endDate);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Reserves a room", description = "Reserves a room in a hotel.")
    public void reserveRoom(@RequestBody PostServiceRequest postServiceRequest){
        reservationService.reserveRoom(postServiceRequest);
    }

}

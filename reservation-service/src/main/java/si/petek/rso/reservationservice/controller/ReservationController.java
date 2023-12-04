package si.petek.rso.reservationservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import si.petek.rso.reservationservice.service.ReservationService;


@RestController
@RequestMapping("api/v1/reservation")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }



    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public String getHello(){
        return "Gaga bababuyi!";
    }

}

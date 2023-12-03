package si.petek.rso.reservationservice.model;

// Reservation entity in a database stores information about a particular reservation
public class Reservation {

    // Holds the following data:
    private String id; // stores information about which hotel we are trying to reserve
    private String user; // stores information about user: name contact information
    private String desiredCheckInDate; // starting date
    private String desiredCheckOutDate; // end date
    private String room; // which room
    private String numOfRooms; // number of rooms
    private String price; //
}
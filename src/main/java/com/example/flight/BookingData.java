package com.example.flight;

public class BookingData {
    private String flightId;
    private String userId;
    private String leave;
    private String destination;
    private String price;

    public BookingData(String flightId, String userId, String leave, String destination, String price) {
        this.flightId = flightId;
        this.userId = userId;
        this.leave = leave;
        this.destination = destination;
        this.price = price;
    }

    public String getFlightId() {
        return flightId;
    }

    public String getUsername() {
        return userId;
    }

    public String getLeave() {
        return leave;
    }

    public String getDestination() {
        return destination;
    }

    public String getPrice() {
        return price;
    }

}
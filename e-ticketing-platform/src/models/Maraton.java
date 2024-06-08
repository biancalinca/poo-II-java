/*
 * Copyright (c) Bia
 */

package models;

import java.util.Date;
import java.util.Set;

public class Maraton extends CharityEvent{
    private static Integer idMaraton = 0;
    private Integer numberOfKilometers;

    public Maraton(String name, Date date, Integer nrOfTickets, double ticketPrice, Location location, Set<HypeTier> hypeTier, double fundraisingGoal, String cause, Integer numberOfKilometers) {
        super(name, date, nrOfTickets, ticketPrice, location, hypeTier, fundraisingGoal, cause);
        idMaraton++;
        this.numberOfKilometers =numberOfKilometers;
    }

    public static Integer getIdMaraton() {
        return idMaraton;
    }

    public static void setIdMaraton(Integer idMaraton) {
        Maraton.idMaraton = idMaraton;
    }

    public Integer getNumberOfKilometers() {
        return numberOfKilometers;
    }

    public void setNumberOfKilometers(Integer numberOfKilometers) {
        this.numberOfKilometers = numberOfKilometers;
    }

    @Override
    public String toString() {
        return "Maraton{" +
                ", id=" + id +
                ", name='" + getName() + '\'' +
                ", numberOfTickets=" + getNrOfTickets() +
                ", ticketPrice=" + getTicketPrice() +
                ", date=" + getDate() +
                ", location=" + getLocation() +
                ", fundraisingGoal=" + getFundraisingGoal() +
                ", cause=" + getCause() +
                ", hypeTier=" + getHypeTier() +
                "numberOfKilometers=" + numberOfKilometers +
                '}';
    }
}

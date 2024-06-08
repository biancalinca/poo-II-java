/*
 * Copyright (c) Bia
 */

package models;

import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

public class Concert extends Entertainment{
    private static Integer idConcert = 0;
    final private String artist;


    public Concert(String name, Date date, Integer nrOfTickets, double ticketPrice, Location location, Set<HypeTier> hypeTier, String genre, String artist) {
        super(name, date, nrOfTickets, ticketPrice, location, hypeTier, genre);
        idConcert++;
        this.artist = artist;
    }

    public String getArtist() {
        return artist;
    }

    public static Integer getIdConcert() {
        return idConcert;
    }

    public static void setIdConcert(Integer idConcert) {
        Concert.idConcert = idConcert;
    }

    @Override
    public String toString() {
        return "Concert{" +
                ", id=" + id +
                ", name='" + getName() + '\'' +
                ", numberOfTickets=" + getNrOfTickets() +
                ", ticketPrice=" + getTicketPrice() +
                ", date=" + getDate() +
                ", location=" + getLocation() +
                ", hypeTier=" + getHypeTier() +
                ", genre=" + getGenre() +
                ", artist='" + artist + '\'' +
                '}';
    }
}

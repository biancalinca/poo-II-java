/*
 * Copyright (c) Bia
 */

package models;

import java.util.Date;
import java.util.Set;

public abstract class Entertainment extends Event {
    final private String genre;

    public Entertainment(String name, Date date, Integer nrOfTickets, double ticketPrice, Location location, Set<HypeTier> hypeTier, String genre) {
        super(name, date, nrOfTickets, ticketPrice, location, hypeTier);
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }
}

/*
 * Copyright (c) Bia
 */

package models;

import java.util.Date;
import java.util.Set;

public abstract class CharityEvent extends Event {
    final private double fundraisingGoal;
    final private String cause;

    public CharityEvent(String name, Date date, Integer nrOfTickets, double ticketPrice, Location location, Set<HypeTier> hypeTier, double fundraisingGoal, String cause) {
        super(name, date, nrOfTickets, ticketPrice, location, hypeTier);
        this.fundraisingGoal = fundraisingGoal;
        this.cause = cause;
    }

    public double getFundraisingGoal() {
        return fundraisingGoal;
    }

    public String getCause() {
        return cause;
    }
}

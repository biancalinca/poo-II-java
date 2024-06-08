/*
 * Copyright (c) Bia
 */

package models;

import java.util.Date;
import java.util.Set;

public class CharityGala extends CharityEvent{
    private static Integer idCharityGala = 0;
    private String dresscode;
    private String auctionItems;


    public CharityGala(String name, Date date, Integer nrOfTickets, Integer ticketPrice, Location location, Set<HypeTier> hypeTier, double fundraisingGoal, String cause, String auctionItems, String dresscode) {
        super(name, date, nrOfTickets, ticketPrice, location, hypeTier, fundraisingGoal, cause);
        idCharityGala++;
        this.auctionItems = auctionItems;
        this. dresscode = dresscode;
    }

    public static Integer getIdCharityGala() {
        return idCharityGala;
    }

    public static void setIdCharityGala(Integer idCharityGala) {
        CharityGala.idCharityGala = idCharityGala;
    }
    public String getDresscode() {
        return dresscode;
    }

    public String getAuctionItems() {
        return auctionItems;
    }

    public void setDresscode(String dresscode) {
        this.dresscode = dresscode;
    }

    public void setAuctionItems(String auctionItems) {
        this.auctionItems = auctionItems;
    }

    @Override
    public String toString() {
        return "CharityGala{" +
                ", id=" + id +
                ", name='" + getName() + '\'' +
                ", numberOfTickets=" + getNrOfTickets() +
                ", ticketPrice=" + getTicketPrice() +
                ", date=" + getDate() +
                ", location=" + getLocation() +
                ", fundraisingGoal=" + getFundraisingGoal() +
                ", cause=" + getCause() +
                ", hypeTier=" + getHypeTier() +
                ", dresscode='" + dresscode + '\'' +
                ", auctionItems=" + auctionItems +
                '}';
    }
}

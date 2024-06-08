package models;

import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

public abstract class Event {
    protected Integer id;
    static private Integer idCount =0;
    private String name;
    private Date date;
    private Integer nrOfTickets;
    private double ticketPrice;
    private Location location;
    private TreeSet<HypeTier> hypeTiers = new TreeSet<HypeTier>(new HypeTierComparator());

    protected Event(String name, Date date, Integer nrOfTickets, double ticketPrice, Location location, Set<HypeTier> hypeTier) {
        idCount++;
        this.id = idCount;
        this.name = name;
        this.date = date;
        this.nrOfTickets = nrOfTickets;
        this.ticketPrice = ticketPrice;
        this.location = location;
        this.hypeTiers = hypeTiers;
    }

    public Event(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    public static Integer getIdCount() {
        return idCount;
    }

    public Integer getNrOfTickets() {
        return nrOfTickets;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public Location getLocation() {
        return location;
    }

    public Set<HypeTier> getHypeTier() {
        return hypeTiers;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public static void setIdCount(Integer idCount) {
        Event.idCount = idCount;
    }

    public void setNrOfTickets(Integer nrOfTickets) {
        this.nrOfTickets = nrOfTickets;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setHypeTier (HypeTier hypeTier){
        this.hypeTiers.add(hypeTier);
    }

    public void setHypeTiers(TreeSet<HypeTier> hypeTiers){
        this.hypeTiers = hypeTiers;
    }
}

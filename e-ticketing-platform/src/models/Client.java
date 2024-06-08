/*
 * Copyright (c) Bia
 */

package models;

import java.util.ArrayList;
import java.util.List;

public class Client {
    private String firstName;
    private String lastName;
    private String password;
    static private Integer idCount = 0;
    final private Integer id;
    private List<Ticket> tickets = new ArrayList<>();

    public Client(String firstName, String lastName, List<Ticket> tickets) {
        idCount++;
        this.id = idCount;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = "passClient";
        this.tickets = tickets;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public static Integer getIdCount() {
        return idCount;
    }

    public Integer getId() {
        return id;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static void setIdCount(Integer idCount) {
        Client.idCount = idCount;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id='" + this.id +'\'' +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", tickets=" + tickets +
                '}';
    }
}

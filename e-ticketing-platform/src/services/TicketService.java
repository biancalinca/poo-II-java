/*
 * Copyright (c) Bia
 */

package services;

import models.Ticket;
import java.util.List;
import java.util.ArrayList;

public class TicketService {
    private List<Ticket> tickets = new ArrayList<Ticket>();
    private static TicketService instance = null;

    private TicketService(){

    }
    public static TicketService getInstance(){
        if(instance != null){
            return instance;
        }
        instance = new TicketService();
        return instance;
    }

    public void getTickets(){
        System.out.println(tickets);
    }
}

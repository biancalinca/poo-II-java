/*
 * Copyright (c) Bia
 */

package services;

import java.io.*;

import models.Client;
import models.Event;
import models.Ticket;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import repos.ClientRepository;

public class ClientService {
    final static private List<Client> clients = new ArrayList<Client>();
    static private Integer id = 0;
    private static ClientService instance = null;
    ClientRepository clientRepository = ClientRepository.getInstance();

    private ClientService() throws IOException {

    }

    public static ClientService getInstance() throws IOException {
        if(instance != null) {
            return  instance;
        }
        instance = new ClientService();
        return instance;
    }


    public Client createClient(List<String[]> parametersArray, boolean fromCSV, boolean print) {
        id++;
        List<Ticket> tickets = new ArrayList<Ticket>();

        if (fromCSV) {
            try {
                Client client = new Client(parametersArray.get(id)[1].trim(), parametersArray.get(id)[2].trim(), tickets);
                clients.add(client);
                clientRepository.addClient(client);
                if (print)
                    System.out.println("Client added succesfully. Id: " + "\n" + id.toString() + "\n");
                return client;
            } catch (Exception e) {
                System.out.println("Nu exista destule date in fisierul CSV");
            }
        } else {
            try {
                Client client = new Client(parametersArray.get(0)[0].trim(), parametersArray.get(0)[1].trim(), tickets);
                clients.add(client);
                clientRepository.addClient(client);
                if (print)
                    System.out.println("Client added succesfully. Id: " + id.toString() + "\n");
                return client;
            } catch (Exception e) {
                System.out.println(("invalid"));
            }
        }
        return null;
    }

    public void updateClientById(String parameters) {
        String[] parametersArray = parameters.split(", ");
        Integer clientId = Integer.parseInt(parametersArray[0]);
        Client client = getClientById(clientId);
        if (client != null) {
            // Actualizează obiectul client local
            client.setFirstName(parametersArray[1].trim());
            client.setLastName(parametersArray[2].trim());

            // Actualizează clientul în baza de date
            clientRepository.updateClient(client);

            // Actualizează clientul în fișierul CSV
            try {
                updateClientInCsv(client);
                System.out.println("Client updated successfully in database and CSV.\n");
            } catch (IOException e) {
                System.out.println("An error occurred while trying to update the client in the CSV file: " + e.getMessage());
            }
        } else {
            System.out.println("Client with ID " + clientId + " not found.");
        }
    }

    private void updateClientInCsv(Client client) throws IOException {
        File csvFile = new File("src\\resources\\Client.csv");
        List<String[]> newClientsList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                // Dacă găsești clientul care trebuie actualizat
                if (values[0].trim().equals(client.getId().toString())) {
                    // Actualizează informația cu cele mai recente valori
                    values[1] = client.getFirstName();
                    values[2] = client.getLastName();
                }
                newClientsList.add(values);
            }
        }

        try (PrintWriter pw = new PrintWriter(new FileWriter(csvFile))) {
            for (String[] clientData : newClientsList) {
                String clientLine = String.join(",", clientData);
                pw.println(clientLine);
            }
        }
    }


    private Client getClientById(Integer clientId) {
        return clients.stream()
                .filter(client -> clientId.equals(client.getId()))
                .findFirst()
                .orElse(null);
    }

    public void getInfo(Integer clientId){
        for(Client client : clients) {
            if(client.getId().equals(clientId)){
                System.out.println(client);
                break;
            }
        }
    }

    public void deleteClientById(Integer id) {
        Client client = getClientById(id);
        if (client != null) {
            clients.remove(client);
            clientRepository.deleteClient(id);
            try {
                deleteClientFromCsv(id);
                System.out.println(client + " has been successfully removed from database and CSV.");
            } catch (IOException e) {
                System.out.println("An error occurred while trying to remove the client from the CSV file: " + e.getMessage());
            }
        } else {
            System.out.println("Client " + id + " does not exist.");
        }
    }
    private void deleteClientFromCsv(Integer id) throws IOException {
        File csvFile = new File("src\\resources\\Client.csv");
        List<String[]> newClientsList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                // Asumând că ID-ul este primul element în fiecare rând
                if (!values[0].trim().equals(id.toString())) {
                    newClientsList.add(values);
                }
            }
        }
        try (PrintWriter pw = new PrintWriter(new FileWriter(csvFile))) {
            for (String[] clientData : newClientsList) {
                String clientLine = String.join(",", clientData);
                pw.println(clientLine);
            }
        }
    }


    public void getClients() {
        if (clients.size() == 0) {
            System.out.println("There are no clients yet.");
        } else for (Client client : clients) {
            System.out.println(client);
        }
    }

    public void changePassword(String parameters) {
        String[] parametersArray = parameters.split(", ");
        Integer clientId = Integer.valueOf(parametersArray[0]);
        if (parametersArray[2].length() < 8) System.out.println("Password is too short. (at least 8 characters)");
        else {
            Client client = this.getClientById(clientId);
            if (client != null)
                client.setPassword(parametersArray[1]);
        }
    }
    public boolean logIn(String parameters) {
        String[] parametersArray = parameters.split(", ");
        Integer clientId = Integer.valueOf(parametersArray[0]);
        String password = parametersArray[1];
        Client client = this.getClientById(clientId);
        if (client != null)
        {
            if (!client.getPassword().equals(password)) {
                System.out.println("Wrong password.");
                return false;
            }
            else return true;
        }
        return false;
    }

    public void buyTicket(String parameters) throws IOException {
        String[] parametersArray = parameters.split(", ");
        Integer clientId = Integer.valueOf(parametersArray[0]);
        Integer eventId = Integer.valueOf(parametersArray[1]);
        EventService eventService = EventService.getInstance();
        Event event = eventService.getEventById(eventId);
        Client client = this.getClientById(clientId);
        if (client != null) {
            if (event != null) {
                if (event.getNrOfTickets() > 0) {
                    Ticket ticket = new Ticket(event);
                    event.setNrOfTickets(event.getNrOfTickets() - 1);
                    client.getTickets().add(ticket);

                } else System.out.println("No more tickets available for this event.");
            } else System.out.println("The event " + eventId + " does not exist.");
        } else System.out.println("The client " + clientId + " does not exist.");
    }

    public void getTicketsByClientId(Integer clientId) {
        Client client = this.getClientById(clientId);
        if (client != null) {
            System.out.println(client.getTickets());
        } else System.out.println("The client " + clientId + " does not exist.");
    }
}


import models.*;
import repos.ClientRepository;
import repos.HypeTierRepository;
import repos.MovieRepository;
import services.*;

import java.sql.SQLException;
import java.util.Scanner;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException, ParseException, SQLException {
        boolean admin = false;
        boolean client = false;
        boolean connected = true;

        Scanner scanner = new Scanner(System.in);
        ClientService clientService = ClientService.getInstance();
        EventService eventService = EventService.getInstance();
        AuditService audit = AuditService.getInstance();
        HypeTierService hypeTierService = HypeTierService.getInstance();
        WriterService writeService = new WriterService();

        String command, parameters;

        //partea de conectare la baza de date si crearea de tabele.
        ClientRepository clientRepository = ClientRepository.getInstance();
        clientRepository.createTable();

        HypeTierRepository hypeTierRepository = HypeTierRepository.getInstance();
        hypeTierRepository.createTable();

        MovieRepository movieRepository = MovieRepository.getInstance();
        movieRepository.createTable();

        //partea de CSV
        CSVHelper.readClientsFromCsv();
        CSVHelper.readCharityGalaFromCsv();
        CSVHelper.readMaratonFromCsv();
        CSVHelper.readConcertsFromCsv();
        CSVHelper.readMoviesFromCsv();
        CSVHelper.readHypeTiersFromCsv();

        System.out.println("Type admin if you`re an admin and client if you`re a client");
        String choice = scanner.nextLine().toLowerCase();

        if(!choice.equals("admin") && !choice.equals("client"))
            System.out.println("Invalid choice. Try again.");

        if(choice.equals("admin"))
            admin = true;
        else client = true;
        while (connected){
            while(admin){
                System.out.println("Please enter a command. Available commands: \nCREATE: createClient, createMaratonEvent, createCharityGalaEvent, createConcertEvent," +
                        " createMovieEvent, createhypeTier, \nUPDATE: updateClient, updateMaratonEvent, updateCharityGalaEvent, updateConcertEvent," +
                        " updateMovieEvent, updatehypeTier, \nGET: getEvents, getClients, gethypeTiers, gethypeTiersByType, \nDELETE: deleteEvent," +
                        " deleteClient, deletehypeTier,\naddhypeTierToEvent, buyTicket, change, \nexit");
                command = scanner.nextLine();
                switch (command) {
                    case "createClient":
                        System.out.println("You're creating a client. Please enter the client's data in the following format: firstName, lastName");
                        parameters = scanner.nextLine();
                        try {
                            List<String[]> parametersArray = new ArrayList<>(Collections.singletonList(parameters.split(", ")));
                            clientService.createClient(parametersArray, true, true);
                            String[] temp = new String[3];
                            temp[0] = Client.getIdCount().toString();
                            for (int i = 0; i < parametersArray.get(0).length; i++) {
                                temp[i + 1] = parametersArray.get(0)[i];
                            }
                            parametersArray.set(0, temp);
                            writeService.writeToCSVFile(parametersArray, "src\\resources\\Client.csv");
                            audit.writeToAudit(command);
                        } catch (Exception e) {
                            System.out.println("Not a valid input. Please try again.");
                        }
                        break;
                    case "createConcertEvent":
                        System.out.println("You're creating a concert event. Please enter the event's data in the following format:" +
                                " name, numberOfTickets, ticketPrice, date(day/month/year) time(hh:mm), country, city, " +
                                "address, genre, artist");
                        parameters = scanner.nextLine();
                        try {
                            List<String[]> parametersArray = new ArrayList<>(Collections.singletonList(parameters.split(", ")));
                            eventService.createConcertEvent(parametersArray, false);
                            String[] temp = new String[10];
                            temp[0] = Concert.getIdCount().toString();
                            for (int i = 0; i < parametersArray.get(0).length; i++) {
                                temp[i + 1] = parametersArray.get(0)[i];
                            }
                            parametersArray.set(0, temp);
                            writeService.writeToCSVFile(parametersArray, "src\\resources\\Concert.csv");
                            audit.writeToAudit(command);
                        } catch (Exception e) {
                            System.out.println("Not a valid input. Please try again.");
                        }
                        break;
                    case "createMovieEvent":
                        System.out.println("You're creating a movie event. Please enter the event's data in the following format:" +
                                " name, numberOfTickets, ticketPrice, date(day/month/year) time(HH:mm), country, city, " +
                                "address, genre, director, yearOfProduction");
                        parameters = scanner.nextLine();
                        try {
                            List<String[]> parametersArray = new ArrayList<>(Collections.singletonList(parameters.split(", ")));
                            eventService.createMovieEvent(parametersArray, false);
                            String[] temp = new String[11];
                            temp[0] = Movie.getIdCount().toString();
                            for (int i = 0; i < parametersArray.get(0).length; i++) {
                                temp[i + 1] = parametersArray.get(0)[i];
                            }
                            parametersArray.set(0, temp);
                            writeService.writeToCSVFile(parametersArray, "src\\resources\\Movie.csv");
                            audit.writeToAudit(command);
                        } catch (Exception e) {
                            System.out.println("Not a valid input. Please try again.");
                        }
                        break;
                    case "createMaratonEvent":
                        System.out.println("You're creating a maraton event. Please enter the event's data in the following format:" +
                                " name, numberOfTickets, ticketPrice, date(day/month/year) time(HH:mm), country, city, " +
                                "address, fundraisingGoal, cause, numberOfKilometers");
                        parameters = scanner.nextLine();
                        try {
                            List<String[]> parametersArray = new ArrayList<>(Collections.singletonList(parameters.split(", ")));
                            eventService.createMaraton(parametersArray, false);
                            String[] temp = new String[11];
                            temp[0] = Maraton.getIdCount().toString();
                            for (int i = 0; i < parametersArray.get(0).length; i++) {
                                temp[i + 1] = parametersArray.get(0)[i];
                            }
                            parametersArray.set(0, temp);
                            writeService.writeToCSVFile(parametersArray, "src\\resources\\Maraton.csv");
                            audit.writeToAudit(command);
                        } catch (Exception e) {
                            System.out.println("Not a valid input. Please try again.");
                        }
                        break;
                    case "createCharityGalaEvent":
                        System.out.println("You're creating a movie event. Please enter the event's data in the following format:" +
                                " name, numberOfTickets, ticketPrice, date(day/month/year) time(HH:mm), country, city, " +
                                "address, fundraisingGoal, cause, dresscode, auctionItems");
                        parameters = scanner.nextLine();
                        try {
                            List<String[]> parametersArray = new ArrayList<>(Collections.singletonList(parameters.split(", ")));
                            eventService.createCharityGala(parametersArray, false);
                            String[] temp = new String[12];
                            temp[0] = CharityGala.getIdCount().toString();
                            for (int i = 0; i < parametersArray.get(0).length; i++) {
                                temp[i + 1] = parametersArray.get(0)[i];
                            }
                            parametersArray.set(0, temp);
                            writeService.writeToCSVFile(parametersArray, "src\\resources\\CharityGala.csv");
                            audit.writeToAudit(command);
                        } catch (Exception e) {
                            System.out.println("Not a valid input. Please try again.");
                        }
                        break;
                    case "createhypeTier":
                        System.out.println("You're creating a hypeTier. Please enter the hypeTier's data in the following format:" +
                                " name, type(megahype/hype/cool/nice)");
                        parameters = scanner.nextLine();
                        try {
                            List<String[]> parametersArray = new ArrayList<>(Collections.singletonList(parameters.split(", ")));
                            hypeTierService.createHypeTier(parametersArray, false);
                            String[] temp = new String[3];
                            temp[0] = CharityGala.getIdCount().toString();
                            for (int i = 0; i < parametersArray.get(0).length; i++) {
                                temp[i + 1] = parametersArray.get(0)[i];
                            }
                            parametersArray.set(0, temp);
                            writeService.writeToCSVFile(parametersArray, "src\\resources\\HypeTier.csv");
                            audit.writeToAudit(command);
                        } catch (Exception e) {
                            System.out.println("Not a valid input. Please try again.");
                        }
                        break;
                    case "updateClient":
                        System.out.println("You're updating a client. Please enter the client's updated data in the following format:" +
                                " id, firstName, lastName\n If there is unchanged information, enter the old data for those fields. Note that the client's id cannot be changed.");
                        parameters = scanner.nextLine();
                        try {

                            clientService.updateClientById(parameters);
                            audit.writeToAudit(command);
                        } catch (Exception e) {
                            System.out.println("Not a valid input. Please try again.");
                        }
                        break;
                    case "updateConcertEvent":
                        System.out.println("You're updating a concert event. Please enter the event's updated data in the following format:" +
                                " id, newNumberOfTickets, newTicketPrice, newDate(day/month/year) newTime(hh:mm), newCountry, newCity, " +
                                "newAddress\n If there is unchanged information, " +
                                "enter the old data for those fields. Note that the event's name, artist and genre cannot be changed.");
                        parameters = scanner.nextLine();
                        try {
                            eventService.updateConcertEvent(parameters);
                            audit.writeToAudit(command);
                        } catch (Exception e) {
                            System.out.println("Not a valid input. Please try again.");
                        }
                        break;
                    case "updateMovieEvent":
                        System.out.println("You're updating a movie event. Please enter the event's updated data in the following format:" +
                                " id, newNumberOfTickets, newTicketPrice, newDate(day/month/year) newTime(hh:mm), newCountry, newCity, " +
                                "newAddress\n If there is unchanged information, " +
                                "enter the old data for those fields. Note that the event's name, movie, genre, director and year of production cannot be changed.");
                        parameters = scanner.nextLine();
                        try {
                            eventService.updateMovieEvent(parameters);
                            audit.writeToAudit(command);
                        } catch (Exception e) {
                            System.out.println("Not a valid input. Please try again.");
                        }
                        break;
                    case "updateMaratonEvent":
                        System.out.println("You're updating a maraton event. Please enter the event's updated data in the following format:" +
                                " id, newNumberOfTickets, newTicketPrice, newDate(day/month/year) newTime(hh:mm), newCountry, newCity, " +
                                "newAddress\n If there is unchanged information, " +
                                "enter the old data for those fields. Note that the event's name, fundraisingGoal, cause, numberOfKilometers cannot be changed.");
                        parameters = scanner.nextLine();
                        try {
                            eventService.updateMaraton(parameters);
                            audit.writeToAudit(command);
                        } catch (Exception e) {
                            System.out.println("Not a valid input. Please try again.");
                        }
                        break;
                    case "updateCharityGalaEvent":
                        System.out.println("You're updating a movie event. Please enter the event's updated data in the following format:" +
                                " id, newNumberOfTickets, newTicketPrice, newDate(day/month/year) newTime(hh:mm), newCountry, newCity, " +
                                "newAddress\n If there is unchanged information, " +
                                "enter the old data for those fields. Note that the event's name, fundraisingGoal,cause,dresscode,auctionItems cannot be changed.");
                        parameters = scanner.nextLine();
                        try {
                            eventService.updateCharityGala(parameters);
                            audit.writeToAudit(command);
                        } catch (Exception e) {
                            System.out.println("Not a valid input. Please try again.");
                        }
                        break;
                    case "updatehypeTier":
                        System.out.println("You're updating a hypeTier. Please enter the hypeTier's updated data in the following format:" +
                                " id, name. Note that the hypeTier's id and type cannot be changed.");
                        parameters = scanner.nextLine();
                        try {
                            hypeTierService.updateHypeTierById(parameters);
                            audit.writeToAudit(command);
                        } catch (Exception e) {
                            System.out.println("Not a valid input. Please try again.");
                        }
                        break;
                    case "getEvents":
                        try {
                            eventService.getEvents();
                            audit.writeToAudit(command);
                        } catch (Exception e) {
                            System.out.println("getEvents returned an exception.");
                        }
                        break;
                    case "getClients":
                        try {
                            clientService.getClients();
                            audit.writeToAudit(command);
                        } catch (Exception e) {
                            System.out.println("getClients returned an exception.");
                        }

                        break;
                    case "getTicketsByClientId":
                        System.out.println("Please enter client's id");
                        parameters = scanner.nextLine();
                        try {
                            clientService.getTicketsByClientId(Integer.valueOf(parameters));
                            audit.writeToAudit(command);
                        } catch (Exception e) {
                            System.out.println("Not a valid input. Please try again.");
                        }
                        break;
                    case "gethypeTiers":
                        try{
                            hypeTierService.getHypeTiers();
                            audit.writeToAudit(command);
                        } catch (Exception e) {
                            System.out.println("N ai putut sa vezi hype tiers.");
                        }
                        break;
                    case "gethypeTiersByType":
                        System.out.println("Please enter the desired type(megahype/hype/cool/nice).");
                        parameters = scanner.nextLine();
                        try {
                            hypeTierService.getHypeTiersByType(parameters);
                            audit.writeToAudit(command);
                        } catch (Exception e) {
                            System.out.println("Not a valid input. Please try again.");
                        }
                        break;
                    case "addhypeTierToEvent":
                        System.out.println("Please enter event's id and the hypeTier's id in the following format: eventId, hypeTierId");
                        parameters = scanner.nextLine();
                        try {
                            eventService.addHypeTierToEvent(parameters);
                            audit.writeToAudit(command);
                        } catch (Exception e) {
                            System.out.println("Not a valid input. Please try again.");
                        }
                        break;
                    case "deleteEvent":
                        System.out.println("You're deleting an event. Please enter the event's id.");
                        try {
                            Integer id = Integer.valueOf(scanner.nextLine());
                            eventService.deleteMovieById(id);
                            audit.writeToAudit(command);
                        } catch (Exception e) {
                            System.out.println("Not a valid input. Please try again.");
                        }
                        break;
                    case "deleteClient":
                        System.out.println("You're deleting a client. Please enter the client's id.");
                        try {
                            Integer clientId = Integer.valueOf(scanner.nextLine());
                            clientService.deleteClientById(clientId);
                            audit.writeToAudit(command);
                        } catch (Exception e) {
                            System.out.println("Not a valid input. Please try again.");
                        }
                        break;
                    case "deletehypeTier":
                        System.out.println("You're deleting a hypeTier. Please enter the hypeTier's id.");
                        try {
                            Integer hypeTierId = Integer.valueOf(scanner.nextLine());
                            hypeTierService.deleteHypeTierById(hypeTierId);
                            audit.writeToAudit(command);
                        } catch (Exception e) {
                            System.out.println("Not a valid input. Please try again.");
                        }
                        break;
                    case "buyTicket":
                        System.out.println("You're buying a ticket. Please enter the client's id and the event's id in the following format: clientId, eventId");
                        parameters = scanner.nextLine();
                        try {
                            clientService.buyTicket(parameters);
                            audit.writeToAudit(command);
                        } catch (Exception e) {
                            System.out.println("Not a valid input. Please try again.");
                        }
                        break;
                    case "change":
                        admin = false;
                        client = true;
                    case "exit":
                        System.out.println("Have a nice day! :D");
                        admin = false;
                        connected = false;
                        break;
                    default:
                        break;
                }
            }

            if (client) {
                boolean loggedIn = false;
                String clientId = null;
                while (!loggedIn) {
                    System.out.println("Hello! Please enter your id and password to log in in the following format: id, password\n");
                    try {
                        parameters = scanner.nextLine();
                        String[] parametersArray = parameters.split(", ");
                        loggedIn = clientService.logIn(parameters);
                        clientId = parametersArray[0];
                    } catch (Exception e) {
                        System.out.println("Wrong input.");
                    } }
                if (loggedIn) {
                    while (client) {
                        System.out.println("Please enter a command. Available commands: getInfo, changePassword, getEvents, gethypeTiers, gethypeTiersByType, buyTicket, gettickets, exit ");
                        command = scanner.nextLine();
                        switch (command) {
                            case "getInfo":
                                try {
                                    clientService.getInfo(Integer.valueOf(clientId));
                                    audit.writeToAudit(command);
                                } catch (Exception e) {
                                    System.out.println("Error. Please try again.");
                                }
                                break;
                            case "changePassword":
                                System.out.println("You're changing your password. Please write your information in the following format: oldPassword, newPassword");
                                parameters = clientId.trim() + ", " + scanner.nextLine();
                                try {
                                    clientService.changePassword(parameters);
                                    audit.writeToAudit(command);
                                } catch (Exception e) {
                                    System.out.println("Not a valid input. Please try again.");
                                }
                                break;
                            case "getEvents":
                                try {
                                    eventService.getEvents();
                                    audit.writeToAudit(command);
                                } catch (Exception e) {
                                    System.out.println("getEvents returned an exception.");
                                }
                                break;
                            case "getTickets":
                                parameters = clientId.trim();
                                try {
                                    clientService.getTicketsByClientId(Integer.valueOf(parameters));
                                    audit.writeToAudit(command);
                                } catch (Exception e) {
                                    System.out.println("Not a valid input. Please try again.");
                                }
                                break;
                            case "gethypeTiers":
                                try{
                                    hypeTierService.getHypeTiers();
                                    audit.writeToAudit(command);
                                }catch (Exception e) {
                                    System.out.println("gethypeTiers returned an exception.");
                                }
                                break;
                            case "gethypeTiersByType":
                                System.out.println("Please enter the desired type(megahype/hype/cool/nice).");
                                parameters = scanner.nextLine();
                                try {
                                    hypeTierService.getHypeTiersByType(parameters);
                                    audit.writeToAudit(command);
                                } catch (Exception e) {
                                    System.out.println("Not a valid input. Please try again.");
                                }
                                break;
                            case "buyTicket":
                                System.out.println("You're buying a ticket. Please enter the recipient's id (can be yours or a friend's) and the event's id in the following format: clientId, eventId");
                                parameters = scanner.nextLine();
                                try {
                                    clientService.buyTicket(parameters);
                                    audit.writeToAudit(command);
                                } catch (Exception e) {
                                    System.out.println("Not a valid input. Please try again.");
                                }
                                break;
                            case "exit":
                                System.out.println("Have a nice day! :D");
                                client = false;
                                connected = false;
                                break;
                            default:
                                break;
                        }
                    }
                }
            }
        }
    }
}
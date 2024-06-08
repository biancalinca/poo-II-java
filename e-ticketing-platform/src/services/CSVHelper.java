/*
 * Copyright (c) Bia
 */

package services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class CSVHelper {
    static ReaderService<String> readerService = new ReaderService<>();
    static EventService eventService;

    static {
        try {
            eventService = EventService.getInstance();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static ClientService clientService;

    static {
        try {
            clientService = ClientService.getInstance();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static HypeTierService hypeTierService;

    static {
        try {
            hypeTierService = HypeTierService.getInstance();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    TicketService ticketService = TicketService.getInstance();
    LocationService locationService = LocationService.getInstance();


    public static void readClientsFromCsv() throws FileNotFoundException {
        List<String[]> parametersCsv = readerService.read("src\\resources\\Client.csv");
        for (int k = 0; k < parametersCsv.size() - 1; k++) {
            try {
                clientService.createClient(parametersCsv, true, false);
            } catch (Exception e) {
                System.out.println("Something didn't work while reading clients from csv file..");
            }
        }
    }

    public static void readCharityGalaFromCsv() throws FileNotFoundException, ParseException {
        List<String[]> parametersCsv = readerService.read("src\\resources\\CharityGala.csv");
        for (int k = 0; k < parametersCsv.size() - 1; k++) {
            try {
                eventService.createCharityGala(parametersCsv, true);
            } catch (Exception e) {
                System.out.println("Something didn't work while reading charity galas from csv file..");
            }
        }
    }

    public static void readMaratonFromCsv() throws FileNotFoundException, ParseException {
        List<String[]> parametersCsv = readerService.read("src\\resources\\Maraton.csv");
        for (int k = 0; k < parametersCsv.size() - 1; k++) {
            try {
                eventService.createMaraton(parametersCsv, true);
            } catch (Exception e) {
                System.out.println("Something didn't work while reading maratons from csv file..");
            }
        }
    }

    public static void readConcertsFromCsv() throws FileNotFoundException, ParseException {
        List<String[]> parametersCsv = readerService.read("src\\resources\\Concert.csv");
        for (int k = 0; k < parametersCsv.size() - 1; k++) {
            try {
                eventService.createConcertEvent(parametersCsv, true);
            } catch (Exception e) {
                System.out.println("Something didn't work while reading concerts from csv file.");
            }
        }
    }
    public static void readMoviesFromCsv() throws FileNotFoundException, ParseException {
        List<String[]> parametersCsv = readerService.read("src\\resources\\Movie.csv");
        for (int k = 0; k < parametersCsv.size() - 1; k++) {
            try {
                eventService.createMovieEvent(parametersCsv, true);
            } catch (Exception e) {
                System.out.println("Something didn't work while reading movies from csv file..");
            }
        }
    }
    public static void readHypeTiersFromCsv() throws FileNotFoundException, ParseException {
        List<String[]> parametersCsv = readerService.read("src\\resources\\HypeTier.csv");
        // Asigură-te că readerService.read() nu returnează null și că lista are elemente
        if (parametersCsv != null && !parametersCsv.isEmpty()) {
            for (int k = 0; k < parametersCsv.size() - 1; k++) {
                try {
                    hypeTierService.createHypeTier(parametersCsv, true);
                } catch (Exception e) {
                    System.out.println("Something didn't work while reading sponsors from csv file.");
                }
            }
        }
    }


}

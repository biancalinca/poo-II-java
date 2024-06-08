/*
 * Copyright (c) Bia
 */

package services;

import models.*;
import repos.MovieRepository;

import java.io.*;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class EventService {
    static private List<Event> events = new ArrayList<Event>();
    static private List<Movie> movies = new ArrayList<Movie>();
    static private Integer id = 0;
    static private Integer locationId = 0;
    private static EventService instance = null;

    MovieRepository movieRepository = MovieRepository.getInstance();

    private EventService() throws IOException {

    }

    public static EventService getInstance() throws IOException {
        if (instance != null) {
            return instance;
        }
        instance = new EventService();
        return instance;
    }

    private void addEvent(Event event) {
        events.add(event);
    }

    public Event getEventById(Integer id){
        for (Event event : events){
            if(event.getId().equals(id))
                return event;
        }
        return null;
    }
    public void getEvents() {
        if (events.size() == 0) {
            System.out.println("There are 0 models.events.");
        } else events.forEach((event) -> System.out.println(event));
    }

    public Concert createConcertEvent(List<String[]> parametersArray, boolean fromCsv) throws ParseException {
        id = Concert.getIdConcert() + 1;
        locationId = Location.getIdLocation();
        if (fromCsv) {
            try {
                TreeSet<HypeTier> hypeTiers = new TreeSet<HypeTier>(new HypeTierComparator());
                Location location = new Location(locationId, parametersArray.get(id)[5].trim(), parametersArray.get(id)[6].trim(), parametersArray.get(id)[7].trim());
                Date eventDate = (new SimpleDateFormat("dd/MM/yyyy HH:mm")).parse(parametersArray.get(id)[4]);
                Concert concert = new Concert(parametersArray.get(id)[1].trim(), eventDate,
                        Integer.valueOf(parametersArray.get(id)[2]), Double.parseDouble(parametersArray.get(id)[3]), location, hypeTiers,
                        parametersArray.get(id)[8].trim(), parametersArray.get(id)[9].trim());
                events.add(concert);
                return concert;
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Eroareee" + e.getMessage());
            }

        } else {
            try {
                TreeSet<HypeTier> hypeTiers = new TreeSet<HypeTier>(new HypeTierComparator());
                Location location = new Location(locationId, parametersArray.get(0)[4].trim(), parametersArray.get(0)[5].trim(), parametersArray.get(0)[6].trim());
                Date eventDate = (new SimpleDateFormat("dd/MM/yyyy HH:mm")).parse(parametersArray.get(0)[3]);
                Concert concert = new Concert(parametersArray.get(0)[0].trim(), eventDate,
                        Integer.valueOf(parametersArray.get(0)[1]), Double.parseDouble(parametersArray.get(0)[2]), location, hypeTiers,
                        parametersArray.get(0)[7].trim(), parametersArray.get(0)[8].trim());
                events.add(concert);
                return concert;
            } catch (Exception e) {
                System.out.println("Invalid.");
            }
        }
        return null;
    }


    public Movie createMovieEvent(List<String[]> parametersArray, boolean fromCsv) throws ParseException {
        id = Movie.getIdMovie() + 1;
        locationId = Location.getIdLocation();
        if (fromCsv) {
                 try {
            TreeSet<HypeTier> hypeTiers = new TreeSet<HypeTier>(new HypeTierComparator());
            Location location = new Location(locationId, parametersArray.get(id)[5].trim(), parametersArray.get(id)[6].trim(), parametersArray.get(id)[7].trim());
            Date eventDate = (new SimpleDateFormat("dd/MM/yyyy HH:mm")).parse(parametersArray.get(id)[4]);
            Movie movie = new Movie(parametersArray.get(id)[1].trim(), eventDate,
                    Integer.valueOf(parametersArray.get(id)[2]), Double.parseDouble(parametersArray.get(id)[3]), location, hypeTiers,
                    parametersArray.get(id)[8].trim(), parametersArray.get(0)[9].trim(), Integer.valueOf(parametersArray.get(id)[10]));
            events.add(movie);
            movieRepository.addMovie(movie);
            return movie;
           } catch (Exception e) {
                 System.out.println("Invalid.");
              }
        } else {
              try {
            TreeSet<HypeTier> HypeTiers = new TreeSet<HypeTier>(new HypeTierComparator());
            Location location = new Location(locationId, parametersArray.get(0)[4].trim(), parametersArray.get(0)[5].trim(), parametersArray.get(0)[6].trim());
            Date eventDate = (new SimpleDateFormat("dd/MM/yyyy HH:mm")).parse(parametersArray.get(0)[3]);
            Movie movie = new Movie(parametersArray.get(0)[0].trim(), eventDate,
                    Integer.valueOf(parametersArray.get(0)[1]), Double.parseDouble(parametersArray.get(0)[2]), location, HypeTiers,
                    parametersArray.get(0)[7].trim(), parametersArray.get(0)[8].trim(), Integer.valueOf(parametersArray.get(0)[9]));
            events.add(movie);
            movieRepository.addMovie(movie);
            return movie;

              } catch (Exception e) {
                  System.out.println("Invalid.");
              }
        }
         return null;
    }


    public void updateConcertEvent(String parameters) throws ParseException {
        String[] parametersArray = parameters.split(", ");
        Integer oldEventId = Integer.valueOf(parametersArray[0]);
        Concert eventToUpdate = (Concert) getEventById(oldEventId);
        Location location = new Location(locationId, parametersArray[4].trim(), parametersArray[5].trim(), parametersArray[6].trim());
        Date eventDate = (new SimpleDateFormat("dd/MM/yyyy HH:mm")).parse(parametersArray[3]);
        eventToUpdate.setDate(eventDate);
        eventToUpdate.setLocation(location);
        eventToUpdate.setTicketPrice(Double.parseDouble(parametersArray[2]));
        eventToUpdate.setNrOfTickets(Integer.valueOf(parametersArray[1]));
    }

    public void updateMovieEvent(String parameters) throws ParseException {
        String[] parametersArray = parameters.split(", ");
        Integer oldEventId = Integer.valueOf(parametersArray[0]);
        Movie eventToUpdate = (Movie) getEventById(oldEventId);
        Location location = new Location(locationId, parametersArray[4].trim(), parametersArray[5].trim(), parametersArray[6].trim());
        Date eventDate = (new SimpleDateFormat("dd/MM/yyyy HH:mm")).parse(parametersArray[3]);
        eventToUpdate.setDate(eventDate);
        eventToUpdate.setLocation(location);
        eventToUpdate.setTicketPrice(Double.parseDouble(parametersArray[2]));
        eventToUpdate.setNrOfTickets(Integer.valueOf(parametersArray[1]));
    }

    public CharityGala createCharityGala(List<String[]> parametersArray, boolean fromCSV) throws ParseException {
        id = CharityGala.getIdCharityGala() + 1;
        locationId = Location.getIdLocation();
        if (fromCSV) {
            try {
                TreeSet<HypeTier> hypeTiers= new TreeSet<HypeTier>(new HypeTierComparator());
                Location location = new Location(locationId, parametersArray.get(id)[5].trim(), parametersArray.get(id)[6].trim(), parametersArray.get(id)[7].trim());
                Date eventDate = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(parametersArray.get(id)[4]);
                CharityGala charityGala = new CharityGala(parametersArray.get(id)[1].trim(), eventDate, Integer.valueOf(parametersArray.get(id)[2]),
                        Integer.valueOf(parametersArray.get(id)[3]), location, hypeTiers, Double.parseDouble(parametersArray.get(id)[8].trim()), parametersArray.get(id)[9].trim(),
                        parametersArray.get(id)[10].trim(), parametersArray.get(id)[11].trim());
                events.add(charityGala);
                return charityGala;
            } catch (Exception e) {
                System.out.println("Not enough data in the csv file.");
            }
        } else {
            try {
                TreeSet<HypeTier> hypeTiers= new TreeSet<HypeTier>(new HypeTierComparator());
                Location location = new Location(locationId, parametersArray.get(0)[4].trim(), parametersArray.get(0)[5].trim(), parametersArray.get(0)[6].trim());
                Date eventDate = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(parametersArray.get(0)[3]);
                CharityGala charityGala = new CharityGala(parametersArray.get(0)[1].trim(), eventDate, Integer.valueOf(parametersArray.get(0)[2]),
                        Integer.valueOf(parametersArray.get(0)[2]), location, hypeTiers, Double.parseDouble(parametersArray.get(0)[7].trim()), parametersArray.get(0)[8].trim(),
                        parametersArray.get(0)[9].trim(), parametersArray.get(0)[10].trim());
                events.add(charityGala);
                return charityGala;
            } catch (Exception e) {
                System.out.println("Invalid.");
            }
        }
        return null;
    }

    public Maraton createMaraton(List<String[]> parametersArray, boolean fromCSV) throws ParseException {
        id = Maraton.getIdMaraton() + 1;
        locationId = Location.getIdLocation();
        if (fromCSV) {
            try {
                TreeSet<HypeTier> hypeTiers= new TreeSet<HypeTier>(new HypeTierComparator());
                Location location = new Location(locationId, parametersArray.get(id)[5].trim(), parametersArray.get(id)[6].trim(), parametersArray.get(id)[7].trim());
                Date eventDate = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(parametersArray.get(id)[4]);
                Maraton maraton = new Maraton(parametersArray.get(id)[1].trim(),eventDate, Integer.valueOf(parametersArray.get(id)[2]),
                        Double.parseDouble(parametersArray.get(id)[3]), location, hypeTiers, Double.parseDouble(parametersArray.get(id)[8].trim()), parametersArray.get(id)[9].trim(),
                        Integer.parseInt(parametersArray.get(id)[10].trim()));
                events.add(maraton);
                return maraton;
            } catch (Exception e) {
                System.out.println("Not enough data in the csv file.");
            }
        } else {
            try {
                TreeSet<HypeTier> hypeTiers= new TreeSet<HypeTier>(new HypeTierComparator());
                Location location = new Location(locationId, parametersArray.get(0)[4].trim(), parametersArray.get(0)[5].trim(), parametersArray.get(0)[6].trim());
                Date eventDate = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(parametersArray.get(0)[3]);
                Maraton maraton = new Maraton(parametersArray.get(0)[0].trim(), eventDate, Integer.valueOf(parametersArray.get(0)[1]),
                        Integer.valueOf(parametersArray.get(0)[2]), location, hypeTiers, Double.parseDouble(parametersArray.get(0)[7].trim()), parametersArray.get(0)[8].trim(),
                        Integer.parseInt(parametersArray.get(0)[9].trim()));
                events.add(maraton);
                return maraton;
            } catch (Exception e) {
                System.out.println("Invalid.");
            }
        }
        return null;
    }

    public void updateCharityGala(String parameters) throws ParseException {
        String[] parametersArray = parameters.split(", ");
        Integer oldEventId = Integer.valueOf(parametersArray[0]);
        CharityGala eventToUpdate = (CharityGala) getEventById(oldEventId);
        Location location = new Location(locationId, parametersArray[6].trim(), parametersArray[5].trim(), parametersArray[4].trim());
        Date eventDate = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(parametersArray[3]);
        eventToUpdate.setDate(eventDate);
        eventToUpdate.setLocation(location);
        eventToUpdate.setTicketPrice(Double.parseDouble(parametersArray[2]));
        eventToUpdate.setNrOfTickets(Integer.valueOf(parametersArray[1]));
        eventToUpdate.setAuctionItems(parametersArray[6].trim());
    }


    public void updateMaraton(String parameters) throws ParseException {
        String[] parametersArray = parameters.split(", ");
        Integer oldEventId = Integer.valueOf(parametersArray[0]);
        Maraton eventToUpdate = (Maraton) getEventById(oldEventId);
        Location location = new Location(locationId, parametersArray[6].trim(), parametersArray[5].trim(), parametersArray[4].trim());
        Date eventDate = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(parametersArray[3]);
        eventToUpdate.setDate(eventDate);
        eventToUpdate.setLocation(location);
        eventToUpdate.setTicketPrice(Double.parseDouble(parametersArray[2]));
        eventToUpdate.setNrOfTickets(Integer.valueOf(parametersArray[1]));
        eventToUpdate.setNumberOfKilometers(Integer.valueOf(parametersArray[6].trim()));
    }

    public void addHypeTierToEvent(String parameters) throws IOException {
        String[] parametersArray = parameters.split(", ");
        Integer hypeTierId = Integer.valueOf(parametersArray[1]);
        Integer eventId = Integer.valueOf(parametersArray[0]);
        HypeTierService hypeTierService = HypeTierService.getInstance();
        HypeTier hypeTier = hypeTierService.getHypeTierById(hypeTierId);
        Event event = getEventById(eventId);
        if (event != null)
            event.setHypeTier(hypeTier);
        else System.out.println(eventId + " does not exist.");
    }

    public void deleteEventById(Integer id) {
        for (Event event : events) {
            if (event.getId().equals(id)) {
                events.remove(event);
                System.out.println(event.getName() + " has been successfully removed.");
                break;
            }
        }
    }

    //delete movie
    public Movie getMovieById(Integer hypeTierId) {
        for (Movie movie : movies) {
            if (movie.getId().equals(hypeTierId))
                return movie;
        }
        return null;
    }
    public void deleteMovieById(Integer id) {
        Movie movie = getMovieById(id);
        if (movie != null) {
            movies.remove(movie);
            movieRepository.deleteMovie(id);
            try {
                deleteMovieFromCsv(id);
                System.out.println(movie + " has been successfully removed from database and CSV.");
            } catch (IOException e) {
                System.out.println("An error occurred while trying to remove the client from the CSV file: " + e.getMessage());
            }
        } else {
            System.out.println("Movie " + id + " does not exist.");
        }
    }

    public void deleteMovieFromCsv(Integer id) throws IOException {
        File csvFile = new File("src\\resources\\Movie.csv");
        List<String[]> newMoviesList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                // Asumând că ID-ul este primul element în fiecare rând
                if (!values[0].trim().equals(id.toString())) {
                    newMoviesList.add(values);
                }
            }
        }
        try (PrintWriter pw = new PrintWriter(new FileWriter(csvFile))) {
            for (String[] movieData : newMoviesList) {
                String movieLine = String.join(",", movieData);
                pw.println(movieLine);
            }
        }
    }

}



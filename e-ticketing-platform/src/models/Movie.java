/*
 * Copyright (c) Bia
 */

package models;

import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

public class Movie extends Entertainment {
    private static Integer idMovie = 0;
    final private String director;
    final private Integer yearOfProduction;


    public Movie(String name, Date date, Integer nrOfTickets, double ticketPrice, Location location, Set<HypeTier> hypeTier, String genre, String director, Integer yearOfProduction) {
        super(name, date, nrOfTickets, ticketPrice, location, hypeTier, genre);
        idMovie++;
        this.director = director;
        this.yearOfProduction = yearOfProduction;
    }

    public static Integer getIdMovie() {
        return idMovie;
    }

    public static void setIdMovie(Integer idMovie) {
        Movie.idMovie = idMovie;
    }

    public String getDirector() {
        return director;
    }

    public Integer getYearOfProduction() {
        return yearOfProduction;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + getName() + '\'' +
                ", numberOfTickets=" + getNrOfTickets() +
                ", ticketPrice=" + getTicketPrice() +
                ", date=" + getDate() +
                ", location=" + getLocation() +
                ", hypeTier=" + getHypeTier() +
                ", genre=" + getGenre() +
                "yearOfProduction=" + yearOfProduction +
                ", director='" + director + '\'' +
                '}';
    }
}

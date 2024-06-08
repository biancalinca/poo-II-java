/*
 * Copyright (c) Bia
 */

package models;

public class Location {
    private Integer id;
    private static Integer idLocation = 1;
    private String country;
    private String city;
    private String address;

    public Location(Integer id, String country, String city, String address) {
        idLocation++;
        this.id = id;
        this.country = country;
        this.city = city;
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public static Integer getIdLocation() {
        return idLocation;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public static void setIdLocation(Integer idLocation) {
        Location.idLocation = idLocation;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}

/*
 * Copyright (c) Bia
 */

package services;

import models.Location;

import java.util.ArrayList;
import java.util.List;

public class LocationService {
    static private List<Location> locations = new ArrayList<Location>();
    final static private Integer id =0;
    private static LocationService instance = null;

    public LocationService(){
    }

    public static LocationService getInstance(){
        if(instance != null){
            return instance;
        }
        instance = new LocationService();
        return instance;
    }

    public Location getLocationById(Integer id){
        for(Location location : locations){
            if(location.getId().equals(id))
                return location;
        }
        return null;
    }
}

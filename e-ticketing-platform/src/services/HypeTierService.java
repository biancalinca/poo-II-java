/*
 * Copyright (c) Bia
 */

package services;
import models.*;
import repos.HypeTierRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class HypeTierService {
    private static List<HypeTier> hypeTiers = new ArrayList<HypeTier>();
    private static Integer id = 0;
    private static HypeTierService instance = null;
    HypeTierRepository hypeTierRepository = HypeTierRepository.getInstance();
    private HypeTierService() throws IOException {

    }
    public static HypeTierService getInstance() throws IOException{
        if (instance != null) {
            return instance;
        }
        instance = new HypeTierService();
        return instance;
    }

    public HypeTier createHypeTier(List<String[]> parametersArray, boolean fromCsv) {
        id = HypeTier.getIdHypeTier() + 1;
        if (fromCsv) {
            try {
                if (parametersArray.get(id)[2].toLowerCase().equals("megahype") || parametersArray.get(id)[2].toLowerCase().equals("hype")
                        || parametersArray.get(id)[2].toLowerCase().equals("cool") || parametersArray.get(id)[2].toLowerCase().equals("nice")) {
                    HypeTier hypeTier = new HypeTier(parametersArray.get(id)[1], parametersArray.get(id)[2].toLowerCase());
                    hypeTiers.add(hypeTier);
                    hypeTierRepository.addHypeTier(hypeTier);
                    return hypeTier;
                }
            } catch (Exception e) {
                System.out.println("Invalid");
            }
        } else {
            try {
                if (parametersArray.get(0)[1].toLowerCase().equals("megahype") || parametersArray.get(0)[1].toLowerCase().equals("hype")
                        || parametersArray.get(0)[1].toLowerCase().equals("cool") || parametersArray.get(0)[1].toLowerCase().equals("nice")) {
                    HypeTier hypeTier = new HypeTier(parametersArray.get(0)[0], parametersArray.get(0)[1].toLowerCase());
                    hypeTiers.add(hypeTier);
                    return hypeTier;
                }
            } catch (Exception e) {
                System.out.println("Invalid");
            }
        }
        return null;
    }

    //update
    public void updateHypeTierById(String parameters) {
        String[] parametersArray = parameters.split(", ");
        Integer hypeTierId = Integer.parseInt(parametersArray[0]);
        String hypeTierName = parametersArray[1];
        HypeTier hypeTier = getHypeTierById(hypeTierId);
        if (hypeTier != null) {
            // Actualizează obiectul hypeTier local
            hypeTier.setName(parametersArray[1].trim());
            hypeTier.setType(parametersArray[2].trim());

            // Actualizează hypeTier în baza de date
            hypeTierRepository.updateHypeTierName(hypeTierName, hypeTierId);

            // Actualizează hypeTier în fișierul CSV
            try {
                updateHypeTierInCsv(hypeTier);
                System.out.println("HypeTier updated successfully in database and CSV.\n");
            } catch (IOException e) {
                System.out.println("An error occurred while trying to update the client in the CSV file: " + e.getMessage());
            }
        } else {
            System.out.println("HypeTier with ID " + hypeTierId + " not found.");
        }
    }

    private void updateHypeTierInCsv(HypeTier hypeTier) throws IOException {
        File csvFile = new File("src\\resources\\HypeTier.csv");
        List<String[]> newHypeTiersList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");


                if (values[0].trim().equals(hypeTier.getId().toString())) {
                    // Actualizează informația cu cele mai recente valori
                    values[1] = hypeTier.getName();
                    values[2] = hypeTier.getType();
                }
                newHypeTiersList.add(values);
            }
        }
        try (PrintWriter pw = new PrintWriter(new FileWriter(csvFile))) {
            for (String[] hypeTierData : newHypeTiersList) {
                String hypeTierLine = String.join(",", hypeTierData);
                pw.println(hypeTierLine);
            }
        }
    }

    // delete
    public void deleteHypeTierById(Integer id) {
        HypeTier hypeTier = getHypeTierById(id);
        if (hypeTier != null) {
            hypeTiers.remove(hypeTier);
            hypeTierRepository.deleteHypeTier(id);
            try {
                deleteHypeTierFromCsv(id);
                System.out.println(hypeTier + " has been successfully removed from database and CSV.");
            } catch (IOException e) {
                System.out.println("An error occurred while trying to remove the client from the CSV file: " + e.getMessage());
            }
        } else {
            System.out.println("HypeTier " + id + " does not exist.");
        }
    }
    private void deleteHypeTierFromCsv(Integer id) throws IOException {
        File csvFile = new File("src\\resources\\HypeTier.csv");
        List<String[]> newHypeTiersList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                // Asumând că ID-ul este primul element în fiecare rând
                if (!values[0].trim().equals(id.toString())) {
                    newHypeTiersList.add(values);
                }
            }
        }
        try (PrintWriter pw = new PrintWriter(new FileWriter(csvFile))) {
            for (String[] hypeTierData : newHypeTiersList) {
                String hypeTierLine = String.join(",", hypeTierData);
                pw.println(hypeTierLine);
            }
        }
    }

    public HypeTier getHypeTierById(Integer hypeTierId) {
        for (HypeTier hypeTier : hypeTiers) {
            if (hypeTier.getId().equals(hypeTierId))
                return hypeTier;
        }
        return null;
    }

    public void getHypeTiers() {
        if (hypeTiers.size() == 0) {
            System.out.println("There are 0 hype tiers :(.");
        } else {
            hypeTiers.forEach((hypeTiers) -> System.out.println(hypeTiers));
        }
    }

    public void getHypeTiersByType(String type) {
        hypeTiers.stream().filter(hypeTier -> hypeTier.getType().equals(type.toLowerCase())).forEach(hypeTier -> System.out.println(hypeTier));
    }

}

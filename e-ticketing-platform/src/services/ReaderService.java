/*
 * Copyright (c) Bia
 */

package services;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReaderService<T> {

    public ReaderService(){}

    public List<T[]> read(String path) throws FileNotFoundException{
        Scanner scanner = new Scanner(new File(path));
        List<T[]> data = new ArrayList<>();

        while (scanner.hasNext()){
            String line = scanner.nextLine();
            T[] items = (T[]) (line.split(","));
            data.add(items);
        }
        scanner.close();
        return data;
    }
}

/*
 * Copyright (c) Bia
 */

package services;

import java.io.*;
import java.util.List;

public class WriterService<T> {

    public WriterService(){}

    public void writeToCSVFile(List<T[]> write, String fileName){
        try(FileWriter writer = new FileWriter(fileName, true)){
            for(T[] strings : write){
                for(int i =0; i< strings.length; i++ ){
                    writer.append((CharSequence) strings[i]);
                    if(i<(strings.length-1))
                        writer.append(",");
                }
                writer.append(System.lineSeparator());
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (Exception e){
            System.out.println("Ceva nu a mers cand ai incercat sa scrii in fisierul CSV!");
        }
    }
}

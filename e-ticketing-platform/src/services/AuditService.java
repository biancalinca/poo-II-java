/*
 * Copyright (c) Bia
 */

package services;

import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.IOException;

public class AuditService {
    private static AuditService instance = null;

    public AuditService() {
    }

    public static AuditService getInstance() throws IOException{
        if(instance != null){
            return instance;
        }
        instance = new AuditService();
        File myFile = new File("src\\resources\\audit.csv");
        if( myFile.createNewFile()){
            try (FileWriter write = new FileWriter("src\\resources\\audit.csv", true)){
                write.append("Action");
                write.append(",");
                write.append("Time");
                write.append(System.lineSeparator());
                write.flush();
            }catch (IOException e){
                e.printStackTrace();
            }catch (Exception e){
                System.out.println("Something went wrong when trying to write to audit.");
            }
        }
        return instance;
    }

    public void writeToAudit(String write) throws IOException {

        try (FileWriter writer = new FileWriter("src\\resources\\audit.csv", true)) {
            writer.append(write);
            writer.append(",");
            writer.append(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")).toString());
            writer.append(System.lineSeparator());
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Something went wrong when trying to write to audit.");
        }
    }


}

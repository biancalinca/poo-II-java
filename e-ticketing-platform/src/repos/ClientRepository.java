package repos;

import config.DatabaseConfig;
import models.Client;

import services.AuditService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

public class ClientRepository {
    private static ClientRepository instance = null;
    AuditService audit = AuditService.getInstance();
    private ClientRepository() throws IOException {

    }

    public static ClientRepository getInstance() throws IOException {
        if (instance != null) {
            return instance;
        }
        instance = new ClientRepository();
        return instance;
    }
    public void createTable() {
        String createTableSql = "CREATE TABLE IF NOT EXISTS Client " +
                "(id int PRIMARY KEY AUTO_INCREMENT, " +
                "firstname varchar(50)," +
                "lastname varchar(30))";
        Connection connection = DatabaseConfig.getDatabaseConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(createTableSql)){
            Statement stat = connection.createStatement();
            stat.execute(createTableSql);
            audit.writeToAudit("createClientTabletDB");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public void addClient(Client client) {
        String insertPersonSql = "INSERT INTO Client(firstname, lastname) VALUES(?, ?)";

        Connection connection = DatabaseConfig.getDatabaseConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertPersonSql)) {
            preparedStatement.setString(1, client.getFirstName());
            preparedStatement.setString(2, client.getLastName());

            preparedStatement.executeUpdate();
            audit.writeToAudit("addClientDB");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    //adaugat
    public void updateClient(Client client) {
        String updateClientSql = "UPDATE Client SET firstname = ?, lastname = ? WHERE id = ?";
        Connection connection = DatabaseConfig.getDatabaseConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateClientSql)) {
            preparedStatement.setString(1, client.getFirstName());
            preparedStatement.setString(2, client.getLastName());
            preparedStatement.setInt(3, client.getId());

            int result = preparedStatement.executeUpdate(); // Aceasta ar trebui să returneze numărul de rânduri afectate
            if (result > 0) {
                System.out.println("Client updated successfully in the database.");
                audit.writeToAudit("updateClientDB");
            } else {
                System.out.println("No client was updated, please check the client ID.");
            }
        } catch (SQLException | IOException e) {
            System.err.println("Error updating client in the database: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public void deleteClient(int clientId) {
        String deleteClientSql = "DELETE FROM Client WHERE id = ?";
        Connection connection = DatabaseConfig.getDatabaseConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteClientSql)) {
            preparedStatement.setInt(1, clientId);
            preparedStatement.executeUpdate();
            audit.writeToAudit("deleteClientDB");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

}

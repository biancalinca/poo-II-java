/*
 * Copyright (c) Bia
 */

package repos;

import config.DatabaseConfig;
import models.HypeTier;
import services.AuditService;

import java.io.IOException;
import java.sql.*;

public class HypeTierRepository {
    private static HypeTierRepository instance = null;
    AuditService audit = AuditService.getInstance();

    private HypeTierRepository() throws IOException {

    }

    public static HypeTierRepository getInstance() throws IOException {
        if (instance != null) {
            return instance;
        }
        instance = new HypeTierRepository();
        return instance;
    }
    public void createTable() throws IOException {
        String createTableSql = "CREATE TABLE IF NOT EXISTS hypeTier " +
                "(id int PRIMARY KEY AUTO_INCREMENT, " +
                "name varchar(30), " +
                "type varchar(30))";
        Connection connection = DatabaseConfig.getDatabaseConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(createTableSql)){
            Statement stat = connection.createStatement();
            stat.execute(createTableSql);
            audit.writeToAudit("createHypeTierTableDB");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
  public void addHypeTier(HypeTier hypeTier) throws IOException {
      String insertPersonSql = "INSERT INTO hypeTier(name, type) VALUES(?, ?)";

      Connection connection = DatabaseConfig.getDatabaseConnection();

      try (PreparedStatement preparedStatement = connection.prepareStatement(insertPersonSql)) {
          preparedStatement.setString(1, hypeTier.getName());
          preparedStatement.setString(2, hypeTier.getType());
          audit.writeToAudit("addhypeTierDB");
          preparedStatement.executeUpdate();
      } catch (SQLException e) {
          e.printStackTrace();
      }
  }

    public HypeTier getHypeTierByType(String type) throws IOException {
        String selectSql = "SELECT * FROM hypeTier WHERE type=?";

        Connection connection = DatabaseConfig.getDatabaseConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectSql)) {
            preparedStatement.setString(1, type);

            ResultSet resultSet = preparedStatement.executeQuery();
            audit.writeToAudit("getSponsorByTypeDB");
            return mapToHypeTier(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void updateHypeTierName(String name, int id) {
        String updateNameSql = "UPDATE hypeTier SET name=? WHERE id=?";

        Connection connection = DatabaseConfig.getDatabaseConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateNameSql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, id);

            preparedStatement.executeUpdate();
            audit.writeToAudit("updateHypeTierNameDB");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteHypeTier(int id) {
        String deleteSql = "DELETE FROM hypeTier WHERE id=?";
        Connection connection = DatabaseConfig.getDatabaseConnection();
        try(PreparedStatement statement = connection.prepareStatement(deleteSql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            audit.writeToAudit("deleteHypeTierDB");
        }
        catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
    private HypeTier mapToHypeTier(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            return new HypeTier(resultSet.getString(2), resultSet.getString(3));
        }
        return null;
    }


}

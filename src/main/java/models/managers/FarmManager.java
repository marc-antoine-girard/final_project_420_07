package models.managers;

import models.entities.Farm;
import org.intellij.lang.annotations.Language;
import services.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class FarmManager {

    @Language("SQL") // need to set datasource to use this
    private static final String queryByCountry = "select * from farm where lower(country) = ?";
    @Language("SQL") // we now know the query works
    private static final String queryAll = "select * from farm";

    public static HashMap<Integer, Farm> getAll() {
        HashMap<Integer, Farm> result = new HashMap<>();

        // Get service // Do query
        try (PreparedStatement preparedStatement = DatabaseConnection.getInstance().preparedQuery(queryAll)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            // Process result
            while (resultSet.next()) {
                int id = resultSet.getInt("farm_id");
                String farm_name = resultSet.getString("farm_name");
                String country = resultSet.getString("country");

                Farm farm = new Farm(id, farm_name, country);

                result.put(farm.getId(), farm); // add all farms
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseConnection.getInstance().close(); // very important
        }

        // return result
        return result;
    }

    public static HashMap<Integer, Farm> getByCountry(String value) {
        HashMap<Integer, Farm> result = new HashMap<>();

        try (PreparedStatement preparedStatement = DatabaseConnection.getInstance().preparedQuery(queryByCountry)) {
            preparedStatement.setString(1, value.toLowerCase()); // index starts at 1 instead of 0

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                int id = resultSet.getInt("farm_id");
                String farm_name = resultSet.getString("farm_name");
                String country = resultSet.getString("country");

                Farm farm = new Farm(id, farm_name, country);

                result.put(farm.getId(), farm);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseConnection.getInstance().close();
        }

        return result;
    }

}

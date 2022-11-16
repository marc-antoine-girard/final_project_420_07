package models.managers;

import models.entities.Farm;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanMapHandler;
import services.DatabaseConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class FarmManager {

    private static final String queryByCountry = "select * from farm where lower(country) = ?";
    private static final String queryAll = "select * from farm";

    public static HashMap<Integer, Farm> getAll() {
        try
        {
            QueryRunner runner = DatabaseConnection.getInstance().runner();
            return runner.query(queryAll, FarmManager::handle);
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public static Map<Integer, Farm> getByCountry(String value) {
        try
        {
            QueryRunner runner = DatabaseConnection.getInstance().runner();
            return runner.query(queryByCountry, new BeanMapHandler<>(Farm.class, "farm_id"), value);
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    private static HashMap<Integer, Farm> handle(ResultSet resultSet) throws SQLException {
        HashMap<Integer, Farm> temp = new HashMap<>();
        while (resultSet.next()) {
            int id = resultSet.getInt("farm_id");
            String farm_name = resultSet.getString("farm_name");
            String country = resultSet.getString("country");

            Farm farm = new Farm(id, farm_name, country);

            temp.put(farm.getId(), farm);
        }
        return temp;
    }
}

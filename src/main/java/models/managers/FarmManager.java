package models.managers;

import models.entities.Farm;
import org.apache.commons.dbutils.QueryRunner;
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

    public static HashMap<Integer, Farm> getByCountry(String value) {
        try
        {
            QueryRunner runner = DatabaseConnection.getInstance().runner();
            return runner.query(queryByCountry, FarmManager::handle, value);
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

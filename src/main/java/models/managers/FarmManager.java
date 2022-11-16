package models.managers;

import models.entities.Farm;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanMapHandler;
import services.DatabaseConnection;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class FarmManager {

    private static final String queryByCountry = "select * from farm where lower(country) = ?";
    private static final String queryAll = "select * from farm";

    public static Map<Integer, Farm> getAll() {
        try {
            QueryRunner runner = DatabaseConnection.getInstance().runner();
            return runner.query(queryAll, new BeanMapHandler<>(Farm.class));
        } catch (SQLException e) {
            e.printStackTrace();
            return new HashMap<>();
        }
    }

    public static Map<Integer, Farm> getByCountry(String value) {
        try {
            QueryRunner runner = DatabaseConnection.getInstance().runner();
            return runner.query(queryByCountry, new BeanMapHandler<>(Farm.class), value);
        } catch (SQLException e) {
            e.printStackTrace();
            return new HashMap<>();
        }
    }
}

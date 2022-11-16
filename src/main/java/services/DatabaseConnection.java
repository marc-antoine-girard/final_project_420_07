package services;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;


public class DatabaseConnection {

    //region singleton
    private static DatabaseConnection instance;
    private final BasicDataSource basicDataSource;

    private DatabaseConnection() {
        basicDataSource = new BasicDataSource();
        basicDataSource.setUrl("jdbc:mysql://localhost:3306/farm_db");
        basicDataSource.setUsername("root");
        basicDataSource.setPassword("abc123...");
        basicDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
    }

    public static DatabaseConnection getInstance() {
        if (instance == null)
            instance = new DatabaseConnection();
        return instance;
    }
    //endregion

    public QueryRunner runner() {
        return new QueryRunner(basicDataSource);
    }
}

package services;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;

/*
 * https://commons.apache.org/proper/commons-dbutils/index.html
 * */

public class DatabaseConnection {

    //region singleton
    private static DatabaseConnection instance;

    private DatabaseConnection() {
        createDataSource();
    }

    public static DatabaseConnection getInstance() {
        if (instance == null)
            instance = new DatabaseConnection();
        return instance;
    }
    //endregion

    private BasicDataSource basicDataSource;

    private void createDataSource() {
        basicDataSource = new BasicDataSource();
        basicDataSource.setUrl("jdbc:mysql://localhost:3306/farm_db");
        basicDataSource.setUsername("root");
        basicDataSource.setPassword("abc123...");
        basicDataSource.setDriverClassName("com.mysql.jdbc.Driver");
    }

    public QueryRunner runner() {
        return new QueryRunner(basicDataSource);
    }
}

package gdx.game.template.database;

import com.google.gson.Gson;

import org.pmw.tinylog.Logger;


import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gdx.game.template.Configuration;


public class Mysql {

    //Configuration
    private String host = null;
    private Integer port = null;
    private String user = null;
    private String password = null;
    private String table = null;

    //mysql connection
    private Connection connection = null;
    private Statement statement = null;
    private PreparedStatement setResponseStatement = null;
    private PreparedStatement getResponseStatement = null;
    private PreparedStatement logCreditPrepared = null;
    private PreparedStatement logCreditPreparednew = null;
    private PreparedStatement logSymbolsRequestPrepared = null;
    private PreparedStatement logSymbolsResponsePrepared = null;
	private PreparedStatement getConfigurationForMachineStatement = null;
	private PreparedStatement logFeedbackFlag = null;

	private PreparedStatement logLastSpins = null;
	//    private PreparedStatement

    private Gson gson = new Gson();
    
    private int requestId = 0;
    
    private boolean debug = false;

    public Mysql() {

    }
    public void Connect() {
        try {
            this.host = Configuration.get("mysql.host", "localhost");
            this.port = Configuration.get("mysql.port", 3306);
            this.user = Configuration.get("mysql.user", "");
            this.password = Configuration.get("mysql.password", "");
            this.table = Configuration.get("mysql.table", "");

            // this will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.jdbc.Driver").newInstance();

            // setup the connection with the DB.
            
            connection = DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+table, user, password);

                   /* .getConnection("jdbc:mysql://"+host+":3306/"+table+"?"+"user="+user+"&password="+password);*/

            // statements allow to issue SQL queries to the database
            statement = connection.createStatement();

            //setResponseStatement = connect.prepareStatement("INSERT INTO responses (user, time, json) VALUES (?, NOW(), ?)");
            setResponseStatement = connection.prepareStatement("UPDATE users SET response = ? WHERE id = ?");
            //getResponseStatement = connect.prepareStatement("SELECT * FROM responses WHERE user = ? ORDER BY time LIMIT 1");
            getResponseStatement = connection.prepareStatement("SELECT response FROM users WHERE id = ?");
            Logger.debug("Connected to MySQL");

        } catch (Exception e) {
            Logger.error(e);
            close();
        }
    }



    /**
     * Close connection
     */
    public void close() {
        try {
        	Logger.debug("Closing connection to MySQL");
        	if (setResponseStatement != null) {
        		setResponseStatement.close();
        	}
        	if (getResponseStatement != null) {
        		getResponseStatement.close();
        	}
        	if (logCreditPrepared != null) {
        		logCreditPrepared.close();
        	}
        	if (logCreditPreparednew != null) {
        		logCreditPreparednew.close();
        	}
        	if (logSymbolsRequestPrepared != null) {
        		logSymbolsRequestPrepared.close();
        	}
        	if (logSymbolsResponsePrepared != null) {
        		logSymbolsResponsePrepared.close();
        	}
        	if (statement != null) {
        		statement.close();
        	}
        	if (connection != null) {
        		connection.close();
        	}

        } catch (Exception e) {
        	Logger.error(e);
        }
    }
}

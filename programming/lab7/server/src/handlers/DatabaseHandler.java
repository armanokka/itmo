package handlers;

import entities.Coordinates;
import entities.Location;
import entities.Route;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class DatabaseHandler {
    private Connection connection;
    private Map<Integer, Route> collection = Collections.synchronizedMap(new Hashtable<Integer, Route>());;
    private ArrayList<String> commandHistory = new ArrayList<String>();
    private final LocalDateTime dateCreated = LocalDateTime.now();

    public DatabaseHandler(String host, Integer port, String user, String password, String dbname, String schema) {
        var uri = String.format("jdbc:postgresql://%s:%d/%s?currentSchema=%s", host, port, dbname, schema);

        try {
            Class.forName("org.postgresql.Driver");
        } catch (Exception e) {
            System.out.println("Error loading postgres driver: " + e.getMessage());
        }

        try {
            this.connection = DriverManager.getConnection(uri, user, password);
        } catch (Exception e) {
            System.out.println("Couldn't connect to database: " + e.getMessage());
            return;
        }
        System.out.println("Connection with database established");

        try {
            var count = this.loadCollection();
            System.out.println("Loaded " + count + " routes");
        } catch (Exception e) {
            System.out.println("Couldn't load collection from the database: " + e.getMessage());
        }
    }


    // loadCollection loads collection from the database into this.collection and returns its new size
    private Integer loadCollection() {
        try {
            var stmt = connection.createStatement();
            var results = stmt.executeQuery("SELECT * FROM routes;");
            var route = new Route();
            while (results.next()) {
                route.id = results.getInt(1);
                route.key = results.getInt(2);
                route.name = results.getString(3);
                route.coordinates = new Coordinates(results.getDouble(4), results.getLong(5));
                route.creationDate = new Date(results.getTimestamp(6).getTime());
                route.distance = results.getDouble(7);
                route.from = new Location(results.getString(8), results.getLong(9), results.getDouble(10), results.getDouble(11));
                route.to = new Location(results.getString(12), results.getLong(13), results.getDouble(14), results.getDouble(15));
                route.userID = results.getInt(16);

                this.collection.put(route.key, route);
            }
            results.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println("loadCollection exception: " + e.getMessage());
        } finally {
            return this.collection.size();
        }
    }

    public Integer AuthorizeUser(String login, String password) {
        try {
            var stmt = connection.prepareStatement("SELECT id FROM users WHERE login = ? AND password = ?");
            stmt.setString(1, login);
            stmt.setString(2, password);
            var rs = stmt.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (Exception e) {
//            System.out.println("AuthorizeUser exception: " + e.getMessage());
            return -1;
        }
    }

    public Boolean UserExists(String login) {
        try {
            var stmt = connection.prepareStatement("SELECT exists(SELECT 1 FROM users WHERE login = ?)");
            stmt.setString(1, login);
            var rs = stmt.executeQuery();
            rs.next();
            return rs.getBoolean(1);
        } catch (Exception e) {
            System.out.println("UserExists exception: " + e.getMessage());
            return false;
        }
    }

    public Integer CreateUser(String login, String password) {
        try {
            var stmt = connection.prepareStatement("INSERT  INTO users (login, password) VALUES (?, ?) RETURNING id");
            stmt.setString(1, login);
            stmt.setString(2, password);
            var rs = stmt.executeQuery();
            rs.next();
            var id = rs.getInt(1);
            stmt.close();
            return id;
        } catch (Exception e) {
            System.out.println("CreateUser exception: " + e.getClass().getCanonicalName() + " " + e.getMessage());
            return -1;
        }
    }



    public String info(){
        // Code for generating information about the collection
        String output = "Collection " + this.collection.getClass().getSimpleName();
        output += " containing " + this.collection.size() + " of object Route. \n";
        output += "Collection created on " + dateCreated + ".\n";

        return output;
    }

    public Map<Integer, Route> getCollection() {
        return this.collection;
    }


    public void clearCollection(Integer userID) throws Exception {
        var stmt = connection.prepareStatement("DELETE FROM routes WHERE user_id = ?");
        stmt.setInt(1, userID);
        stmt.execute();
        stmt.close();

        this.collection = collection.entrySet().stream().filter(e -> Objects.equals(e.getValue().userID, userID)).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public void insertRoute(Route route, Integer userID) throws Exception {
        var stmt = connection.prepareStatement("INSERT  INTO routes (key, name, coordinate_x, coordinate_y, date, distance, from_name, from_x, from_y, from_z, to_name, to_x, to_y, to_z, user_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        stmt.setInt(1, route.key);
        stmt.setString(2, route.name);
        stmt.setDouble(3, route.coordinates.getX());
        stmt.setLong(4, route.coordinates.getY());
        stmt.setTimestamp(5, new Timestamp(route.creationDate.getTime()));
        stmt.setDouble(6, route.distance);
        stmt.setString(7, route.from.getName());
        stmt.setLong(8, route.from.getX());
        stmt.setDouble(9, route.from.getY());
        stmt.setDouble(10, route.from.getZ());
        stmt.setString(11, route.to.getName());
        stmt.setLong(12, route.to.getX());
        stmt.setDouble(13, route.to.getY());
        stmt.setDouble(14, route.to.getZ());
        stmt.setInt(15, userID);
        stmt.execute();
        stmt.close();

        collection.put(route.key, route);
    }

    public void removeRoute(Integer key) throws Exception {
        var stmt = connection.prepareStatement("DELETE FROM routes WHERE key = ?");
        stmt.setInt(1, key);
        stmt.execute();
        stmt.close();

        collection.remove(key);
    }

    public ArrayList<String> getCommandHistory() {
        return this.commandHistory;
    }
    public void saveCommandHistory(String command) {
        this.commandHistory.add(command);
        var size = this.commandHistory.size();
        if (size > 6) {
            this.commandHistory.remove(0);
        }
    }


}

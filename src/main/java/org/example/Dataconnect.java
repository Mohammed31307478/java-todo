package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.UUID;

public class Dataconnect {
    private final String DB_URL = "jdbc:mysql://10.0.0.69:3306/moudeh1789_tod";
    private final String USER = "moudeh1789_tod";
    private final String PASSWORD = "r0JiaJ3jF3IYhn7Q";

    public Dataconnect() {
        try {
            DriverManager.getConnection(DB_URL, USER, PASSWORD);
            System.out.println("Connection to Mysql made");
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
    }


    public void insertTodo(String taak, Todoitems newItem) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            String query = "INSERT INTO TODO (id ,taak,done) VALUES (?,?,?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                String uniqueId = UUID.randomUUID().toString();
                newItem.setId(uniqueId);
                preparedStatement.setString(1, uniqueId);
                preparedStatement.setString(2, taak);
                preparedStatement.setBoolean(3, false);

                preparedStatement.executeUpdate();
                System.out.println("Task inserted into database");


            }
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public void deleteTodo(String id) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            String query = "DELETE FROM TODO WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, id);
                preparedStatement.executeUpdate();
                System.out.println("Task deleted from database");
            }
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
    }


    public ArrayList<Todoitems> getalltodos() throws SQLException {
        ArrayList<Todoitems> todos = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            String query = "SELECT taak, done FROM TODO";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        String taak = resultSet.getString("taak");
                        boolean done = resultSet.getBoolean("done");
                        Todoitems todo = new Todoitems(taak, done);
                        todos.add(todo);
                    }
                }
            }
        }
        return todos;
    }


    public void updateTodoCompletionStatus(String taak, boolean completed) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            String query = "UPDATE TODO SET done = ? WHERE taak = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setBoolean(1, completed);
                preparedStatement.setString(2, taak);
                preparedStatement.executeUpdate();
                System.out.println("Task completion status updated in the database");
            }
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public void updateTodoText(String oldText, String newText) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            String query = "UPDATE TODO SET taak = ? WHERE taak = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, newText);
                preparedStatement.setString(2, oldText);
                preparedStatement.executeUpdate();
                System.out.println("Task text updated in the database");
            }
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
    }
}



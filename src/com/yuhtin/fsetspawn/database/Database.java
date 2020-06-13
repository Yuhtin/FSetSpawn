package com.yuhtin.fsetspawn.database;

import com.yuhtin.fsetspawn.core.controller.SpawnerController;
import com.yuhtin.fsetspawn.core.FactionSpawner;
import com.yuhtin.fsetspawn.utils.serialization.Serializer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {

    public Connection connection;
    public String table;

    public Database(Data data, String table) {
        connection = data.openConnection();
        this.table = table;
    }

    public boolean createTable() {
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS " + table + " (`tag` VARCHAR(5) PRIMARY KEY, `data` TEXT);");
            statement.executeUpdate();
            statement.close();

            return true;
        } catch (SQLException e) {
            System.out.println("Não foi possivel criar a tabela");
            e.printStackTrace();
            return false;
        }
    }

    public void loadLocations() {
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement("SELECT * FROM " + table);

            ResultSet result = statement.executeQuery();
            while (result.next()) {
                SpawnerController.addFaction(Serializer.deserialize(result.getString("data")));
            }

        } catch (SQLException exception) {
            System.out.println("Não foi possível carregar um dado do banco de dados.");
        }
    }

    public void create(String tag) {
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement("INSERT INTO " + table + "(`tag`, `data`) VALUES(?,?)");
            statement.setString(1, tag);
            statement.setString(2, "");

            statement.executeUpdate();
            statement.close();
        } catch (SQLException exception) {
            System.out.println("Não foi possível salvar um dado no banco de dados.");
        }
    }

    public void save(String tag, FactionSpawner spawner) {
        PreparedStatement statement;
        try {
            String result = Serializer.serialize(spawner);
            statement = connection.prepareStatement("UPDATE " + table + " SET `data` = ? WHERE `tag` = ?");
            statement.setString(1, result);
            statement.setString(2, tag);

            statement.executeUpdate();
            statement.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("Could not save data to database");
        }
    }

    public void close() {
        if (connection != null) {
            try {
                SpawnerController.factions.forEach(this::save);

                connection.close();
                connection = null;
                System.out.println("Conexão com o banco de dados foi fechada.");
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Não foi possivel fechar a conexão com o banco de dados.");
            }
        }
    }
}

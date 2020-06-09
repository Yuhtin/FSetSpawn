package com.yuhtin.fsetspawn.database.types;

import com.yuhtin.fsetspawn.dao.FactionSpawnerDAO;
import com.yuhtin.fsetspawn.dao.controller.SpawnerController;
import com.yuhtin.fsetspawn.database.Data;
import com.yuhtin.fsetspawn.utils.serialization.Serializer;
import lombok.AllArgsConstructor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class MySQL implements Data {
    private Connection connection;
    String host, user, password, database;
    int port;

    @Override
    public boolean openConnection() {
        String url = "jdbc:mysql://" + host + ":" + port + "/" + database;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Conexao com o MySQL sucedida!");

            return createTable();
        } catch (Exception e) {
            System.out.println("Conexão com o MySQL falhou");
            return false;
        }
    }

    @Override
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

    @Override
    public boolean loadLocations() {
        PreparedStatement statement;
        List<SpawnerController> list = new ArrayList<>();
        try {
            statement = connection.prepareStatement("SELECT * FROM " + table);

            ResultSet result = statement.executeQuery();
            while (result.next()) {
                FactionSpawnerDAO.addFaction(Serializer.deserialize(result.getString("data")));
            }

            return true;
        } catch (SQLException exception) {
            System.out.println("Não foi possível salvar um dado no banco de dados.");
            return false;
        }
    }

    @Override
    public void save(String tag, SpawnerController controller) {
        PreparedStatement statement;
        try {
            String result = Serializer.serialize(controller);
            statement = connection.prepareStatement("INSERT INTO " + table + " (tag, data) VALUES(" + tag + "," + result + ") ON DUPLICATE KEY UPDATE tag=tag, data='" + result + "'");
            statement.executeUpdate();
            statement.close();
        } catch (SQLException exception) {
            System.out.println("Não foi possível salvar um dado no banco de dados.");
        }
    }

    @Override
    public void close() {
        if (connection != null) {
            try {
                FactionSpawnerDAO.factions.forEach(this::save);

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

package com.savin.db.controller;


import com.savin.db.structure.Record;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class DBController implements GuestBookController {

    private Connection conn;
    private Statement statement;

    public DBController(DataSource ds) throws Exception {
        synchronized (DBController.class){
            try {
                this.conn = ds.getConnection();
                statement = conn.createStatement();
                System.out.println("ready to start");
            } catch (SQLException ex) {
                throw new Exception(ex);
            }
            finally {
                conn.close();
            }
        }


    }


    public void addRecord(String postMessage) throws SQLException {
        Record record = new Record(postMessage);
        String s = "INSERT INTO posts(postData,postMessage) VALUES (?,?)";
        PreparedStatement preparedStatement = conn.prepareStatement(s);
        preparedStatement.setString(2, postMessage);
        preparedStatement.setLong(1,record.getData());
        preparedStatement.executeUpdate();
    }


    public List<Record> getRecords() throws SQLException {
        List<Record> records = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery("SELECT id, postData, postMessage FROM posts ORDER BY postDate DESC");
        if(resultSet==null){
            return Collections.EMPTY_LIST;
        }
        while (resultSet.next()) {
            Record record = new Record(resultSet.getInt("id"), resultSet.getLong("postData"), resultSet.getString("postMessage"));
            records.add(record);
        }
        return records;
    }

    public void delete() throws SQLException {
        statement.execute("DELETE FROM POSTS");
    }

    public void close() throws SQLException {
        conn.close();
        statement.close();
    }


}

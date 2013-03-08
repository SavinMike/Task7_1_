package com.savin.db.structure;

import javax.sql.DataSource;
import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Record {
    private Connection conn;
    private Statement st1;
    public Record(DataSource ds) {
        try {
            this.conn = ds.getConnection();
            st1 = conn.createStatement();
            System.out.println("ready to start");

        } catch (Exception e) {
            e.printStackTrace();
        }


    }



    public void addRecord(long date, String postMessage) throws SQLException {
        String query="INSERT INTO posts(postDate,postMessage) " +
                "VALUES (" + date + ",?)";
        PreparedStatement statement=conn.prepareStatement(query);
        statement.setString(1,postMessage);
        statement.executeUpdate();
        System.out.println("Insert done");
    }

    public List<String> getRecords() throws SQLException {
        ResultSet result;
        ArrayList<String>list=new ArrayList<>();

        result = st1.executeQuery("SELECT * FROM posts ORDER BY postDate DESC");
        if(result.wasNull()){
            return Collections.EMPTY_LIST;
        }
        while (result.next()) {
            list.add(result.getString("PostMessage"));

        }
        return list;
    }

    public void delete() throws SQLException {
        st1.execute("DELETE FROM POSTS");
    }

    public void close() throws SQLException {
        conn.close();
    }


}

package com.savin.task7;

import com.savin.db.controller.DBController;
import com.savin.db.controller.GuestBookController;
import com.savin.db.structure.Record;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

public class GuestBook {
    private GuestBookController db;
    public  GuestBook(DataSource sources)throws Exception{
        db = new DBController(sources);
        System.out.println("Guest BOOk init");
    };

    public synchronized void add(String message)throws IOException,SQLException {
        db.addRecord(message.trim());
        System.out.println("Add record:"+message);
    }
    public synchronized List<Record> list() throws SQLException{
        List<Record>ms;
        ms=db.getRecords();
        return ms;
    }
    public synchronized void close(){
        try {
            db.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }


}

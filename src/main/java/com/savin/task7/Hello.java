package com.savin.task7;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet(name = "SimpleServlet",urlPatterns={"/simple"})
public class Hello extends HttpServlet {

    @Resource(name = "jdbc/testDC")
    private DataSource ds;

    private GuestBook guestBook;

  //  @PostConstruct
    public synchronized void initGuestBook(){
        try {
            guestBook=new GuestBook(ds);
        }
        catch (Exception e)  {
            e.printStackTrace();
        }

    }
    @PostConstruct
    public void createDB(){
        try {
        Connection conn = ds.getConnection();
        Statement statement=conn.createStatement();
        statement.executeUpdate(
                "CREATE TABLE posts(id int NOT NULL AUTO_INCREMENT," +
                        "postDate long," +
                        "postMessage varchar(255)," +
                        "PRIMARY KEY (id))\n");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);    //To change body of overridden methods use File | Settings | File Templates.
    }

    protected void doGet(HttpServletRequest req,HttpServletResponse response) throws ServletException,IOException {
        try {
            initGuestBook();
            req.setAttribute("GuestList", guestBook.list());
            req.getRequestDispatcher("WEB-INF/test.jsp").forward(req, response);
            guestBook.close();

        }
        catch (SQLException e){
            guestBook.close();
            e.printStackTrace();
            req.setAttribute("Exception","DB problem not add");
        }
    }
    protected void doPost(HttpServletRequest req,HttpServletResponse response) throws ServletException,IOException{
        try {
            initGuestBook();
            guestBook.add(req.getParameter("Rollno"));
            req.setAttribute("GuestList",guestBook.list());
            req.getRequestDispatcher("WEB-INF/test.jsp").forward(req, response);
            guestBook.close();
        }
        catch (SQLException e){
            guestBook.close();
            req.setAttribute("Exception","DB problem not add");
            e.printStackTrace();
        }

    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.ajax.Composer;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author Nguyen
 */
@WebServlet(name = "UpdateProducts", urlPatterns = {"/UpdateProducts"})
public class UpdateProducts extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UpdateProducts</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateProducts at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>"); 
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //doGet(request, response);
        String driverName = "com.mysql.jdbc.Driver";
        String connectionUrl = "jdbc:mysql://localhost:3306/";
        String dbName = "ecmrproj";
        String userId = "username";
        String password = "password";

        try {
        Class.forName(driverName);
        } catch (ClassNotFoundException e) {
        e.printStackTrace();
        }

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try{ 
        connection = DriverManager.getConnection(connectionUrl+dbName, userId, password);
        statement=connection.createStatement();
        
        String temp  = "";
        String idtemp= "";
            try{
            List<FileItem> formItems= new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
            for (FileItem item : formItems) {            
                if (item.isFormField()) {
                    // Process regular form field (input type="text|radio|checkbox|etc", select, etc).
                    String fieldname = item.getFieldName();
                    String fieldvalue = item.getString();
                    if (fieldname.contentEquals("id")){
                        idtemp = fieldvalue;
                        System.out.println(idtemp);
                    }
                    System.out.println(fieldname+ "//");
                    System.out.println(fieldvalue);
                    temp = temp + fieldvalue + ", ";
                    // ... (do your job here)
                }else {
                    //Upload Image
                if(!item.isFormField()){
    //                        fname = new File(item.getName()).getName();
                            String id = idtemp;
                            id = id + ".jpg";
                            item.write(new File("/Users/Nguyen/Documents/InternationalUniversity/WEB/ECommerceProject/web/images" + File.separator + id));
                        }}
            }}catch (Exception ex) {
                
            }
        System.out.println(temp);
        String[] fieldString = temp.split(", ");
        
        String id = fieldString[0];
            System.out.print(id);
        String name = fieldString[1];
        System.out.print(name);
        String price = fieldString[2];
        System.out.print(price);
        String supplierid = fieldString[3];
        System.out.print(supplierid);
        String sql ="INSERT INTO `ecmrproj`.`product` (`productid`, `productname`, `productprice`, `supplierid`, `info`) VALUES ('"+ id + "', '" + name + "', " + price + ", '" + supplierid + "', '');";        
        statement.executeUpdate(sql);
//        resultSet = statement.executeQuery(sql);
//        while(resultSet.next()){
//            System.out.print(resultSet.getString("productname"));
//            String name=resultSet.getString("productname");
//            String id=resultSet.getString("productid");
//        }
        } catch (Exception e) {
        e.printStackTrace();
        }

        response.sendRedirect("admin_1.jsp");
         
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

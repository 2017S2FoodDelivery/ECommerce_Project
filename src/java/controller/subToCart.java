/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Product;
import repository.ProductRepositoty;
import util.Settings;

/**
 *
 * @author Nguyen
 */
@WebServlet(name = "subCart", urlPatterns = {"/subCart"})
public class subToCart extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        String productId = request.getParameter("productid");
        
        
        
        
        Map<String, String> map = (HashMap<String, String>) Settings.getSessionAttribute(request, "languageCode");

        Product product = ProductRepositoty.getProductById(productId);

        Settings.subToCart(request, product);
        
    }

   
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }

   
    

}

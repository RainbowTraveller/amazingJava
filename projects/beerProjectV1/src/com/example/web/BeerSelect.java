package com.example.web;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import com.example.model.BeerExpert;
import java.util.List;

public class BeerSelect extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{

        BeerExpert be = new BeerExpert();
        //response.setContentType("text/html");
        //PrintWriter out = response.getWriter();
        //out.println("Beer Selection Advice<br>");
        String color =  request.getParameter("color");
        //out.println("<br> Got Beer Color " + be.getBrands(color));
        List<String> recos = be.getBrands(color);
        request.setAttribute("styles", recos);
        RequestDispatcher view = request.getRequestDispatcher("result.jsp");
        view.forward(request, response);
    }
}

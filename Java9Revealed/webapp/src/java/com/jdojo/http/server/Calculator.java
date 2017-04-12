/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdojo.http.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ksharan
 */
@WebServlet(name = "Calculator", urlPatterns = {"/Calculator"})
public class Calculator extends HttpServlet {

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
        response.setContentType("text/plain;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {            
            String n1 = request.getParameter("n1");
            String n2 = request.getParameter("n2");
            String op = request.getParameter("op");
           
            Enumeration<String> em = request.getHeaderNames();
            while(em.hasMoreElements()) {
                String hn = em.nextElement();
                System.out.println("Header: " + hn + "=" + request.getHeader(hn));
            }
            
            if (n1 == null || n2 == null || op == null) {
                out.write("n1, n2, and op parameters must be specified.");
                return;
            }
            
            System.out.println("n1: " + n2 + ", n2=" + n2 + ", op=" + op);
            try {                
                double result = Double.NaN;
                switch(op) {
                    case "+":
                        result = Double.valueOf(n1) + Double.valueOf(n2);
                        out.write(n1 + " + "  + n2 + " = " + result);
                        break;
                    case "-":
                        result = Double.valueOf(n1) - Double.valueOf(n2);
                        out.write(n1 + " - "  + n2 + " = " + result);
                        break;
                    case "*":
                        result = Double.valueOf(n1) * Double.valueOf(n2);
                        out.write(n1 + " * "  + n2 + " = " + result);
                        break;
                    case "/":
                        result = Double.valueOf(n1) / Double.valueOf(n2);
                        out.write(n1 + " / "  + n2 + " = " + result);
                        break;
                    default:
                        out.write("Unrecognized operation '" + op + "'");
                }
            } catch(Throwable t) {
                System.out.println("KKKK: " + out + ", t=" + t);
                //out.write(t.getMessage());
            }            
        }
    }

       
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    @Override
    public String getServletInfo() {
        return "Calculator";
    }// </editor-fold>

}

package com.sathya.productapp;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/DeleteProductServlet")
public class DeleteProductServlet extends HttpServlet {
 
	private static final long serialVersionUID = 1L;

protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// Get the product ID to be deleted from the request
    String productId = request.getParameter("id");
    
         String message;
         boolean status;
	status = ProductDao.deleteProduct(productId);
       
    // Set the message as a request attribute
	if(status)
	{
		message = "Deletion Success";
    request.setAttribute("message", message);
	}
	else {
		message= "Deletion Failed";
		request.setAttribute("message", message);
	}
    

    // Forward back to the product list page
    RequestDispatcher dispatcher = request.getRequestDispatcher("ProductList.jsp");
    dispatcher.forward(request, response);
}
}

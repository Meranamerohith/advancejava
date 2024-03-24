package com.sathya.productapp;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;
@WebServlet("/AddProductServlet")
@MultipartConfig
public class AddProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Read the Data
				String proId=request.getParameter("proId");
				String proName=request.getParameter("proName");
				
				double proPrice=Double.parseDouble(request.getParameter("proPrice"));
				String proBrand=request.getParameter("proBrand");
				String proMadeIn=request.getParameter("proMadeIn");
				
				Date proMfgDate=Date.valueOf(request.getParameter("proMfgDate"));
				Date proExpDate=Date.valueOf(request.getParameter("proExpDate"));
				
				
				Part imagepart = request.getPart("proImage");
				InputStream inputStreamImage = imagepart.getInputStream();
				
				//conversion of InputStream into byte[] 
				byte[] proImage = IOUtils.toByteArray(inputStreamImage);
				
				
				Part audiopart = request.getPart("audioDescription");
				InputStream inputStreamAudio = audiopart.getInputStream();
				
				//conversion of InputStream into byte[] 
				byte[] proAudio = IOUtils.toByteArray(inputStreamAudio);
				
				
				Part videopart = request.getPart("videoDescription");
				InputStream inputStreamVideo = videopart.getInputStream();
				
				//conversion of InputStream into byte[] 
				byte[] proVideo = IOUtils.toByteArray(inputStreamVideo);
				
				
				
					
				//Using above details create the product object
				Product product=new Product();
				
				product.setProId(proId);
				product.setProName(proName);
				product.setProPrice(proPrice);
				product.setProBrand(proBrand);
				product.setProMadeIn(proMadeIn);
				product.setProMfgDate(proMfgDate);
				product.setProExpDate(proExpDate);
				product.setProImage(proImage);
				product.setProaudio(proAudio);
				product.setProvideo(proVideo);
				
				
				//Giving the product object to DAO Layer
				ProductDao productDao=new ProductDao();
				
				int result=productDao.save(product);	
				response.setContentType("text/html");
			//	PrintWriter w=response.getWriter();
				String message;
		        if (result==1) {
		            message = "Data insertion successful.";
		        } else {
		            message = "Data insertion failed.";
		        }
		        request.setAttribute("message", message);
		    
				RequestDispatcher dispatcher = request.getRequestDispatcher("ProductList.jsp");
				dispatcher.forward(request, response);

	}
}
				
				
			


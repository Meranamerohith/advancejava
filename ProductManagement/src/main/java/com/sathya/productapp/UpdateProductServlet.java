package com.sathya.productapp;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;

@WebServlet("/UpdateProductServlet")
@MultipartConfig
public class UpdateProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String productId = request.getParameter("productId");
        ProductDao productDao = new ProductDao();
        Product existingProduct = productDao.findById(productId);

        if (existingProduct != null) {
            try {
                String productName = request.getParameter("productName");
                double productPrice = Double.parseDouble(request.getParameter("productPrice"));
                String productBrand = request.getParameter("productBrand");
                String productMadeIn = request.getParameter("productMadeIn");
                Date productMfgdate = Date.valueOf(request.getParameter("mfgDate"));
                Date productExpdate = Date.valueOf(request.getParameter("expDate"));
                Part part = request.getPart("productImage");
                
                // Check if part is null before using it
                if (part != null) {
                    InputStream inputStream = part.getInputStream();
                    byte[] productImage = IOUtils.toByteArray(inputStream);
                    existingProduct.setProImage(productImage);
                }

                existingProduct.setProName(productName);
                existingProduct.setProPrice(productPrice);
                existingProduct.setProBrand(productBrand);
                existingProduct.setProMadeIn(productMadeIn);
                existingProduct.setProMfgDate(productMfgdate);
                existingProduct.setProExpDate(productExpdate);

                boolean updated = productDao.updateProduct(existingProduct);

                if (updated) {
                    request.setAttribute("successMessage", "Product updated successfully");
                } else {
                    request.setAttribute("errorMessage", "Failed to update product");
                }
            } catch (NumberFormatException e) {
                request.setAttribute("errorMessage", "Invalid product price format");
            } catch (Exception e) {
                request.setAttribute("errorMessage", "An error occurred while updating product");
            }
        } else {
            request.setAttribute("errorMessage", "Product not found");
        }

        response.sendRedirect("ProductList.jsp");
    }

}

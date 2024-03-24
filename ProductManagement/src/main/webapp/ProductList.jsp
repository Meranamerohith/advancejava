<%@page import="com.sathya.productapp.ProductDao" 
import="com.sathya.productapp.*"
import="java.util.Base64"%>
<%@ page language="java" contentType="text/html" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
	<title>Product List</title>
	<link rel="stylesheet" 
		href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" rel="stylesheet" >
	</head>
		
	<body>
	<form action="ProductList.jsp" method="get" class="form-inline justify-content-center mt-3">
            <div class="form-group mx-sm-3 mb-2">
                <label for="searchInput" class="sr-only">Search</label>
                <label for="searchInput" class="sr-only">Search</label>
                <input type="text" class="form-control" id="searchInput" name="search" placeholder="Search">
            </div>
            <button type="submit" class="btn btn-primary mb-2">Search</button>
        </form>
        <a href="add-product.html" class="btn btn-success mt-3">Add Product</a>
		<table class="table table-bordered table-striped" >
		<thead class="thead-dark">
                <tr>
                    <th>Product ID</th>
                    <th>Product Name</th>
                    <th>Product Cost</th>
                    <th>Product Brand</th>
                    <th>Made In</th>
                    <th>Manufacture Date</th>
                    <th>ExpiryDate</th>
                    <th>Image</th>
                    <th>Video</th>
                    <th>Audio</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="product" items="<%=new ProductDao().findAll()%>">

                    <tr>
                        <td>${product.proId}</td>
                        <td>${product.proName}</td>
                        <td>${product.proPrice}</td>
                        <td>${product.proBrand}</td>
                        <td>${product.proMadeIn}</td>
                        <td>${product.proMfgDate}</td>
                        <td>${product.proExpDate}</td>
                        <td>
                            <c:if test="${product.proImage ne null}">
                                <img src="data:image/jpeg;base64,${Base64.getEncoder().encodeToString(product.proImage)}" alt="Product Image" style="max-width: 100px; max-height: 100px;">
                            </c:if>
                        </td>
                        <td>
                            <video width="150" height="120" controls>
                                <source src="${product.provideo}" type="video/mp4">
                            </video>
                        </td>
                        <td>
                            <audio controls>
                                <source src="${product.proaudio}" type="audio/mp3">
                            </audio>
                        </td>
                        <td>
                            <a href="EditProduct.jsp?id=${product.proId}" class="btn btn-primary">Edit</a>
                            <a href="DeleteProductServlet?id=${product.proId}" class="btn btn-danger">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
</body>
</html>

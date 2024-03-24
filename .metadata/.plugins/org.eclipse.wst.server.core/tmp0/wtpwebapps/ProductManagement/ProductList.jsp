<%@page import="com.sathya.productapp.ProductDao" 
import="java.util.Base64"
language="java" contentType="text/html" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>
	<title>Product List</title>
	<link rel="stylesheet" 
		href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" rel="stylesheet" >
	</head>
		
	<body>
		<table class="table table-bordered table-striped" >
		<thead class="thead-dark">
		<tr>
			<th>Product ID</th>
			<th>Product Name</th>
			<th>Product Cost</th>
			<th>Product Brand</th>
			<th>Made IN</th>
			<th>Manufacture Date</th>
			<th>ExpiryDate</th>
			<th>Image</th>			
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
            <img src="data:image/jpeg;base64,${Base64.getEncoder().encodeToString(product.proImage)}" alt="Product Image" style="max-width: 100px; max-height: 100px;">
        </td>		
			</tr>
		</c:forEach>
		
		</tbody>
		</table>
	</body>
</html>
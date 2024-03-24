<%@page import="com.sathya.productapp.ProductDao"%>
<%@page import="com.sathya.productapp.Product"%>
<%@page import="java.util.Base64"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Edit Product</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css">
</head>
<body>
    <h1>Edit Product</h1>
    <%
        String productId = request.getParameter("id");
        if (productId != null) {
            Product product = new ProductDao().findById(productId);
            if (product != null) {
    %>
                <form action="UpdateProductServlet" method="post" enctype="multipart/form-data" onsubmit="return validateForm()">
                    <input type="hidden" name="productId" id="proId" value="<%=product.getProId()%>">
                    <div class="form-group">
                        <label for="productName">Product Name:</label>
                        <input type="text" class="form-control-sm" id="proName" name="productName" value="<%=product.getProName()%>" required>
                    </div>
                    <div class="form-group">
                        <label for="productPrice">Product Price:</label>
                        <input type="text" class="form-control-sm" id="proPrice" name="productPrice" value="<%=product.getProPrice()%>" required>
                    </div>
                    <div class="form-group">
                        <label for="productBrand">Product Brand:</label>
                        <input type="text" class="form-control-sm" id="proBrand" name="productBrand" value="<%=product.getProBrand()%>" required>
                    </div>
                    <div class="form-group">
                        <label for="productMadeIn">Product Made In:</label>
                        <input type="text" class="form-control-sm" id="proMadeIn" name="productMadeIn" value="<%=product.getProMadeIn()%>" required>
                    </div>
                    <div class="form-group">
                        <label for="mfgDate">Manufacturing Date:</label>
                        <input type="date" class="form-control-sm" id="mfgDate" name="mfgDate" value="<%=product.getProMfgDate()%>" required>
                    </div>
                    <div class="form-group">
                        <label for="expDate">ExpiryDate:</label>
                        <input type="date" class="form-control-sm" id="expDate" name="expDate" value="<%=product.getProExpDate()%>" required>
                    </div>
                    
                    <div class="form-group">
                          <label class="font-italic font-weight-bold" for="proIamge">Product Image:</label>
                          <input type="file" class="form-control-file-sm" id="proImage" name="proImage" accept="image/*">
                          <c:if test="${product.proImage ne null}">
                          <img src="data:image/jpeg;base64,${Base64.getEncoder().encodeToString(product.proImage)}" alt="Product Image" style="max-width: 100px; max-height: 100px;">
                          </c:if>
                    </div>
                   
                   
                   <div class="form-group">
                             <label for="productaudio">Current Product_description audio:</label><br>
                                 <% if(product.getProaudio() != null) { %>
                                <img src="data:audio/mpeg;base64, <%= Base64.getEncoder().encodeToString(product.getProaudio()) %>" alt="Product Audio" style="max-width: 100px; max-height: 100px;">
                               <% } else { %>
                              <p>No Audio available</p>
                              <% } %>
                               <br>
                             <label for="existingAudio">Upload New Audio:</label>
                            <input type="file" class="form-control-sm" id="productAudio" name="productAudio">
                   </div>
                   
                   
                   
                   <div class="form-group">
                             <label for="productImage">Current Product_description Video:</label><br>
                                 <% if(product.getProvideo() != null) { %>
                                <img src="data:video/mp4;base64, <%= Base64.getEncoder().encodeToString(product.getProvideo()) %>" alt="Product video" style="max-width: 100px; max-height: 100px;">
                               <% } else { %>
                              <p>No Video available</p>
                              <% } %>
                               <br>
                             <label for="existingVideo">Upload New Video:</label>
                            <input type="file" class="form-control-sm" id="productVideo" name="productVideo">
                   </div>

                    <input type="submit" class="btn btn-primary" value="Update">
                </form>
    <%
            } else {
                out.println("Product not found");
            }
        } else {
            out.println("No product ID provided");
        }
    %>
    <!-- JavaScript Validation -->
    <script src="Editformvalidation.js"></script>
</body>
</html>
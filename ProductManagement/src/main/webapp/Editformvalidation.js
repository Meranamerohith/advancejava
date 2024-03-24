function validateForm() {
    // Read the form data
    var proId = document.getElementById("proId").value.trim();
    var proName = document.getElementById("proName").value.trim();
    var proPrice = document.getElementById("proPrice").value.trim();
    var proBrand = document.getElementById("proBrand").value.trim();
    var proMadeIn = document.getElementById("proMadeIn").value.trim();
    var mfgDate = document.getElementById("mfgDate").value.trim();
    var expDate = document.getElementById("expDate").value.trim();

    // Check if any field is empty
    if (proId === "" || proName === "" || proPrice === "" || proBrand === "" || proMadeIn === "" || mfgDate === "" || expDate === "") {
        alert("All fields must be filled out");
        return false;
    }

    // Check if proPrice is a non-negative value
    if (parseFloat(proPrice) < 0) {
        alert("Product Price must be a non-negative value");
        return false;
    }

    // Check if proName and proBrand are less than 50 characters
    if (proName.length > 50 || proBrand.length > 50) {
        alert("Product name and Product Brand must be less than 50 characters");
        return false;
    }

    // Convert mfgDate and expDate into date objects
    var manufacturingDateObj = new Date(mfgDate);
    var expiryDateObj = new Date(expDate);

    // Check if manufacturingDateObj is greater than expiryDateObj
    if (manufacturingDateObj > expiryDateObj) {
        alert("Manufacturing date cannot be greater than Expiry date.");
        return false;
    }

    return true;
}

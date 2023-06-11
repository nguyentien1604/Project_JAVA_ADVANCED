listAccount=[];
function handleCreateNewProduct(Params) {
    var newName = $("#Usenname").val();
    var newfullname = $("#Fullname").val();
    var newemail = $("#email").val();
    var newPassword = $("#password_id").val();
    
    var newTel = $("#Phone").val();
    var newAddress = $("#Address").val();
    var newCity = $("#City").val();
    var newCountry = $("#Country").val();
  
    var newAccount = {
      username: newName,
      fullname: newfullname,
      email: newemail,
      password: newPassword,
      
      phone: newTel,
      address: newAddress,
      city: newCity,
      country: newCountry,
      
    };
    console.log("newProduct: ", newAccount);
  
    //Add sản phẩm vào listProduct
    //Thực hiện gọi API
    $.ajax({
      type: "POST",
      url: "http://localhost:8080/accountRegisters",
      beforeSend: function (xhr) {
        xhr.setRequestHeader("Authorization", "Basic " + btoa("Admin:123456"));
      },
      data: JSON.stringify(newAccount),
      // dataType: "json",
      contentType: "application/json; charset=UTF-8",
      success: function (response, status) {
        listAccount=response;
        if (status === "success") {
        } else {
          console.log("Error when loading data!!!");
          return;
        }
      },
    });
    handleResetFormAccount();
  }
  
  function handleResetFormAccount()
  {
     $("#Usenname").val("");
     $("#Fullname").val("");
     $("#email").val("");
     $("#password_id").val("");
     $("#Phone").val("");
     $("#Address").val("");
     $("#City").val("");
     $("#Country").val("");
     $("#re_password_id").val("");
  }

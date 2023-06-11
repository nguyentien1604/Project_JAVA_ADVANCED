var listAccount = [];
var listProduct = [];
var listOrderDetail = [];

var listSupplier = [];
var listCategory = [];
var idclickCart;
var cart = [];
var currentItem = "Xiaomi";
var v_username_login = localStorage.getItem("USERNAME");
var v_password_login = localStorage.getItem("PASSWORD");


var search="";
var currentpage = 1;
var currentsize = 6;
var totalpage;
var currentSelect;
var idProductUpdate;

var startIndex = (currentpage - 1) * currentsize; // tính startIndex
var endIndex = startIndex + (currentsize *totalpage); 

getlistProduct();

// $(function () {
//   loadComponentAdmin();
// });

//Load các thành phần trong trang Home Page
// function loadComponentAdmin() {
//   $(".SideBarSection").load("./SideBarAdmin.html");

// }

// $(function () {
//   loadComponentAdmin();
// });

// //Load các thành phần trong trang Home Page
// function loadComponentAdmin() {
//    $(".MenuSection").load("./Menu.html");
//   // $(".ProductAdminSection").load("./ContainProduct.html");
// }






function getlistProduct(param) {
  // var v_username_login = localStorage.getItem("username_login");
  // var v_password_login = localStorage.getItem("password_login");

  $.ajax({
    type: "get",
    url: `http://localhost:8080/products?page=${currentpage}&size=${currentsize}&search=${search}`,
    data: "data",
    beforeSend: function (xhr) {
      xhr.setRequestHeader(
        "Authorization",
        "Basic " + btoa(v_username_login + ":" + v_password_login)
      );
    },
    success: function (response) {
      listProduct = response.content;
      document.getElementById("usernameLogin").innerHTML=localStorage.getItem("USERNAME");
      //3.Hien thi danh sach Product
      $("#product_ID").empty();
      for (var index = 0; index < listProduct.length; index++) {
        $("#product_ID").append(
          `<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 filter-box-div" style="text-align:left;width: 300px;height: 500px;">
          <!-- Ảnh -->

          <div class="row ">

              <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
              <img src="../Project_Railway65_JavaAdvanced/Asset/img/${
                listProduct[index].picture
              }" alt="" style="width:100%;height: 100%;" id="imgcart">
              </div>
          </div>
          <!--Ten San Pham-->
          <div class="row">
              <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                  <h3 style="font-weight: bold;" id="productnameCart">${
                    listProduct[index].productname
                  }</h3>
              </div>
          </div>
          <!-- Hang San Xuat -->

          <div class="row">

              <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                  <h4>Hãng Sản Xuất: ${listProduct[index].supplierName}</h4>
              </div>
          </div>
          <!-- Danh gia -->

          <div class="row">

              <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                  <ul class="rating"
                      style="list-style: none; display: flex; font-size: 20px; padding-inline-start: 0px">
                      ${showStarRating(listProduct[index].rating)}
                  </ul>
              </div>

          </div>


          <div class="row">

            
          <!-- Them Gio Hang -->
          <div class="row">

              <div class="col-xs-8 col-sm-8 col-md-8 col-lg-8" id="priceCart">
                  <h4>${formatCurrnecy(
                    listProduct[index].unitPrice,
                    "vnd"
                  )}</h4>
              </div>

              <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
                  <button type="button" class="btn add-to-cart" style="border: 0px" 
                   >
                      <i class="fas fa-cart-plus add-to-cart" style="color: green; font-size: 25px" 
                      onclick="addToCart(${listProduct[index].productid})"></i>
                  </button>
                
              </div>

          </div>
          <br>
          <br>
          <br>
      </div> `
        );
        totalpage = response.totalPages;
        pagination(totalpage);
      }
    },
  });
}
console.log();
function showStarRating(rateParam) {
  let starRating = "";
  //Hiển thị đánh giá sao
  for (var index = 1; index < rateParam; index++) {
    starRating += ` <li>
    <i class="fa fa-star selected" style="color: orange"></i>
 </li>`;
  }
  //Hiển thị sao không được đánh giá
  for (var index = 1; index < 5 - rateParam; index++) {
    starRating += `<li>
    <i class="fa fa-star"></i>
 </li>`;
  }
  return starRating;
}
function formatCurrnecy(n, currency) {
  return (
    n.toFixed(0).replace(/./g, function (c, i, a) {
      //trong đó i là index, c là chuỗi đang xét và a là chuỗi gốc
      return i > 0 && c !== "." && (a.length - i) % 3 === 0 ? "." + c : c;
    }) +
    " " +
    currency
  );
}

function pagination(totalpage) {
  //Hiển thị nút previous
  $("#pagination_Id").empty();
  //hiển thị nút previous
  if (currentpage > 1) {
    $("#pagination_Id").append(`
    <li class="page-item"><a href="#" class="page-link" onclick="handlePrevious()">Previous</a></li>
  `);
  }

  for (var index = currentpage; index <= totalpage; index++) {
    if (index === currentpage) {
      $("#pagination_Id").append(
        `<li class="active"><a href="#" onclick="handleChangePage(${index})">${index}</a></li>`
      );
    } else {
      $("#pagination_Id").append(
        `<li ><a href="#" onclick="handleChangePage(${index})">${index}</a></li>`
      );
    }
  }
  //Hiển thị nút Next
  if (currentpage < totalpage) {
    $("#pagination_Id").append(` 
    <li class="page-item"><a href="#" class="page-link" onclick="handleNext()">Next</a></li>
     `);
  }
}
function handleChangePage(pageParam) {
  // Kiểm tra xem trang có phải trang hiện tại không. Nếu là trang hiện tại thì không làm gì
  if (currentpage === pageParam) {
    return;
  } else {
    currentpage = pageParam;
    getlistProduct();
  }
}
function handlePrevious() {
  currentpage = currentpage - 1;
  getlistProduct();
}
function handleNext() {
  currentpage = currentpage + 1;
  getlistProduct();
}

function handleHome() {
  window.open("Home.html", "_self");
}
function handleRegister() {
  window.open("dangky.html", "_self");
}

//Dành Cho nút Login
$("#login").click(function () {
  //Get username and password
  var v_username = $("#username").val();
  var v_password = $("#password").val();
// Todo validate
if(!v_username)  
{
  showErrorMessage("Please input Username");
  return;
}
if(!v_password)  
{
  showErrorMessage("Please input Passowrd");
  return;
}
// if(!v_username||v_username.length<6||v_username.length>50||v_password.length<6||v_password>50){
//     showErrorMessage("Login fail!");
//     return;
//   }
//Call API
  $.ajax({
    type: "GET",
    url: `http://localhost:8080/login`,
    // dataType: "json",
    contentType: "application/json; charset=UTF-8",
    beforeSend: function (xhr) {
      xhr.setRequestHeader(
        "Authorization",
        "Basic " + btoa(v_username + ":" + v_password)
      );
    },
    success: function (response, status,user) {
      if (status == "success") {
        localStorage.setItem("USERNAME", v_username);
        localStorage.setItem("PASSWORD", v_password);
        $("#login").hide();
        $("#logout").show();
        window.open("Home.html", "_self");
      } else {
        alert("Đăng nhập thất bại! Vui lòng thử lại.");
      }
      if (user !== null) {
        $('body').attr('isLoggedIn', true);
        $('#username').text(user.username);
      } else {
        $('body').attr('isLoggedIn', false);
      }
    },
    error(jqXHR, textStatus, errorThrown) {
      if (jqXHR.status == 401) {
        showErrorMessage("Login fail!");
      } else {
        console.log(jqXHR);
        console.log(textStatus);
        console.log(errorThrown);
      }
    },
  });
});

function showErrorMessage(message)
{
  document.getElementById("loginErrorMessage").style.display="block";
  document.getElementById("loginErrorMessage").innerHTML=message;
}
function hideErrorMessage()
{
  document.getElementById("loginErrorMessage").style.display="none";

}

//Dành cho nút Log Out
$("#logout").click(function () {
  localStorage.removeItem("USERNAME");
  localStorage.removeItem("PASSWORD");
  $("#logout").hide();
  $("#login").show();
  window.open("Login.html", "_self");
});

function getlistSupplier(param) {
  listSupplier = [];
  $.ajax({
    type: "GET",
    url: "http://localhost:8080/suppliers",
    // data: "data",
    dataType: "json",
    beforeSend: function (xhr) {
      xhr.setRequestHeader(
        "Authorization",
        "Basic " + btoa(v_username_login + ":" + v_password_login)
      );
    },
    success: function (response) {
      listSupplier = response;
      // for (var index = 0; index < listSupplier.length; index++) {
      //   $("#Supplier_Id").append(
      //     `<option value="${listSupplier[index].supplierId}">${listSupplier[index].supplierName}</option>`
      //   );

      //   //Update
      //   $("#SupplierUpDate").append(
      //     `<option value="${listSupplier[index].supplierId}">${listSupplier[index].supplierName}</option>}`
      //   );
      // }
    },
  });
}

function addToCart(cartParam)
{
  idProductUpdate=cartParam
  var indexcart=listProduct.findIndex((product)=>product.productid==idProductUpdate);
//   if($(".item-in-cart").toArray().map(el=>el.getAttribute("data-id")).includes(indexcarts)){

//     alert("Already Added")

// }else{
  function isLogin() {
  if (localStorage.getItem("USERNAME")!=null) {
    return true;
  } else {
    return false;
  }
}
  if (!isLogin()) {
    window.location.replace("Login.html");
    return;
  }else
  {
cart= $("#cart").append(
    `<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 filter-box-div" style="text-align:left;width: 300px;height: 500px;">
    <!-- Ảnh -->

    <div class="row ">
                <div class="card border-0 item-in-cart" data-id="${listProduct[indexcart].productid}">
                <div class="card-body">
                <div class="d-flex justify-content-between align-items-end product">
                <img src="../Project_Railway65_JavaAdvanced/Asset/img/${
                  listProduct[indexcart].picture
                }" alt="" style="width:100%;height: 100%;" id="imgcart" class="product" style="width:50px;">
                    <button class="btn btn-outline-danger remove-from-cart">
                        <i class="fas fa-trash-alt"></i>
                    </button>
                </div>
                    <p class="mt-3">
                        ${listProduct[indexcart].productname}
                    </p>
                    <div class="d-flex justify-content-between align-items-end">
                        <div class="form-row">
                            <button class="btn btn-outline-primary quantity-minus">
                                <i class="fas fa-minus"></i>
                            </button>
                            <input type="number" class="form-control w-25 mx-2 item-in-cart-cost" unitPrice="${formatCurrnecy(
                              listProduct[indexcart].unitPrice,
                              "vnd"
                            )}" value="1" min="1">
                            <button class="btn btn-outline-primary quantity-plus" >
                                <i class="fas fa-plus" onclick="increaseValue()"></i>
                            </button>
                        </div>
                        <p class="mb-0">$ <span class="item-in-cart-cost">${formatCurrnecy(
                          listProduct[indexcart].unitPrice,
                          "vnd"
                        )}</span></p>
                    </div>
                    <hr>
                </div>
        </div>
</div> `

);}
// cartTotal();
// $('#modal-idCart').modal("show");
// 
// localStorage.setItem('cartItem',cart);
cartTotal();
}

function cartModal()
{
  $('#modal-idCart').modal("show");
}

$("#category").on("change",function () {

  let selectedCategory = $(this).val();
  console.log(typeof selectedCategory);

  if(selectedCategory != 0){
      let filterProducts = listProduct.filter(product=>{
          if(product.categoryname === selectedCategory){
              return product;
          }
      })

      getlistProduct(filterProducts);
  }else{
      getlistProduct(listProduct);
  }
})

function cartTotal(){

  let count = $(".item-in-cart-cost").length;

  $(".item-in-cart-count").html(count);

  if(count>0){
      let totalCost = $(".item-in-cart-cost").toArray().map(el=>el.innerHTML).reduce((x,y)=>Number(x)+Number(y));
      // console.log(typeof totalCost);
      $(".total").html(`

          <div class="d-flex justify-content-between font-weight-bold px-3">
              <h4>Total</h4>
              <h4>$ <span class="cart-cost-total">${Number(totalCost).toFixed(2)}</span></h4>
          </div>

      `)
  }else{
      $(".total").html("empty cart")
  }

}

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



//lọc dữ liệu
function handleSelect(selectparam)
{
 var selectProduct=selectparam.value;
 console.log(selectProduct);
 if (search != selectProduct) {
  search = selectProduct;
  getlistProduct(search);
}else
{
  search="*";
  getlistProduct(search);
}
}


function filterProduct(buttonValue) {
  var filteredProducts = listProduct.filter(function(product) {
    return product.supplierName === buttonValue; // Lọc sản phẩm có supplierName bằng với giá trị của button
  });
  // Xóa danh sách sản phẩm hiện tại
  $("#product_ID").empty();
  console.log(filteredProducts);
  // Hiển thị danh sách sản phẩm đã lọc
  for (var i = 0; i < filteredProducts.length; i++) {
    //Thêm sản phẩm vào list
    $("#product_ID").append(`<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 filter-box-div" style="text-align:left;width: 300px;height: 500px;">
    <!-- Ảnh -->
    <div class="row ">
        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
        <img src="../Project_Railway65_JavaAdvanced/Asset/img/${
          filteredProducts[i].picture
        }" alt="" style="width:100%;height: 100%;" id="imgcart">
        </div>
    </div>
    <!--Ten San Pham-->
    <div class="row">
        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
            <h3 style="font-weight: bold;" id="productnameCart">${
              filteredProducts[i].productname
            }</h3>
        </div>
    </div>
    <!-- Hang San Xuat -->
    <div class="row">
        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
            <h4>Hãng Sản Xuất: ${
              filteredProducts[i].supplierName
            }</h4>
        </div>
    </div>
    <!-- Danh gia -->
    <div class="row">
        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
            <ul class="rating"
                style="list-style: none; display: flex; font-size: 20px; padding-inline-start: 0px">
                ${showStarRating(filteredProducts[i].rating)}
            </ul>
        </div>
    </div>
    <div class="row">
    <!-- Them Gio Hang -->
    <div class="row">
        <div class="col-xs-8 col-sm-8 col-md-8 col-lg-8" id="priceCart">
            <h4>${formatCurrnecy(
              filteredProducts[i].unitPrice,
              "vnd"
            )}</h4>
        </div>

        <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
            <button type="button" class="btn add-to-cart" style="border: 0px"  >
                <i class="fas fa-cart-plus add-to-cart" style="color: green; font-size: 25px" onclick="addToCart(${
                  filteredProducts[i].productid
                })"></i>
            </button>

        </div>

    </div>
    <br>
    <br>
    <br>
</div>`);

  }
}


// function filterCategory(buttonValue) {
//   var filterCategory = listProduct.filter(function(product) {
//     return product.categoryName === buttonValue; // Lọc sản phẩm có categoryname bằng với giá trị của button
//   });

//   console.log(filterCategory);
//   // Xóa danh sách sản phẩm hiện tại
//   $("#product_ID").empty();
//   console.log(endIndex);

//   // Hiển thị danh sách sản phẩm đã lọc
//   for (var i = startIndex; i < endIndex&&i<filterCategory.length; i++) {
//     //Thêm sản phẩm vào list
//     $("#product_ID").append(`<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 filter-box-div" style="text-align:left;width: 300px;height: 500px;">
//     <!-- Ảnh -->
//     <div class="row ">
//         <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
//         <img src="../Project_Railway65_JavaAdvanced/Asset/img/${
//           filterCategory[i].picture
//         }" alt="" style="width:100%;height: 100%;" id="imgcart">
//         </div>
//     </div>
//     <!--Ten San Pham-->
//     <div class="row">
//         <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
//             <h3 style="font-weight: bold;" id="productnameCart">${
//               filterCategory[i].productname
//             }</h3>
//         </div>
//     </div>
//     <!-- Hang San Xuat -->
//     <div class="row">
//         <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
//             <h4>Hãng Sản Xuất: ${
//               filterCategory[i].supplierName
//             }</h4>
//         </div>
//     </div>
//     <!-- Danh gia -->
//     <div class="row">
//         <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
//             <ul class="rating"
//                 style="list-style: none; display: flex; font-size: 20px; padding-inline-start: 0px">
//                 ${showStarRating(filterCategory[i].rating)}
//             </ul>
//         </div>
//     </div>
//     <div class="row">
//     <!-- Them Gio Hang -->
//     <div class="row">
//         <div class="col-xs-8 col-sm-8 col-md-8 col-lg-8" id="priceCart">
//             <h4>${formatCurrnecy(
//               filterCategory[i].unitPrice,
//               "vnd"
//             )}</h4>
//         </div>

//         <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
//             <button type="button" class="btn add-to-cart" style="border: 0px"  >
//                 <i class="fas fa-cart-plus add-to-cart" style="color: green; font-size: 25px" onclick="addToCart(${
//                   filterCategory[i].productid
//                 })"></i>
//             </button>

//         </div>

//     </div>
//     <br>
//     <br>
//     <br>
// </div>`);
// // getlistProduct(filterCategory);
//   }
// }


function filterProductsByCategory(category) {
  // tìm sản phẩm trong danh sách
  var productIndex = listProduct.findIndex(function(product) {
    return product.categoryName === category;
  });

  // nếu không tìm thấy sản phẩm thì thông báo
  if (productIndex === -1) {
    alert("Không tìm thấy sản phẩm trong danh sách!");
    return;
  }

  // tính toán số trang và chỉ số sản phẩm trên trang đó
  var numProductsPerPage = 6; // số sản phẩm trên mỗi trang
  var productPage = Math.ceil((productIndex + 1) / numProductsPerPage); // trang chứa sản phẩm
  var productIndexOnPage = (productIndex + 1) % numProductsPerPage; // chỉ số của sản phẩm trên trang đó
  if (productIndexOnPage === 0) {
    productIndexOnPage = numProductsPerPage;
  }

  // hiển thị trang chứa sản phẩm đó
  pagination(productPage);
  getlistProduct(productIndexOnPage);
}











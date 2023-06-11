//Khai Báo Danh sách sản phẩm, nhà cung cấp, loại hàng hóa
listProduct = [];
listSupplier = [];
listCategory = [];
//

// Khai báo các biến sử dụng trong phân trang
var currentPage = 1;
var currentSize = 6;

// Lưu thông tin tổng số trang
var totalPages;

// Khai báo biến dùng trong Sort dữ liệu
var sortField = "";
var directionSort = "asc";

// Khai báo biến lưu dữ liệu Search
var search = "";
//
var idProductUpdate = "";
var idAccountUpdate = "";

$(function () {
  loadComponentAdmin();
});

//Load các thành phần trong trang Home Page
function loadComponentAdmin() {
  $(".SideBarSection").load("./SideBarAdmin.html");
  $(".MenuSection").load("../Menu.html", function () {
    document.getElementById("usernameLogin").innerHTML =
      localStorage.getItem("USERNAME");
  });

  // $(".ProductAdminSection").load("./ContainProduct.html");
}
function handleShowProduct() {
  // Load nội dung ContainProduct
  $(".ProductAdminSection").load(
    "./ContainProduct.html",
    "data",
    function (response, status, request) {
      // Sau khi load thành công giao diện mới thực thi các hàm Callback trong này.
      getlistProduct();
      getlistSupplier();
      getListCategory();

      $(".ProductAdminSection").css("display", "block");
      $(".AccountSection").css("display", "none");
      $("#Id").attr("disabled", "disabled");
    }
  );
}

function getListCategory() {
  $.ajax({
    type: "get",
    url: "http://localhost:8080/categories",
    // data: "data",
    beforeSend: function (xhr) {
      xhr.setRequestHeader("Authorization", "Basic " + btoa("Admin:123456"));
    },
    dataType: "json",
    success: function (response, status) {
      listCategory = response;
      for (var index = 0; index < listCategory.length; index++) {
        //Create
        $("#Category").append(
          `<option value="${listCategory[index].categoryid}">
            ${listCategory[index].categoryname}</option>}`
        );
        //Update
        $("#CategoryUpDate").append(
          `<option value="${listCategory[index].categoryid}">
                    ${listCategory[index].categoryname}</option>}`
        );
      }
    },
  });
}
//Phân Trang sản phẩm
function pagination(totalpage) {
  //Hiển thị nút previous
  $("#pagination_Id").empty();
  //hiển thị nút previous
  if (currentPage > 1) {
    $("#pagination_Id").append(`
    <li class="page-item"><a href="#" class="page-link" onclick="handlePrevious()">Previous</a></li>
  `);
  }

  for (var index = 1; index <= totalpage; index++) {
    if (index === currentPage) {
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
  if (currentPage < totalpage) {
    $("#pagination_Id").append(` 
    <li class="page-item"><a href="#" class="page-link" onclick="handleNext()">Next</a></li>
     `);
  }
}
//Xử lý chuyển trang
function handleChangePage(pageParam) {
  // Kiểm tra xem trang có phải trang hiện tại không. Nếu là trang hiện tại thì không làm gì
  if (currentPage === pageParam) {
    return;
  } else {
    currentPage = pageParam;
    getlistProduct();
  }
}
function handlePrevious() {
  currentPage -= 1;
  getlistProduct();
}
function handleNext() {
  currentPage += 1;
  getlistProduct();
}

//Gọi sản phẩm
function getlistProduct(param) {
  $.ajax({
    type: "GET",
    url: `http://localhost:8080/products?page=${currentPage}&size=${currentSize}&sort=${sortField},${directionSort}&search=${search}`,
    data: "data",
    beforeSend: function (xhr) {
      xhr.setRequestHeader("Authorization", "Basic " + btoa("Admin:123456"));
    },
    // dataType: "dataType",
    success: function (response) {
      listProduct = response.content;

      //3.Hien thi danh sach Product
      $("#tbProduct").empty();
      for (var index = 0; index < listProduct.length; index++) {
        $("#tbProduct").append(
          ` <tr>
            <td>${listProduct[index].productid}</td>
            <td>${listProduct[index].productname}</td>
            <td>${listProduct[index].categoryName}</td>
            <td>${listProduct[index].supplierName}</td>
            <td>${listProduct[index].quantity}</td>
            <td>${formatCurrency(listProduct[index].unitPrice, "vnd")}</td>
            <td>${listProduct[index].description}</td>
            <td>${listProduct[index].rating}</td>
            <td>${listProduct[index].picture}</td>
            <td>${listProduct[index].type}</td>
            <td>
              <button type="button" class="btn btn-warning"  onclick="handleEdit(${
                listProduct[index].productid
              })">Edit</button>
            </td>
            <td>
              <button type="button" class="btn btn-danger" onclick="handleDelete(${
                listProduct[index].productid
              })">Delete</button>
            </td>
        </tr>
        `
        );
        totalpage = response.totalPages;
        pagination(totalpage);
      }
    },
  });
}
//Hàm chuyển chuỗi sang currency
function formatCurrency(n, currency) {
  return (
    n.toFixed(0).replace(/./g, function (c, i, a) {
      return i > 0 && c !== "," && (a.length - i) % 3 === 0 ? "." + c : c;
    }) +
    " " +
    currency
  );
}

//Tạo sản phẩm mới
function handleCreateNewProduct(Params) {
  var newName = $("#Name").val();
  var newCategory = $("#Category").val();
  var newSupplierID = $("#Supplier_Id").val();
  var newquantity = $("#Unitquantity").val();
  var newUnitPrice = $("#UnitPrice").val();
  var newDescription = $("#Description").val();
  var newRating = $("#Rating").val();
  var newPicture = getPicture($("#Picture").val());
  var newType = $("#Type").val();

  var newProduct = {
    productname: newName,
    categoryID: newCategory,
    supplierID: newSupplierID,
    quantity: newquantity,
    unitPrice: newUnitPrice,
    description: newDescription,
    rating: newRating,
    picture: newPicture,
    type: newType,
  };
  console.log("newProduct: ", newProduct);

  //Add sản phẩm vào listProduct
  //Thực hiện gọi API
  $.ajax({
    type: "POST",
    url: "http://localhost:8080/products",
    beforeSend: function (xhr) {
      xhr.setRequestHeader("Authorization", "Basic " + btoa("Admin:123456"));
    },
    data: JSON.stringify(newProduct),
    // dataType: "json",
    contentType: "application/json; charset=UTF-8",
    success: function (response, status) {
      if (status === "success") {
        getlistProduct();
      } else {
        console.log("Error when loading data!!!");
        return;
      }
    },
  });
  handleResetForm();
  getlistProduct();
  // $("#modal-id").modal("hide");
}

function handleResetForm() {
  $("#Name").val("");
  $("#CategoryId").val("");
  $("#SupplierId").val("");
  $("#Unitquantity").val("");
  $("#UnitPrice").val("");
  $("#Description").val("");
  $("#Rating").val("");
  $("#Picture").val("");
  $("#Type").val("");
}
function getPicture(pathImg) {
  var itemArray = pathImg.split("\\");

  var pictureName = itemArray[itemArray.length - 1];

  return pictureName;
}

//Hàm edit sản phẩm
function handleEdit(indexEdit) {
  idProductUpdate = indexEdit;

  var indexNew = listProduct.findIndex(
    (product) => product.productid == idProductUpdate
  );
  var v_CategoryId = listCategory.find(
    (categoryUpdate) =>
      categoryUpdate.categoryname == listProduct[indexNew].categoryName
  );

  //   let v_CategoryId;
  // for (let i = 0; index < listCategory.length; i++) {
  //   if (listCategory[index].categoryname === listProductEdit.categoryname) {
  //     v_CategoryId = listCategory[i].categoryid;

  //   }
  // }
  var v_SupplierId = listSupplier.find(
    (supplier) => supplier.supplierName == listProduct[indexNew].supplierName
  );

  // console.log(v_CategoryId);
  console.log(v_SupplierId);

  var listProductUpdate = listProduct[indexNew];
  // $("#IdUpdate").attr("disabled", "disabled");
  $("#IdUpdate").val(listProductUpdate.productid);
  $("#NameUpdate").val(listProductUpdate.productname);
  $("#CategoryUpDate").val(v_CategoryId.categoryid);
  $("#SupplierUpDate").val(v_SupplierId.supplierId);
  $("#quantityUpDate").val(listProductUpdate.quantity);
  $("#UnitPriceUpdate").val(listProductUpdate.unitPrice);
  $("#DescriptionUpDate").val(listProductUpdate.description);
  $("#RatingUpdate").val(listProductUpdate.rating);
  $("#TypeUpdate").val(listProductUpdate.type);

  $("#modal-idUpdate").modal("show");
}
//Cập nhật sản phẩm
function handleUpdateProduct() {
  var indexUpdate = listProduct.findIndex(
    (product) => product.productid == idProductUpdate
  );
  console.log(idProductUpdate);
  console.log(listProduct);
  var newID = $("#IdUpdate").val();
  var newName = $("#NameUpdate").val();
  var newCategory = $("#CategoryUpDate").val();
  var newSupplierID = $("#SupplierUpDate").val();
  var newquantity = $("#quantityUpDate").val();
  var newUnitPrice = $("#UnitPriceUpdate").val();
  var newDescription = $("#DescriptionUpDate").val();
  var newRating = $("#RatingUpdate").val();
  var newPicture = getPicture($("#PictureUpdate").val());
  var newType = $("#TypeUpdate").val();
  console.log(newPicture);

  if (newPicture == null || newPicture === "") {
    newPicture = listProduct[indexUpdate].picture;
    console.log(newPicture);
  }

  var updateProduct = {
    productname: newName,
    categoryID: newCategory,
    supplierID: newSupplierID,
    quantity: newquantity,
    unitPrice: newUnitPrice,
    description: newDescription,
    rating: newRating,
    picture: newPicture,
    type: newType,
  };

  $.ajax({
    type: "PUT",
    url: `http://localhost:8080/products/${newID}`,
    beforeSend: function (xhr) {
      xhr.setRequestHeader("Authorization", "Basic " + btoa("Admin:123456"));
    },
    data: JSON.stringify(updateProduct),
    contentType: "application/json; charset=UTF-8",
    success: function (response) {
      getlistProduct(response);
    },
  });
  $("#modal-idUpdate").modal("hide");
}

//Lấy danh sách nhà cung cấp
function getlistSupplier() {
  listSupplier = [];
  $.ajax({
    type: "GET",
    url: "http://localhost:8080/suppliers",
    beforeSend: function (xhr) {
      xhr.setRequestHeader("Authorization", "Basic " + btoa("Admin:123456"));
    },
    // data: "data",
    dataType: "json",
    success: function (response) {
      listSupplier = response;
      for (var index = 0; index < listSupplier.length; index++) {
        $("#Supplier_Id").append(
          `<option value="${listSupplier[index].supplierId}">${listSupplier[index].supplierName}</option>`
        );

        //Update
        $("#SupplierUpDate").append(
          `<option value="${listSupplier[index].supplierId}">${listSupplier[index].supplierName}</option>}`
        );
      }
    },
  });
}

// function handleDelete(indexDel) {
//   idProductUpdate = indexDel;

//   var result = confirm("Bạn có chắc chắn muốn xóa Account này");
//   if (result) {
//     // listProduct.splice(indexDel, 1);
//     // Gọi lại hàm showListProduct(): Cập nhật lại bảng kết quả
//     // showListAcount();

//     // Từ indexDel của phần tử cần xóa, cần tìm ra Id tương ứng
//     var v_id_delete = listProduct.findIndex(
//       (product) => product.productid == idProductUpdate
//     );
//     // console.log("indexDel: ", indexDel);
//     // console.log("v_id_delete: ", v_id_delete);
//     // Thực hiện Call API xóa Account
//     $.ajax({
//       type: "DELETE",
//       url: `http://localhost:8080/products/${v_id_delete}`,
//       // data: "data",
//       // dataType: "dataType",
//       success: function (response) {
//         getlistProduct();
//       },
//     });
//   } else {
//     return;
//   }
// }

function handleDelete(idDelete) {
  // Hiện hộp thoại confirm
  var confirmDelete = confirm("Bạn có chắc chắn muốn xóa sản phẩm này không?");
  if (confirmDelete) {
    // Tìm index của Product cần xóa theo id
    var indexPrductDelete = listProduct.findIndex(
      (product) => product.productid == idDelete
    );
    // Nếu không tìm thấy sản phẩm indexPrductDelete=-1

    if (indexPrductDelete !== -1) {
      // Xóa Product trong listProduct đang lưu ở JS
      // listProduct.splice(indexPrductDelete, 1);
      // Lưu lại listProduct vào LocalStorage
      // localStorage.setItem("listProduct", JSON.stringify(listProduct));
      // Call API Xóa Product
      var urlDelete = `http://localhost:8080/products/${idDelete}`;
      $.ajax({
        type: "DELETE",
        url: urlDelete,
        beforeSend: function (xhr) {
          xhr.setRequestHeader(
            "Authorization",
            "Basic " + btoa("Admin:123456")
          );
        },
        // data: "data",
        // dataType: "dataType",
        success: function (response) {
          // Hiển thị lại dữ liệu
          getlistProduct();
        },
      });
      // Hiển thị lại dữ liệu
      // fetchListProductAdmin();
    } else {
      alert("Không thể xóa sản phẩm");
    }
  }
}

function changesize(sizeParam) {
  var sizeSelect = sizeParam.value;
  if (sizeSelect == currentSize) {
    return;
  } else {
    currentSize = sizeSelect;
  }
  getlistProduct();
}

function handleSort(sortFieldParam) {
  if (sortFieldParam === sortField) {
    directionSort = directionSort === "asc" ? "desc" : "asc";
  } else {
    sortField = sortFieldParam;
    directionSort = "asc";
  }

  getlistProduct();
}

function myFunction(event) {
  if (event.keyCode === 13) {
    event.preventDefault();
    handleSearch();
  }
}
function handleSearch() {
  var searchParam = $("#inputSearch").val();
  if (search != searchParam) {
    search = searchParam;
    getlistProduct(search);
  }

  $("$inputSearch").val(" ");
}

function handleHome() {
  window.open("Home.html", "_self");
}

function getlistAccount() {
  $.ajax({
    type: "GET",
    url: `http://localhost:8080/accounts`,
    data: "data",
    beforeSend: function (xhr) {
      xhr.setRequestHeader("Authorization", "Basic " + btoa("Admin:123456"));
    },
    // dataType: "dataType",
    success: function (response) {
      listAccount = response;

      //3.Hien thi danh sach Product
      $("#tbAccount").empty();
      for (var index = 0; index < listAccount.length; index++) {
        $("#tbAccount").append(
          ` <tr>
            <td>${listAccount[index].id}</td>
            <td>${listAccount[index].username}</td>
            <td>${listAccount[index].fullname}</td>
            <td>${listAccount[index].email}</td>
            <td>${listAccount[index].phone}</td>
            <td>${listAccount[index].address}</td>
            <td>${listAccount[index].createDate}</td>
            <td>${listAccount[index].city}</td>
            <td>${listAccount[index].country}</td>
            <td>
              <button type="button" class="btn btn-warning"  onclick="handleEditAccount(${listAccount[index].id})">Edit</button>
            </td>
            <td>
              <button type="button" class="btn btn-danger" onclick="handleDeleteAccount(${listAccount[index].id})">Delete</button>
            </td>
        </tr>
        `
        );
        totalpage = response.totalPages;
        pagination(totalpage);
      }
    },
  });
}
function handleShowAccount() {
  // Load nội dung ContainProduct

  $(".AccountSection").load(
    "./Accountcontain.html",
    "data",
    function (response, status, request) {
      getlistAccount();
      $(".AccountSection").css("display", "block");
      $(".ProductAdminSection").css("display", "none");

      $("#Id").attr("disabled", "disabled");
    }
  );
}

function handleEditAccount(indexEdit) {
  idAccountUpdate = indexEdit;

  var indexAccount = listAccount.findIndex(
    (account) => account.id == idAccountUpdate
  );
  console.log(idProductUpdate);

  var listAccountUpdate = listAccount[indexAccount];
  console.log(listAccount);

  $("#IdAccountUpdate").val(listAccountUpdate.id);
  $("#NameAccountUpdate").val(listAccountUpdate.username);
  $("#fullnameUpdate").val(listAccountUpdate.fullname);
  $("#EmailUpdate").val(listAccountUpdate.email);
  $("#PhoneUpdate").val(listAccountUpdate.phone);
  $("#AddressUpDate").val(listAccountUpdate.address);

  $("#modal-idUpdateAccount").modal("show");
}
//Cập nhật sản phẩm
function handleUpdateAccount(params) {
  var indexUpdate = listAccount.findIndex(
    (account) => account.id == idAccountUpdate
  );

  var newIDAccount = $("#IdAccountUpdate").val();
  var newName = $("#NameAccountUpdate").val();
  var newfullname = $("#fullnameUpdate").val();
  var newemail = $("#EmailUpdate").val();
  var newPhone = $("#PhoneUpdate").val();
  var newAddress = $("#AddressUpDate").val();

  var updateAccount = {
    accountname: newName,
    fullname: newfullname,
    email: newemail,
    phone: newPhone,
    address: newAddress,
  };

  $.ajax({
    type: "PUT",
    url: `http://localhost:8080/accounts/${newIDAccount}`,
    beforeSend: function (xhr) {
      xhr.setRequestHeader("Authorization", "Basic " + btoa("Admin:123456"));
    },
    data: JSON.stringify(updateAccount),
    contentType: "application/json; charset=UTF-8",
    success: function (response) {
      getlistProduct(response);
    },
  });
  $("#modal-idUpdate").modal("hide");
}

function handleDeleteAccount(idDelete) {
  // Hiện hộp thoại confirm
  var confirmDelete = confirm("Bạn có chắc chắn muốn xóa tài khoản này không?");
  if (confirmDelete) {
    // Tìm index của Product cần xóa theo id
    var indexAccountDelete = listAccount.findIndex(
      (account) => account.id == idDelete
    );
    // Nếu không tìm thấy sản phẩm indexPrductDelete=-1

    if (indexAccountDelete !== -1) {
      // Xóa Product trong listProduct đang lưu ở JS
      // listProduct.splice(indexPrductDelete, 1);
      // Lưu lại listProduct vào LocalStorage
      // localStorage.setItem("listProduct", JSON.stringify(listProduct));
      // Call API Xóa Product
      var urlDelete = `http://localhost:8080/accounts/${idDelete}`;
      $.ajax({
        type: "DELETE",
        url: urlDelete,
        beforeSend: function (xhr) {
          xhr.setRequestHeader(
            "Authorization",
            "Basic " + btoa("Admin:123456")
          );
        },
        // data: "data",
        // dataType: "dataType",
        success: function (response) {
          // Hiển thị lại dữ liệu
          getlistAccount();
        },
      });
      // Hiển thị lại dữ liệu
      // fetchListProductAdmin();
    } else {
      alert("Không thể xóa sản phẩm");
    }
  }
}

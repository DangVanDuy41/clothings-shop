  $(document).ready(function() {
     var currentPage = 0;
     function loadProducts(page) {
         $.ajax({
             type: "GET",
             url: "/Trangchu/newProduct?page=" + page,
             success: function(data) {
                 var productList = $("#product-list");
                 productList.empty();
                 $.each(data, function(index, product) {
                     productList.append("<div class='new__product-item  col-3'>" +
                         "<a class='new__product-item-link' href=''>" +
                         "<div class='new__product-item-link-image' style='background-image: url(/image/imageProduct/" + product.image + ");'></div>" +
                         "<span class='home-product-item__name'>" + product.name + "</span>" +
                         "<span class='home-product-item__price'>" + product.formattedPrice + "</span>" +
                         "</a></div>");
                 });
                 currentPage = page;
             },
             error: function(err) {
                 console.error("Đã xảy ra lỗi: " + err.responseText);
             }
         });
     }
     function getPageTotal(callback) {
         $.ajax({
             type: "GET",
             url: "/Trangchu/pageNewProduct",
             success: function(data) {

                     callback(data);

             },
             error: function(err) {
                 console.error("Đã xảy ra lỗi: " + err.responseText);
             }
         });
     }

     $(".previous-link-newproduct ").click(function() {
         if (currentPage > 0) {
             loadProducts(currentPage - 1);
         }
     });

    $(".next-link-newproduct ").click(function() {
        getPageTotal(function(totalPages) {
            if (currentPage < totalPages -1) {
                loadProducts(currentPage + 1) ;
            }
        });
    });
     loadProducts(currentPage);
 });
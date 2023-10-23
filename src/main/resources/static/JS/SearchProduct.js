

    let menu = document.querySelector("#menu__search");
    menu.addEventListener('input', function () {

        let searchValue = menu.value.toLowerCase();

              listProducts(searchValue)
    });
    function  listProducts(searchValue){
                                     $.ajax({
                                        type: "GET",
                                        url: "/Trangchu/category/listProduct",
                                        success: function(data) {
                                            render(data,searchValue);

                                        },
                                        error: function(err) {
                                            console.error("Đã xảy ra lỗi: " + err.responseText);
                                        }
                                    });
                             }

                                            function render(data,searchValue){
                                                      var productList = $("#list-product");
                                                         productList.empty();

                                                         // Sử dụng Array.filter() để lọc danh sách sản phẩm
                                                         var filteredProducts = data.filter(function(product) {
                                                             return product.name.toLowerCase().includes(searchValue);
                                                         });

                                                         // Hiển thị kết quả lọc
                                                         filteredProducts.forEach(function(product) {
                                                             productList.append("<div  class='item-product  new__product-item  col-3'>" +
                                                                 "<a class='new__product-item-link' href=''>" +
                                                                 "<div class='new__product-item-link-image' style='background-image: url(/image/imageProduct/" + product.image + ");'></div>" +
                                                                 "<span class='home-product-item__name home-product-item__name-product'>" + product.name + "</span>" +
                                                                 "<span class='home-product-item__price'>" + product.formattedPrice + "</span>" +
                                                                 "</a></div>");
                                                         });
                                                }

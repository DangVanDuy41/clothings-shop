  $(document).ready(function() {
         var category=0;
         var pageCurren =0;
         listProductsAllpage(pageCurren);
        $(".catelogy-item__link").click(function(e){
            e.preventDefault();
                $(".catelogy-item__link").css("background-color:black")
             category =$(this).data('category-id');
                if(category === 0){
                    listProductsAllpage(0);
                     $(".list-product-page").show();
                }else{
                    loadProducts(category);
                    $(".list-product-page").hide();
                }

        })
              $("#thapdencao").click(function(e){
                              e.preventDefault();
                                if(category==0){
                                    listProductALLthapdencao()
                                }else{
                                     listProductsThapdencao(category);
                                }




              })
              $("#caodenthap").click(function(e){
                        e.preventDefault();
                        if(category==0){
                         listProductALLscaodenthap()
                        }else{
                        listProductscaodenthap(category);
                        }


                })
        function  loadProducts(categoryId){
                 $.ajax({
                    type: "GET",
                    url: "/Trangchu/category/" + categoryId,
                    success: function(data) {
                          render(data);
                    },
                    error: function(err) {
                        console.error("Đã xảy ra lỗi: " + err.responseText);
                    }
                });
    }
    function  listProducts(){
                     $.ajax({
                        type: "GET",
                        url: "/Trangchu/category/listProduct",
                        success: function(data) {
                            render(data);
                        },
                        error: function(err) {
                            console.error("Đã xảy ra lỗi: " + err.responseText);
                        }
                    });
        }
          function listProductsAllpage(pageCurren){
                $.ajax({
                           type: "GET",
                           url: "/Trangchu/AllProduct/" +pageCurren,
                           success: function(data) {
                                 render(data);
                           },
                           error: function(err) {
                               console.error("Đã xảy ra lỗi: " + err.responseText);
                           }
                       });

          }

        function  listProductsThapdencao(id){
                             $.ajax({
                                type: "GET",
                                url: "/Trangchu/category/thapdencao/" + id,
                                success: function(data) {
                                     render(data);
                                },
                                error: function(err) {
                                    console.error("Đã xảy ra lỗi: " + err.responseText);
                                }
                            });
                }
                 function  listProductscaodenthap(id){
                                             $.ajax({
                                                type: "GET",
                                                url: "/Trangchu/category/caodenthap/" + id,
                                                success: function(data) {
                                                     render(data);
                                                },
                                                error: function(err) {
                                                    console.error("Đã xảy ra lỗi: " + err.responseText);
                                                }
                                            });
                                }
                                                    function  listProductALLscaodenthap(){
                                                         $.ajax({
                                                            type: "GET",
                                                            url: "/Trangchu/category/caodenthap" ,
                                                            success: function(data) {
                                                                  render(data);
                                                            },
                                                            error: function(err) {
                                                                console.error("Đã xảy ra lỗi: " + err.responseText);
                                                            }
                                                        });
                                                 }

                                                    function  listProductALLthapdencao(){
                                                         $.ajax({
                                                            type: "GET",
                                                            url: "/Trangchu/category/thapdencao" ,
                                                            success: function(data) {
                                                                  render(data);
                                                            },
                                                            error: function(err) {
                                                                console.error("Đã xảy ra lỗi: " + err.responseText);
                                                            }
                                                        });
                                                 }
                                                    function render(data){
                                                     var productList = $("#list-product");
                                                     productList.empty();
                                                     $.each(data, function(index, product) {
                                                         productList.append("<div  class='item-product  new__product-item  col-3'>" +
                                                             "<a class='new__product-item-link' href=''>" +
                                                             "<div class='new__product-item-link-image' style='background-image: url(/image/imageProduct/" + product.image + ");'></div>" +
                                                             "<span class='home-product-item__name home-product-item__name-product'>" + product.name + "</span>" +
                                                             "<span class='home-product-item__price'>" + product.formattedPrice + "</span>" +
                                                             "</a></div>");
                                                     });
                                                }
    $(".next-page ").click(function(){
            pageCurren++;
         listProductsAllpage(pageCurren);
    });
    $(".previous-page").click(function(){
        if(pageCurren >=0){
            pageCurren--;
            listProductsAllpage(pageCurren);
        }

    })
 });
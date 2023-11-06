  $(document).ready(function() {
       var id = $("#productId").val();
     function loadProducts(id) {
         $.ajax({
             type: "GET",
             url: "/ProductDetails/product/" + id,
             success: function(data) {
                 var productList = $("#product-list");
                 productList.empty();
                 $.each(data, function(index, product) {
                     productList.append("<div class='new__product-item  col-3'>" +
                          "<a class='new__product-item-link' href='/ProductDetails/" + product.id + "'>" +
                         "<div class='new__product-item-link-image' style='background-image: url(/image/imageProduct/" + product.image + ");'></div>" +
                         "<span class='home-product-item__name'>" + product.name + "</span>" +
                         "<span class='home-product-item__price'>" + product.formattedPrice + "</span>" +
                         "</a></div>");
                 });

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
                loadProducts(currentPage + 1) ;

    });
    loadProducts(id)


      $(".reduce").click(function(){
         var quantity = parseInt($(".stock-input").val());
            if(quantity > 1){
                quantity--
              $(".stock-input").val(quantity);

            }

      })

      $(".increase").click(function(){
         var quantity = parseInt($(".stock-input").val());
            quantity++
         $(".stock-input").val(quantity);
      })


//      $(".buy").click(function(){
//            let quantity  = parseInt($(".stock-input").val());
//        $.ajax({
//                    type: "GET",
//                    url: "/CartItem/myCart?id=" + id +"&quantity="+ quantity,
//                    success: function(data) {
//                            console.log(data);
//
//
//                    },
//                    error: function(err) {
//                        console.error("Đã xảy ra lỗi: " + err.responseText);
//                    }
//                });
//
//      })
           $(".buy").click(function(){

                $.ajax({
                            type: "GET",
                            url: "/CartItem/productIncart" ,
                            success: function(data) {
                                    console.log(data);


                            },
                            error: function(err) {
                                console.error("Đã xảy ra lỗi: " + err.responseText);
                            }
                        });

              })

 });

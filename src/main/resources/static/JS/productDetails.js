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


   $(".buy").click(function() {
       let quantity = parseInt($(".stock-input").val());

       $.ajax({
           type: "GET",
           url: "/CartItem/myCart?id=" + id + "&quantity=" + quantity,
           success: function(data) {
                    mycart();
           },
           error: function(err) {
               console.error("Đã xảy ra lỗi: " + err.responseText);
           }
       });
   });


    function mycart(){
             $.ajax({
                       type: "GET",
                       url: "/CartItem/productIncart",
                       success: function(data) {
                        renderCart(data);
                        totalPrice();
                       },
                       error: function(err) {
                           console.error("Đã xảy ra lỗi: " + err.responseText);
                       }
                   });
        }


       function renderCart(data) {
           var cartHTML = '';
           data.forEach((item) => {
               cartHTML += `
                   <div class="myCart">
                       <div class="cart__image">
                           <div style="background-image: url('/image/imageProduct/${item.image}');" class="product_image"></div>
                       </div>
                       <div class="cart__product">
                           <div class="cart__product-title">
                               <span>${item.nameProduct}</span>
                           </div>
                           <div class="total cart__product-price">
                               <span class="quantity">${item.quantity}</span>
                               <span>x</span>
                               <span class="price">${item.price}</span>
                           </div>
                       </div>
                       <div class="cart__delete">
                           <i class="fa-solid fa-delete-left"></i>
                       </div>
                   </div>
               `;
           });

           $("#cart_item").html(cartHTML);
       }
         function totalPrice(){
                   $.ajax({
                             type: "GET",
                             url: "/CartItem/totalPrice",
                             success: function(data) {

                                var cartHTML = '';
                                cartHTML += `<span>Tổng phụ: </span>
                                           <span class="price"> ${data}</span>`;

                                            $(".total-price").html(cartHTML);


                             },
                             error: function(err) {
                                 console.error("Đã xảy ra lỗi: " + err.responseText);
                             }
                         });
              }
              function totalPrice(){

                          $.ajax({
                                    type: "GET",
                                    url: "/CartItem/totalPrice",
                                    success: function(data) {
                                       var cartHTML = '';

                                       cartHTML += `<span>Tổng phụ: </span>
                                                  <span class="price">${data}</span>`;

                                                   $(".total-price").html(cartHTML);


                                    },
                                    error: function(err) {
                                        console.error("Đã xảy ra lỗi: " + err.responseText);
                                    }
                                });
                     }
       mycart();

})


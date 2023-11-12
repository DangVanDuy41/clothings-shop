    function mycart(){
         $.ajax({
                   type: "GET",
                   url: "/CartItem/productIncart",
                   success: function(data) {
                    renderCart(data)
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
                   <div class="cart__delete" onclick="deleteCartItem(${item.idCart})">
                       <i class="fa-solid fa-delete-left"></i>
                   </div>
               </div>
           `;
       });

       $("#cart_item").html(cartHTML);
   }
    function totalPrice(){
        var cartHTML = '';
         $(".total-price").empty();
            $.ajax({
                      type: "GET",
                      url: "/CartItem/totalPrice",
                      success: function(data) {


                         cartHTML += `<span>Tổng phụ: </span>
                                    <span class="price">${data}</span>`;

                                     $(".total-price").html(cartHTML);


                      },
                      error: function(err) {
                          console.error("Đã xảy ra lỗi: " + err.responseText);
                      }
                  });
       }
    function deleteCartItem(idcart) {

        $.ajax({
            type: "GET",
            url: "/CartItem/delete/" + idcart,
            success: function(data) {
               mycart();
            },
            error: function(err) {
                console.error("Đã xảy ra lỗi: " + err.responseText);
            }
        });
    }
   mycart();

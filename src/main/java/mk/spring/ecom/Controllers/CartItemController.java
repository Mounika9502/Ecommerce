package mk.spring.ecom.Controllers;
import java.util.List;



import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mk.spring.ecom.CartItem;
import mk.spring.ecom.Services.CartItemService;

 

@RestController
@RequestMapping("/cart")
public class CartItemController {

    private final CartItemService cartItemService;


 

    public CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }



 

    @GetMapping
    public List<CartItem> getCart() {
        return cartItemService.getAllCartItems();
    }
 

    @PostMapping("/items")
    public String addItemToCart(@RequestBody CartItem cartItem) {
    	
       return cartItemService.addItemToCart(cartItem);
        
    }

 

    @PutMapping("/items/{itemId}")
    public void updateCartItem(@PathVariable Long itemId, @RequestBody CartItem updatedCartItem) {
        cartItemService.updateCartItem(itemId, updatedCartItem);
    }

 

    @DeleteMapping("/items/{itemId}")
    public void removeCartItem(@PathVariable Long itemId) {
        cartItemService.removeCartItem(itemId);
    }

 

    @DeleteMapping
    public void emptyCart() {
        cartItemService.emptyCart();
    }
}

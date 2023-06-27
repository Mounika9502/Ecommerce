package mk.spring.ecom.Services;



import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mk.spring.ecom.CartItem;
import mk.spring.ecom.Product;
import mk.spring.ecom.Repositories.CartItemRepository;
import mk.spring.ecom.Repositories.ProductRepo;



@Service
public class CartItemService {
	
    private final CartItemRepository cartItemRepository;

    public CartItemService(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }
    @Autowired
    ProductRepo proRepository;
    
    public List<CartItem> getAllCartItems() {
        return cartItemRepository.findAll();
    }

    public String addItemToCart(CartItem cartItem) {
    	Long idd= cartItem.getProductId();
    	Optional<Product> productOptional=proRepository.findById(idd);
    	if(productOptional.isPresent()) {
    		cartItemRepository.save(cartItem);	
    		return "item added into the cart successfully with product id "+idd;
    	}
    	return "Sorry! No product exists with the product id "+idd;
    	
    }

    public void updateCartItem(Long itemId, CartItem updatedCartItem) {
        CartItem cartItem = cartItemRepository.findById(itemId)
                .orElseThrow(() -> new NoSuchElementException("CartItem not found"));

        cartItem.setQuantity(updatedCartItem.getQuantity());
        cartItemRepository.save(cartItem);
    }

    public void removeCartItem(Long itemId) {
        cartItemRepository.deleteById(itemId);
    }

    public void emptyCart() {
        cartItemRepository.deleteAll();
    }
}

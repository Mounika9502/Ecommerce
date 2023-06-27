package mk.spring.ecom.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mk.spring.ecom.Product;
import mk.spring.ecom.Repositories.CatRepository;
import mk.spring.ecom.Repositories.ProductRepo;

@RestController
@RequestMapping("/api")
public class ProductController {
    
    @Autowired
    ProductRepo productRepo;
    @Autowired
    CatRepository  catRepository;
    
    @PostMapping("/addProduct")
    public String addProduct(@RequestBody Product product) {
        
        productRepo.save(product);
        return "new product added";
        
    }
    
    @PutMapping("/updateProduct/{productId}")
    public String updateProduct(@RequestBody Product product,@PathVariable("productId") Long productId)
    {
        
        Product product1=productRepo.findById(productId).orElse(null);
        if(product1 != null) {
            productRepo.deleteById(productId);
        }
        productRepo.save(product);
        return "updated";
    }
    
    @GetMapping("/getAllProducts")
    public List<Product> getAllProducts()
    {
    	List<Product> pList=productRepo.findAll();
    	System.out.println(pList);
        return pList;
    }
    @GetMapping("getAllProducts/{categoryId}")
    public List<Product> getProductsByCategory(@PathVariable Long categoryId) {
    	return productRepo.findByCatid(categoryId);
    }
    
    @GetMapping("/getProduct/{productId}")
    public Product getProduct(@PathVariable("productId") Long productId)
    {
        return productRepo.findById(productId).orElse(null);
    }
    
    @DeleteMapping("/deleteProduct/{productId}")
    public String deleteProduct(@PathVariable("productId") Long productId)
    {
        Product product1=productRepo.findById(productId).orElse(null);
        if(product1 != null) {
            productRepo.deleteById(productId);
            return "Deleted";
        }
        return "Not Found";
    }
    

}
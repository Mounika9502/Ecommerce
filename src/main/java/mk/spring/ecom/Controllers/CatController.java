package mk.spring.ecom.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mk.spring.ecom.Categories;
import mk.spring.ecom.Repositories.CatRepository;
//import mk.spring.ecom.Repositories.ProductRepo;		


@RestController
@RequestMapping("/api")
public class CatController {
	@Autowired
	CatRepository catRepository;
//	@Autowired
//	ProductRepo proRepository;


	@PostMapping("/Categories")
	public String createNewCategory(@RequestBody Categories cat) {
	    catRepository.save(cat);
	    return "New Category Created";
	}

	@GetMapping("/Categories")
	public ResponseEntity<List<Categories>> getAllCategories(){
//		List<Categories> catlist=new ArrayList<>();
//		catRepository.findAll().forEach(catlist::add);
		return new ResponseEntity<List<Categories>>(catRepository.findAll(),HttpStatus.OK);
	}


	@GetMapping("/Categories/{id}")
	public ResponseEntity<Categories> getuserbyId(@PathVariable Long id){
		Optional<Categories> user=catRepository.findById(id);
		if(user.isPresent()) {
			return new ResponseEntity<Categories>(user.get(),HttpStatus.FOUND);
		}
		else {
			return new ResponseEntity<Categories>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/Categories/{id}")
	public String updateuserdetailsbyId(@PathVariable Long id,@RequestBody Categories cat) {
		Optional<Categories> exCat=catRepository.findById(id);
		if(exCat.isPresent()) {
			Categories updCat=exCat.get();
			updCat.setCatname(cat.getCatname());
			catRepository.save(updCat);
			return "Details of Category with id " + id + "are updated";
			
		}
		else {
			return "there is no Category with  id " + id;
		}
		
	}
//	@PostMapping("/Categories/{categoryId}/products")
//	public String addProductToCategory(@PathVariable Long categoryId, @RequestBody Product product) {
//	    Optional<Categories> categoryOptional = catRepository.findById(categoryId);
//	    if (categoryOptional.isPresent()) {
//	        Categories category = categoryOptional.get();
//	        category.addProduct(product);
//	        catRepository.save(category);
//	        return "Product added to category";
//	    } else {
//	        return "Category not found";
//	    }
//	}
//	@GetMapping("/Categories/{categoryId}/products")
//	public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable Long categoryId) {
//	    Optional<Categories> categoryOptional = catRepository.findById(categoryId);
//	    if (categoryOptional.isPresent()) {
//	        Categories category = categoryOptional.get();
//	        List<Product> products = category.getProducts();
//	        return new ResponseEntity<>(products, HttpStatus.OK);
//	    } else {
//	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//	    }
//	}


	@DeleteMapping("/Categories/{id}")
	public String deletebyId(@PathVariable Long id) {
		catRepository.deleteById(id);
		return "Deleted the user with user id" + id;
	}
	@DeleteMapping("/Categories")
	public String deleteallUsers() {
		catRepository.deleteAll();
		return "All Categories are deleted";
	}
	

}

package mk.spring.ecom.Controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
//import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mk.spring.ecom.Users;
import mk.spring.ecom.Repositories.UserRepository;



@RestController
@RequestMapping("/api")
public class UsersController {
	@Autowired
	UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
//	private static final Logger logger = Logger.getLogger(UsersController.class.getName());

	@PostMapping("/Users")
	public String createnewuser(@RequestBody Users user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
		return "New User Created";
	}
	@PostMapping("/Users/Login")
	public ResponseEntity<String> loginUser(@RequestBody Users user){
		Optional<Users> userOp=userRepository.findByEmail(user.getEmail());
		
		if(userOp.isPresent()) {
			
			Users exUser=userOp.get();
			boolean allowed=passwordEncoder.matches(user.getPassword(), exUser.getPassword());
			System.out.println(allowed);
			if(allowed) {
//				logger.info("passed comparision");
				 if (exUser.isLoggedIn()) {
		                return ResponseEntity.badRequest().body("User is already logged in");
		         }
				 exUser.setLoggedIn(true);
		         userRepository.save(exUser);
		         return ResponseEntity.ok("User logged in successfully.  " );	
			}
		}
		return ResponseEntity.badRequest().body("Invalid email or password");
	}
	@PostMapping("/Users/logout")
	public ResponseEntity<String> logoutUser(@RequestBody Users user ){
	    
//	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//	    String userEmail = authentication.getName();

	    Optional<Users> userOptional = userRepository.findByEmail(user.getEmail());
	    if (userOptional.isPresent()) {
	        Users exUser = userOptional.get();

	        if (!exUser.isLoggedIn()) {
	            return ResponseEntity.badRequest().body("User is already logged out");
	        }

	   
	        exUser.setLoggedIn(false);
	        userRepository.save(exUser);

	        return ResponseEntity.ok("Logged out successfully");
	    }

	    return ResponseEntity.badRequest().body("Failed to log out");
	}
	@GetMapping("/Users")
	public ResponseEntity<List<Users>> getAllUsers(){
		List<Users> userlist=new ArrayList<>();
		userRepository.findAll().forEach(userlist::add);
		return new ResponseEntity<List<Users>>(userlist,HttpStatus.OK);
	}
	@GetMapping("/Users/Loggedin")
	public ResponseEntity<List<Users>> getUsersByLoggedInStatus() {
	    List<Users> loggedInUsers = new ArrayList<>();

	    for (Users user : userRepository.findAll()) {
	        if (user.isLoggedIn()) {
	            loggedInUsers.add(user);
	        }
	    }

	    return new ResponseEntity<>(loggedInUsers, HttpStatus.OK);
	}
	
	@GetMapping("/Users/{userid}")
	public ResponseEntity<Users> getuserbyId(@PathVariable Long userid){
		Optional<Users> user=userRepository.findById(userid);
		if(user.isPresent()) {
			return new ResponseEntity<Users>(user.get(),HttpStatus.FOUND);
		}
		else {
			return new ResponseEntity<Users>(HttpStatus.NOT_FOUND);
		}
	}
	@PutMapping("/Users/{userid}")
	public String updateuserdetailsbyId(@PathVariable Long userid,@RequestBody Users user) {
		Optional<Users> exsUser=userRepository.findById(userid);
		if(exsUser.isPresent()) {
			Users updUser=exsUser.get();
			updUser.setUsername(user.getUsername());
			updUser.setPassword(user.getPassword());
			updUser.setEmail(user.getEmail());

			userRepository.save(updUser);
			return "Details of user with userid " + userid + "are updated";
			
		}
		else {
			return "there is no user exsisting with user id " + userid;
		}
		
	}
	@DeleteMapping("/Users/{userid}")
	public String deletebyId(@PathVariable Long userid) {
		userRepository.deleteById(userid);
		return "Deleted the user with user id" + userid;
	}
	@DeleteMapping("/Users")
	public String deleteallUsers() {
		userRepository.deleteAll();
		return "All users are deleted";
	}
	

}

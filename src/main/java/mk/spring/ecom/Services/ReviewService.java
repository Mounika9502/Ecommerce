package mk.spring.ecom.Services;



import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import mk.spring.ecom.Product;
import mk.spring.ecom.Review;
import mk.spring.ecom.Repositories.ProductRepo;
import mk.spring.ecom.Repositories.ReviewRepository;



@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }
    @Autowired
    ProductRepo proRepository;
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

//    public List<Review> getReviewById(Long id) {
////        return reviewRepository.findById(id)
////                .orElseThrow(() -> new NoSuchElementException("Review not found"));
//    	return List<Review.(rlist);
//    }
    public ResponseEntity<List<Review>> getReviewById(Long idd) {
	    List<Review> rlist = new ArrayList<>();

	    for (Review review : reviewRepository.findAll()) {
	        if (review.getProductId().equals(idd)) {
	            rlist.add(review);
	        }
	    }

	    return new ResponseEntity<>(rlist, HttpStatus.OK);
	}
    public String createReview(Review review) {
    	Long idd= review.getProductId();
    	Optional<Product> productOptional=proRepository.findById(idd);
    	if(productOptional.isPresent()) {
    		reviewRepository.save(review);
    		return "Successfully added the revuew for product id "+idd;
    	}
    	return "Sorry! No product exists with the product id "+idd;
       
    }

    public Review updateReview(Long id, Review updatedReview) {
        Review review = reviewRepository.getById(id);

        review.setReviewerName(updatedReview.getReviewerName());
        review.setComment(updatedReview.getComment());
        review.setRating(updatedReview.getRating());

        return reviewRepository.save(review);
    }

    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }
}

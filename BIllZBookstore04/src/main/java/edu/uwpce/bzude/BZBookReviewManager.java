package edu.uwpce.bzude;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface BZBookReviewManager {
	
	List<BZBookReview> getBookReviews(String isbn);
	
	void setBookReviews(List<BZBookReview> bookReviews );
	
	Map<String, List<BZBookReview>> getAllBooksReviews();
	
	void setAllBooksReviews(Map<String, List<BZBookReview>> booksreviews);
	
	void addBookReview(BZBookReview review);
	
	BZBookReview getBookReview(String isbn, Date timestamp);
		

}

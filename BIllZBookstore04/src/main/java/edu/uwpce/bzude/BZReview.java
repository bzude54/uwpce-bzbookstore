package edu.uwpce.bzude;

import java.io.Serializable;
import java.util.Date;

public interface BZReview extends Serializable{
	
	String getReviewText();
		
	void setReviewText(String reviewText);

	void setReviewIsbn(String isbn);
	
	String getReviewIsbn();

	Date getTimeStamp();

	void setTimeStamp(Date timeStamp);

	
}

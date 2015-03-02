package edu.uwpce.bzbookstore;

import java.io.Serializable;
import java.util.Date;

public interface BZReview extends Serializable{
	
	String getReviewText();
		
	void setReviewText(String reviewText);

	void setReviewIsbn(String isbn);
	
	String getReviewIsbn();

	long getTimeStamp();

	void setTimeStamp(long timeStamp);
	
	String getReviewUuid();
	
	void setReviewUuid(String uuid);

	
}

package edu.uwpce.bzude;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class BZBookReview implements BZReview {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8545327746357214420L;
	
	private String uuid;
	private String isbn;
	private String reviewText;
	private Date timeStamp;
	
	public BZBookReview() {	}
	
	public BZBookReview(String isbn, String reviewtext, Date timestamp) {
		this.isbn = isbn;
		this.reviewText = reviewtext;
		this.timeStamp = timestamp;
		this.uuid = UUID.randomUUID().toString();
	}

	@Override
	public String getReviewText() {
		return this.reviewText;
	}

	@Override
	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}

	@Override
	public void setReviewIsbn(String isbn) {
		this.isbn = isbn;
	}

	@Override
	public String getReviewIsbn() {
		return this.isbn;
	}

	@Override
	public Date getTimeStamp() {
		return timeStamp;
	}

	@Override
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	public String getReviewUuid() {
		return this.uuid;
	}

}

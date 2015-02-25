package edu.uwpce.bzude;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.core.io.ClassPathResource;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BZSimpleBookReviewManager implements BZBookReviewManager {

	private Map<String, List<BZBookReview>> allbooksreviews;

	public BZSimpleBookReviewManager() {
		this.allbooksreviews = new ConcurrentHashMap<String, List<BZBookReview>>();
	}

	@PostConstruct
	public void init() {
		ClassPathResource x = new ClassPathResource("defaultreviews.json");
		ObjectMapper mapper = new ObjectMapper();
		try {
			this.allbooksreviews = mapper.readValue(x.getInputStream(),
					new TypeReference<HashMap<String, List<BZBookReview>>>() {
					});
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<BZBookReview> getBookReviews(String isbn) {
		List<BZBookReview> bookreviews = null;
		if (allbooksreviews.containsKey(isbn)) {
			bookreviews = allbooksreviews.get(isbn);
		}
		return bookreviews;
	}

	@Override
	public void setBookReviews(List<BZBookReview> bookReviews) {
		String key = bookReviews.get(0).getReviewIsbn();
		this.allbooksreviews.put(key, bookReviews);

	}

	@Override
	public void setAllBooksReviews(Map<String, List<BZBookReview>> booksreviews) {
		this.allbooksreviews = booksreviews;
	}

	@Override
	public Map<String, List<BZBookReview>> getAllBooksReviews() {
		return this.allbooksreviews;
	}

	@Override
	public void addBookReview(BZBookReview review) {
		List<BZBookReview> reviewlist = allbooksreviews
				.get(review.getReviewIsbn());
		if (reviewlist == null) {
			List<BZBookReview> reviews = new ArrayList<BZBookReview>();
			reviews.add(review);
			allbooksreviews.put(review.getReviewIsbn(), reviews);

		} else {
			ReviewCompare reviewCompare  = new ReviewCompare();
//			Collections.sort(reviewlist, reviewCompare);
//			int index = Collections.binarySearch(reviewlist, review, reviewCompare);
//			reviewlist.add(index, review);
			reviewlist.add(review);
			Collections.sort(reviewlist, reviewCompare);

		}

	}

	@Override
	public BZBookReview getBookReview(String isbn, Date timestamp) {
		// TODO Auto-generated method stub
		return null;
	}

	class ReviewCompare implements Comparator<BZBookReview> {
		public int compare(BZBookReview reviewone, BZBookReview reviewtwo) {
			int result = 0;
			Date thistimestamp = reviewone.getTimeStamp();
			Date thattimestamp = reviewtwo.getTimeStamp();
			result = thistimestamp.compareTo(thattimestamp);
			return result;
		}
	}

}

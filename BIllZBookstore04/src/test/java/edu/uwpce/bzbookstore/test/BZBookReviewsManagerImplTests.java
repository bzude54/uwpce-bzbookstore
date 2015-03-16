package edu.uwpce.bzbookstore.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.uwpce.bzbookstore.BZBook;
import edu.uwpce.bzbookstore.BZBookReview;
import edu.uwpce.bzbookstore.BZBookReviewsManager;
import edu.uwpce.bzbookstore.BZBookReviewsManagerImpl;
import edu.uwpce.bzbookstore.BZBooksManagerImpl;
import edu.uwpce.bzbookstore.BZBooksManager;

public class BZBookReviewsManagerImplTests {
	
	private static final Logger logger = LoggerFactory.getLogger(BZBookReviewsManagerImplTests.class);


	private static String BOOK_ISBN = "978-0441569595";
	private static String REVIEW_TEXT = "I am loving it!";
	private static long TIME_STAMP = 1424759513740L;
	private static String UUID = "1a8dd7e7-b804-453a-b117-22609f290e05";
	
	private static int REVIEWS_INDEX = 0;

	
	private BZBookReviewsManager bookReviewsManager;
	private Map<String, List<BZBookReview>> reviewsAll;
	private List<BZBookReview> reviewsList;
	
	@Before
	public void setUp() throws Exception {
		bookReviewsManager = new BZBookReviewsManagerImpl();
		reviewsAll = new ConcurrentHashMap<String, List<BZBookReview>>();
		reviewsList = new ArrayList<BZBookReview>();
		
		BZBookReview review = new BZBookReview();
		review.setReviewIsbn(BOOK_ISBN);
		review.setReviewText(REVIEW_TEXT);
		review.setReviewUuid(UUID);
		review.setTimeStamp(TIME_STAMP);
		reviewsList.add(review);
		reviewsAll.put(BOOK_ISBN, reviewsList);
		logger.info("Added review to reviews map with id: " + review.getReviewIsbn());
		bookReviewsManager.setAllBooksReviews(reviewsAll);
	}

	@Test
	public void testGetBooksWithNoBooks() {
		BZBooksManager localbookReviewsManager = new BZBooksManagerImpl();
		Map<String, BZBook> localBooks = localbookReviewsManager.getBooks();
		assertNotNull(localBooks);
		assertEquals(0, localBooks.size());
	}

	@Test
	public void testGetBooks() {
		Map<String, List<BZBookReview>> localBooksReviews = bookReviewsManager.getAllBooksReviews();
		assertNotNull(localBooksReviews);
		assertEquals(reviewsAll.size(), localBooksReviews.size());
		
		// We'll duplicate some of the book and author tests here just
		// to make sure we got the SAME list back.
		List<BZBookReview> localBookReviews = localBooksReviews.get(BOOK_ISBN);
		assertEquals(BOOK_ISBN, localBookReviews.get(REVIEWS_INDEX).getReviewIsbn());
		assertEquals(UUID, localBookReviews.get(REVIEWS_INDEX).getReviewUuid());
		assertEquals(REVIEW_TEXT, localBookReviews.get(REVIEWS_INDEX).getReviewText());
		assertEquals(TIME_STAMP, localBookReviews.get(REVIEWS_INDEX).getTimeStamp());
	}
}

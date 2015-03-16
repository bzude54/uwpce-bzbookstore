package edu.uwpce.bzbookstore.test;

import static org.junit.Assert.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.uwpce.bzbookstore.BZBook;
import edu.uwpce.bzbookstore.BZBooksManagerImpl;
import edu.uwpce.bzbookstore.BZBooksManager;

public class BZBooksManagerImplTests {
	
	private static final Logger logger = LoggerFactory.getLogger(BZBooksManagerImplTests.class);


	private static String AUTHOR_NAME = "William Gibson";
	private static String BOOK_ISBN = "978-0441569595";
	private static String BOOK_TITLE = "Neuromancer";
	private static String BOOK_DESCRIPTION = "Case had been the sharpest data-thief in the business, until "
										  + "vengeful former employees crippled his nervous system. But "
										  + "now a new and very mysterious employer recruits him for a "
										  + "last-chance run.";
	private BZBooksManager bookManager;
	private Map<String, BZBook> books;
	
	@Before
	public void setUp() throws Exception {
		bookManager = new BZBooksManagerImpl();
		books = new ConcurrentHashMap<String, BZBook>();
	
		BZBook book = new BZBook();
		book.setISBN(BOOK_ISBN);
		book.setTitle(BOOK_TITLE);
		book.setAuthor(AUTHOR_NAME);
		book.setDescription(BOOK_DESCRIPTION);
		books.put(BOOK_ISBN, book);
		logger.info("Added book to bookMap with id: " + book.getISBN());
		bookManager.setBooks(books);
	}

	@Test
	public void testGetBooksWithNoBooks() {
		BZBooksManager localBookManager = new BZBooksManagerImpl();
		Map<String, BZBook> localBooks = localBookManager.getBooks();
		assertNotNull(localBooks);
		assertEquals(0, localBooks.size());
	}

	@Test
	public void testGetBooks() {
		Map<String, BZBook> localBooks = bookManager.getBooks();
		assertNotNull(localBooks);
		assertEquals(books.size(), localBooks.size());
		
		// We'll duplicate some of the book and author tests here just
		// to make sure we got the SAME list back.
		BZBook localBook = localBooks.get(BOOK_ISBN);
		assertEquals(BOOK_ISBN, localBook.getISBN());
		assertEquals(BOOK_TITLE, localBook.getTitle());
		assertEquals(BOOK_DESCRIPTION, localBook.getDescription());
		assertEquals(AUTHOR_NAME, localBook.getAuthor());
	}
}

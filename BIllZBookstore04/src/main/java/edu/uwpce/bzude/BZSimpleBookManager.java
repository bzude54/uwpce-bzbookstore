package edu.uwpce.bzude;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BZSimpleBookManager implements BZBookManager {

	private static final Logger logger = LoggerFactory.getLogger(BZSimpleBookManager.class);

	private Map<String, BZBook> books;
	
	public BZSimpleBookManager() {
		this.books = new ConcurrentHashMap<String, BZBook>();
	}
	
	public void setBooks(Map<String, BZBook> books) {
		this.books = books;
	}

	@Override
	public Map<String, BZBook> getBooks() {
//		logger.info("Getting books from BZSimpleBookManager");
		return books;
	}

	@Override
	public BZBook getSingleBook(String bookId) {
		return books.get(bookId);
	}

	@Override
	public void setSingleBook(BZBook book) {
		books.put(book.getISBN(), book);
	}
}

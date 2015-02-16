package edu.uwpce.bzude;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BZSimpleBookManager implements BZBookManager {

	private Map<String, BZBook> books;
	
	public BZSimpleBookManager() {
		this.books = new ConcurrentHashMap<String, BZBook>();
	}
	
	public void setBooks(Map<String, BZBook> books) {
		this.books = books;
	}

	@Override
	public Map<String, BZBook> getBooks() {
//		System.out.println("Getting books from BZSimpleBookManager");
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

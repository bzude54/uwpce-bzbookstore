package edu.uwpce.bzude;

import java.util.Map;

public class BZSimpleBookManager implements BZBookManager {

	private Map<String, BZBook> booksMap;
	
	public void setBooks(Map<String, BZBook> books) {
		this.booksMap = books;
	}

	@Override
	public Map<String, BZBook> getBooks() {
//		System.out.println("Getting books from BZSimpleBookManager");
		return booksMap;
	}

	@Override
	public BZBook getSingleBook(String bookId) {
		return booksMap.get(bookId);
	}

	@Override
	public void setSingleBook(BZBook book) {
		// TODO Auto-generated method stub
		
	}
}

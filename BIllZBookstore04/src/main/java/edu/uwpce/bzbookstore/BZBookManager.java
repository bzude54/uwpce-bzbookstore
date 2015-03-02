package edu.uwpce.bzbookstore;

import java.util.Map;

public interface BZBookManager {

	public Map<String, BZBook> getBooks();
	
	public void setBooks(Map<String, BZBook> books);
	
	public BZBook getSingleBook(String bookId);
	
	public void setSingleBook(BZBook book);
}

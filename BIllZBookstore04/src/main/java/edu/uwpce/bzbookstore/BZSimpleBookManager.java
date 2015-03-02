package edu.uwpce.bzbookstore;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BZSimpleBookManager implements BZBookManager {

	private static final Logger logger = LoggerFactory.getLogger(BZSimpleBookManager.class);

	private Map<String, BZBook> books;

	@Value("classpath:/defaultbooks.json")
	private Resource defaultBooksResource;

	public BZSimpleBookManager() {
		this.books = new ConcurrentHashMap<String, BZBook>();
		logger.info("created new bookmanager, map size is: " + this.books.size());
	}
	

	@PostConstruct
	public void init() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			this.books = mapper.readValue(defaultBooksResource.getInputStream(),
					new TypeReference<HashMap<String, BZBook>>() {});
			logger.info("size of books in bookmanager after inputstream from json file: " + this.books.size());
		} catch (JsonParseException e) {
			logger.error("Got JSONParseException." + e);
			e.printStackTrace();
		} catch (JsonMappingException e) {
			logger.error("Got JSONMappingException." + e);
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("Got IOException." + e);
			e.printStackTrace();
		}
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

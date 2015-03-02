package edu.uwpce.bzbookstore;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.uwpce.bzbookstore.BZApiMessage.MsgType;

@RestController
@RequestMapping("/api")
public class BZApiController {

    @Autowired
    private BZBookManager bookManager;
    
    @Autowired
    private BZUserManager userManager;
    
    @Autowired
    private BZCartManager cartManager;
    
    @RequestMapping(value="/books", method=RequestMethod.GET)
    public Map<String, BZBook> getBooks() {
        return bookManager.getBooks();
    }

    @RequestMapping(value="/books/{isbn}", method=RequestMethod.GET)
    public Object getBook(@PathVariable("isbn") String isbn) {
        BZBook book = bookManager.getSingleBook(isbn);
        if (book != null) {
            return book;
        } else {
            return new BZApiMessage(MsgType.ERROR, "Book with ISBN=" + isbn + " does not exist.");
        }
    }
    
    @RequestMapping(value="/books", method=RequestMethod.POST)
    public Object createBook(@RequestBody BZBook book, HttpServletResponse response) {
        if (bookManager.getSingleBook(book.getISBN()) != null) {
            return new BZApiMessage(MsgType.ERROR, "Book with ISBN=" + book.getISBN() + " already exists.");
        }
        bookManager.setSingleBook(book);
        response.setStatus(HttpServletResponse.SC_CREATED);
        return bookManager.getSingleBook(book.getISBN());
    }
    
    @RequestMapping(value="/books/{isbn}", method=RequestMethod.PUT)
    public BZBook updateBook(@RequestBody BZBook book) {
        bookManager.updateBook(book);
        return bookManager.getSingleBook(book.getISBN());
    }
    
    @RequestMapping(value="/books/{isbn}", method=RequestMethod.DELETE)
    public Object deleteBook(@PathVariable("isbn") String isbn,
            HttpServletResponse response) {
        if (bookManager.deleteBook(isbn)) {
//            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            return new BZApiMessage(MsgType.INFO, "Book with ISBN=" + isbn + " has been removed.");
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return new BZApiMessage(MsgType.ERROR, "Book with ISBN=" + isbn + " was not found.");
        }
    }
    
    
    @RequestMapping(value="/accounts", method=RequestMethod.GET)
    public Map<Integer, BZUserInfo> getUsers(){
       	return userManager.getUsers();
    }
    
    
    @RequestMapping(value="/accounts/{userid}", method=RequestMethod.GET)
    public BZUserInfo getUser(HttpServletResponse response, @PathVariable("userid") int userid) {
    	return userManager.getSingleUserById(userid);
    }
    
    @RequestMapping(value="/accounts/{username}", method=RequestMethod.POST)
    public Object createUser(@RequestBody BZUserInfo newuser, @PathVariable("username") String username, HttpServletResponse response) {
		if (userManager.getSingleUserByUsername(username) != null) {
            return new BZApiMessage(MsgType.ERROR, "User with username= " + username + " already exists.");

		} else {
			userManager.setSingleUser(newuser);	
	        response.setStatus(HttpServletResponse.SC_CREATED);
			return userManager.getSingleUserByUsername(username);
		}
    	    	
    }
    
    
    @RequestMapping(value="/accounts/{userid}", method=RequestMethod.PUT)
    public BZUserInfo updateUser(@RequestBody BZUserInfo userinfo, @RequestParam("userid") int userid){
    	userManager.updateUser(userinfo);
    	return userManager.getSingleUserById(userid);
    }
    
    
    @RequestMapping(value="/accounts/{userid}", method=RequestMethod.DELETE)
    public Object deleteUser(@PathVariable("userid") int userid, HttpServletResponse response) {
    	if (userManager.deleteUser(userid)){
    		return new BZApiMessage(MsgType.INFO, "User with id: " + userid + " has been deleted.");
    	} else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return new BZApiMessage(MsgType.ERROR, "User with id: " + userid + " was not found.");
    	}
    }
    
    
    @RequestMapping(value="/carts/{cartid}", method=RequestMethod.GET)
    public Object getCart(@PathVariable("cartid") int cartid) {
        BZCart cart = cartManager.getCart(cartid);
        if (cart != null) {
            return cart;
        } else {
            return new BZApiMessage(MsgType.ERROR, "Cart with cart id: " + cartid + " does not exist.");
        }
    }

    
    @RequestMapping(value="/carts", method=RequestMethod.POST)
    public Object createCart(@RequestBody BZCart cart, HttpServletResponse response) {
        if (cartManager.getCart(cart.getCartId()) != null) {
            return new BZApiMessage(MsgType.ERROR, "Cart with id: " + cart.getCartId() + " already exists.");
        }
        cartManager.setCart(cart);
        response.setStatus(HttpServletResponse.SC_CREATED);
        return cartManager.getCart(cart.getCartId());
    }
    
    
    @RequestMapping(value="/carts/{cartid}", method=RequestMethod.PUT)
    public BZCart updateCart(@RequestBody BZCart cart) {
    	cartManager.updateCart(cart);
    	return cartManager.getCart(cart.getCartId());
    }
    
    
    @RequestMapping(value="/carts/{cartid}", method=RequestMethod.DELETE)
    public Object deleteCart(@PathVariable("cartid") int cartid, HttpServletResponse response) {
    	if (cartManager.deleteCart(cartid)) {
    		return new BZApiMessage(MsgType.INFO, "Cart with id: " + cartid + " has been deleted");
       	} else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return new BZApiMessage(MsgType.ERROR, "Cart with id: " + cartid + " was not found.");
    	}
    }
    
    
    
  
}

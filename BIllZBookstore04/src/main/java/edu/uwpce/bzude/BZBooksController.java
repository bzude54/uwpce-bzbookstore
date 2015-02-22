package edu.uwpce.bzude;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Handles requests for the application home page.
 */
@Controller
public class BZBooksController {
	
	private static final Logger logger = LoggerFactory.getLogger(BZBooksController.class);
	
	
	private BZBookManager bookManager;
	
	@Resource(name="bookManager")
	public void setBookManager(BZBookManager bookManager) {
		this.bookManager = bookManager;
	}
	
	
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/bzbooks", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		model.addAttribute("booksMap", bookManager.getBooks() );
		
		return "bzbooks";
	}
	
	
	@RequestMapping(value = "/bzbookdetails", method = RequestMethod.GET)
	public String bookDetails(Model model, HttpSession session, @RequestParam String id) {

        model.addAttribute("username", session.getAttribute("username"));
        
        BZBook book = bookManager.getSingleBook(id);
        
        if (book != null)
        {
        	model.addAttribute("bookDetail", book);
    		return "bzdetails";
        } else {
            return "redirect:";
        }       
        
	}

	
}

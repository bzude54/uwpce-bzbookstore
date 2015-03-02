package edu.uwpce.bzbookstore;

import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/api")
public class BZApiController {
	
	@Autowired
	private BZBookManager bookManager;
	
	
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/bzbooks", method = RequestMethod.GET)
	public ModelAndView home(Locale locale, Model model) {
		
//		model.addAttribute("booksMap", bookManager.getBooks() );
		
		return new ModelAndView("bzbooks", "booksMap", bookManager.getBooks());
	}
	
	
	@RequestMapping(value = "/bzbooks/{bookId}", method = RequestMethod.GET)
	public ModelAndView bookDetails(Model model, HttpSession session, @PathVariable("bookId") String bookId) {

        model.addAttribute("username", session.getAttribute("username"));
        
        BZBook book = bookManager.getSingleBook(bookId);
        model.addAttribute("bookDetail", book);
        model.addAttribute("bZBookReview", new BZBookReview());
    	
        return new ModelAndView("bzdetails", "bookDetail", book);

	}

	

}

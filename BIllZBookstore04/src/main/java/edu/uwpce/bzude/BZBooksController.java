package edu.uwpce.bzude;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Handles requests for the application home page.
 */
@Controller
public class BZBooksController {
	
	private static final Logger logger = LoggerFactory.getLogger(BZBooksController.class);
	
	
	@Autowired
	private BZBookManager bookManager;
	
	@Autowired
	private BZBookReviewManager bookReviewManager;
	
	@Value("classpath:defaultreviews.json")
	Resource defaultreviews = ctx.;
	

	
	
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
	
	
    @ResponseBody
    @RequestMapping(value="/reviews", method=RequestMethod.POST)
    public List<BZBookReview> getBookReviews(@RequestParam String isbn) {
        logger.info("ISBN from ajax = " + isbn);
        List<BZBookReview> reviews = new ArrayList<BZBookReview>();
//        reviews = bookReviewManager.getBookReviews(isbn);
//        if (reviews == null){
    	BZBookReview newreview1 = new BZBookReview(isbn, "My first review", new Date());
    	reviews.add(newreview1);
    	BZBookReview newreview2 = new BZBookReview(isbn, "My second review", new Date());
    	reviews.add(newreview2);
    	BZBookReview newreview3 = new BZBookReview(isbn, "My third review", new Date());
    	reviews.add(newreview3);
    	BZBookReview newreview4 = new BZBookReview(isbn, "My fourth review", new Date());
    	reviews.add(newreview4);
//        }
//        BZBookReview review = reviews.get(0);
        logger.info("review text1 = " + reviews.get(0).getReviewText());
        logger.info("review uuid1 = " + reviews.get(0).getReviewUuid());
        logger.info("review text2 = " + reviews.get(1).getReviewText());
        logger.info("review uuid2 = " + reviews.get(1).getReviewUuid());
        logger.info("review text3 = " + reviews.get(2).getReviewText());
        logger.info("review uuid3 = " + reviews.get(2).getReviewUuid());
        logger.info("review text4 = " + reviews.get(3).getReviewText());
        logger.info("review uuid4 = " + reviews.get(3).getReviewUuid());
        return reviews;
    }


	
}

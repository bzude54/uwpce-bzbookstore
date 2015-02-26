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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
        	model.addAttribute("bZBookReview", new BZBookReview());
    		return "bzdetails";
        } else {
            return "redirect:";
        }       
        
	}
	
	
    @ResponseBody
    @RequestMapping(value = "/showreviews", method = RequestMethod.POST)
    public List<BZBookReview> getBookReviews(@RequestParam String isbn, Model model) {
        logger.info("ISBN from ajax = " + isbn);
/*        List<BZBookReview> reviews = bookReviewManager.getBookReviews(isbn);
        if (reviews == null){
    	BZBookReview newreview1 = new BZBookReview(isbn, "My first review", new Date());
    	reviews.add(newreview1);
    	BZBookReview newreview2 = new BZBookReview(isbn, "My second review", new Date());
    	reviews.add(newreview2);
    	BZBookReview newreview3 = new BZBookReview(isbn, "My third review", new Date());
    	reviews.add(newreview3);
    	BZBookReview newreview4 = new BZBookReview(isbn, "My fourth review", new Date());
    	reviews.add(newreview4);
    	bookReviewManager.setBookReviews(reviews);
        }
        BZBookReview review = reviews.get(0);
*/   	List<BZBookReview> managerreviews = bookReviewManager.getBookReviews(isbn);
        logger.info("managerreviews text1 = " + managerreviews.get(0).getReviewText());
        logger.info("managerreviews uuid1 = " + managerreviews.get(0).getReviewUuid());
        logger.info("managerreviews text2 = " + managerreviews.get(1).getReviewText());
        logger.info("managerreviews uuid2 = " + managerreviews.get(1).getReviewUuid());
        logger.info("managerreviews text3 = " + managerreviews.get(2).getReviewText());
        logger.info("managerreviews uuid3 = " + managerreviews.get(2).getReviewUuid());
        logger.info("managerreviews text4 = " + managerreviews.get(3).getReviewText());
        logger.info("managerreviews uuid4 = " + managerreviews.get(3).getReviewUuid());
       return managerreviews;
    }
    
    @ResponseBody
    @RequestMapping(value = "/postreview", method = RequestMethod.POST)
    public List<BZBookReview> postReview(HttpSession session, @RequestParam String isbn, @RequestParam String reviewText) {
    	BZBookReview newreview = new BZBookReview(isbn, reviewText, new Date());
//    	newreview.setReviewIsbn(bookIsbn);
//    	newreview.setReviewText(review.getReviewText());
//    	newreview.setTimeStamp(new Date());
    	bookReviewManager.addBookReview(newreview);
       	List<BZBookReview> managerreviews = bookReviewManager.getBookReviews(isbn);
        logger.info("managerreviews from post text1 = " + managerreviews.get(0).getReviewText());
        logger.info("managerreviews from post uuid1 = " + managerreviews.get(0).getReviewUuid());
        logger.info("managerreviews from post text2 = " + managerreviews.get(1).getReviewText());
        logger.info("managerreviews from post uuid2 = " + managerreviews.get(1).getReviewUuid());
        logger.info("managerreviews from post text3 = " + managerreviews.get(2).getReviewText());
        logger.info("managerreviews from post  uuid3 = " + managerreviews.get(2).getReviewUuid());
        logger.info("managerreviews from post text4 = " + managerreviews.get(3).getReviewText());
        logger.info("managerreviews from post uuid4 = " + managerreviews.get(3).getReviewUuid());
        logger.info("managerreviews from post text5 = " + managerreviews.get(4).getReviewText());
        logger.info("managerreviews from post uuid5 = " + managerreviews.get(4).getReviewUuid());
     	
    	
    	return managerreviews;
    	
    }
    


	
}

package sof03.MyReads.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import sof03.MyReads.domain.Book;
import sof03.MyReads.domain.BookRepository;
import sof03.MyReads.domain.Format;
import sof03.MyReads.domain.FormatRepository;
import sof03.MyReads.domain.Genre;
import sof03.MyReads.domain.GenreRepository;
import sof03.MyReads.domain.Review;
import sof03.MyReads.domain.ReviewRepository;
import sof03.MyReads.domain.Status;
import sof03.MyReads.domain.StatusRepository;

@CrossOrigin
@Controller
public class RestController {
	
	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private StatusRepository statusRepository;

	@Autowired
	private GenreRepository genreRepository;

	@Autowired
	private FormatRepository formatRepository;
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	//BOOKS
	
	//Get all books
	@GetMapping(value = "/books")
	public @ResponseBody List<Book> getBooksRest(){
		return (List<Book>) bookRepository.findAll();
	}
	
	//Get book by id
	@GetMapping(value = "/books/{id}")
	public @ResponseBody Optional<Book> findBookRest(@PathVariable("id")Long id){
		return bookRepository.findById(id);
	}
	
	//Add a new book
	@PostMapping(value = "/books")
	public @ResponseBody Book postBookRest(@RequestBody Book book) {
		return bookRepository.save(book);
	}
	
	//Update book
	@PutMapping(value = "/books")
	public @ResponseBody Book updateBookRest(@RequestBody Book book) {
		return bookRepository.save(book);
	}
	
	//Delete book
	@DeleteMapping(value = "/books/{id}")
	public @ResponseBody void deleteBookRest(@PathVariable("id") Long id) {
		bookRepository.deleteById(id);
	}
	
	//GENRES
	
	//Get all genres
	@GetMapping(value = "/genres")
	public @ResponseBody List<Genre> getGenresRest(){
		return (List<Genre>) genreRepository.findAll();
	}
	
	//Get genre by id
	@GetMapping(value = "/genres/{id}")
	public @ResponseBody Optional<Genre> findGenreRest(@PathVariable("id")Long id){
		return genreRepository.findById(id);
	}
	
	//Add a new genre
	@PostMapping(value = "/genres")
	public @ResponseBody Genre postGenreRest(@RequestBody Genre genre) {
		return genreRepository.save(genre);
	}
	
	//STATUSES
	
	//Get all statuses
	@GetMapping(value = "/statuses")
	public @ResponseBody List<Status> getStatusesRest(){
		return (List<Status>) statusRepository.findAll();
	}
	
	//Get status by id
	@GetMapping(value = "/statuses/{id}")
	public @ResponseBody Optional<Status> findStatusRest(@PathVariable("id")Long id){
		return statusRepository.findById(id);
	}
	
	//Add a new status
	@PostMapping(value = "/statuses")
	public @ResponseBody Status postStatusRest(@RequestBody Status status) {
		return statusRepository.save(status);
	}
	
	//FORMATS

	//Get all formats
	@GetMapping(value = "/formats")
	public @ResponseBody List<Format> getFormatsRest(){
		return (List<Format>) formatRepository.findAll();
	}
	
	//Get format by id
	@GetMapping(value = "/formats/{id}")
	public @ResponseBody Optional<Format> findFormatRest(@PathVariable("id")Long id){
		return formatRepository.findById(id);
	}
	
	//Add a new format
	@PostMapping(value = "/formats")
	public @ResponseBody Format postStatusRest(@RequestBody Format format) {
		return formatRepository.save(format);
	}
	
	//REVIEWS
	
	//Get all reviews
	@GetMapping(value = "/reviews")
	public @ResponseBody List<Review> getReviewsRest(){
		return (List<Review>) reviewRepository.findAll();
	}
	
	//Get review by id
	@GetMapping(value = "/reviews/{id}")
	public @ResponseBody Optional<Review> findReviewRest(@PathVariable("id")Long id){
		return reviewRepository.findById(id);
	}
	
	//Add a review
	@PostMapping(value = "/reviews")
	public @ResponseBody Review postReviewRest(@RequestBody Review review) {
		return reviewRepository.save(review);
	}
}

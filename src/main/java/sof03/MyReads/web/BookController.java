package sof03.MyReads.web;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jakarta.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import sof03.MyReads.domain.Book;
import sof03.MyReads.domain.BookRepository;
import sof03.MyReads.domain.FormatRepository;
import sof03.MyReads.domain.GenreRepository;
import sof03.MyReads.domain.ReviewRepository;
import sof03.MyReads.domain.StatusRepository;

@Controller
public class BookController implements WebMvcConfigurer{
	
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private FormatRepository formatRepository;
	@Autowired
	private GenreRepository genreRepository;
	@Autowired
	private StatusRepository statusRepository;
	@Autowired
	private ReviewRepository reviewRepository;
	
	
	//Get home page with books in tables by status
	@GetMapping(value="/MyReads")
	public String getHomePage(Model model) {
		List<Book> read_books = (List<Book>) bookRepository.findByStatus_Name("Read");
		model.addAttribute("read_books", read_books);
		
		List<Book> wtr_books = (List<Book>) bookRepository.findByStatus_Name("Want to read");
		model.addAttribute("wtr_books", wtr_books);
		
		List<Book> cr_books = (List<Book>) bookRepository.findByStatus_Name("Currently reading");
		model.addAttribute("cr_books", cr_books);
		
		return "MyReads";
	}
	
	//Add book
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping(value = "/addbook")
	public String addBook(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("formats", formatRepository.findAll());
		model.addAttribute("genres", genreRepository.findAll());
		model.addAttribute("statuses", statusRepository.findAll());
		model.addAttribute("reviews", reviewRepository.findAll());
		return "addbook";
	}
	
	//Save book without review
	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping(value="/savebook", params = "save")
	public String saveBook(@ModelAttribute @Valid Book book, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("formats", formatRepository.findAll());
			model.addAttribute("genres", genreRepository.findAll());
			model.addAttribute("statuses", statusRepository.findAll());
			model.addAttribute("reviews", reviewRepository.findAll());
			return "addbook";
		}
		bookRepository.save(book);
		return "redirect:/MyReads";
	}
	
	//Save book with review
	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping(value="/savebook", params = "givereview")
	public String saveBookReview(@ModelAttribute Book book, Model model) {
		if (book.getStatus().getName() == "Read") {
			model.addAttribute("hasRead", true);
		} else {
			model.addAttribute("message", "You have not read this book!");
			model.addAttribute("hasRead", false);
		}
		model.addAttribute("formats", formatRepository.findAll());
		model.addAttribute("genres", genreRepository.findAll());
		model.addAttribute("statuses", statusRepository.findAll());
		model.addAttribute("reviews", reviewRepository.findAll());
		return "addbook";
	}
	
	//Delete book
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping(value="/deletebook/{id}")
	public String deleteBook(@PathVariable("id")Long id, Model model) {
		bookRepository.deleteById(id);
		return "redirect:../MyReads";
	}
	
	//Edit book
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value="/editbook/{id}")
	public String editBook(@PathVariable("id")Long id, Model model) {
		Book book = bookRepository.getById(id);
		if (book.getStatus().getName() == "Read") {
			model.addAttribute("hasRead", true);
		} else {
			model.addAttribute("hasRead", false);
		}
		model.addAttribute("book", bookRepository.findById(id));
		model.addAttribute("genres", genreRepository.findAll());
		model.addAttribute("formats", formatRepository.findAll());
		model.addAttribute("statuses", statusRepository.findAll());
		model.addAttribute("reviews", reviewRepository.findAll());
		return "editbook";
	}
	
	//Save edit without review
	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping(value="/saveedit", params="save")
	public String saveEdit(@ModelAttribute @Valid Book book, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("formats", formatRepository.findAll());
			model.addAttribute("genres", genreRepository.findAll());
			model.addAttribute("statuses", statusRepository.findAll());
			model.addAttribute("reviews", reviewRepository.findAll());
			return "editbook";
		}
		bookRepository.save(book);
		return "redirect:/MyReads";
	}
	
	//Save edit with review
	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping(value="/saveedit", params = "givereview")
	public String saveEditReview(@ModelAttribute Book book, Model model) {
		if (book.getStatus().getName() == "Read") {
			model.addAttribute("hasRead", true);
		} else {
			model.addAttribute("message", "You have not read this book!");
			model.addAttribute("hasRead", false);
		}
		model.addAttribute("formats", formatRepository.findAll());
		model.addAttribute("genres", genreRepository.findAll());
		model.addAttribute("statuses", statusRepository.findAll());
		model.addAttribute("reviews", reviewRepository.findAll());
		return "editbook";
	}
	
}

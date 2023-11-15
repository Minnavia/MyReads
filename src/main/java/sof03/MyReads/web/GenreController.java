package sof03.MyReads.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.validation.Valid;
import sof03.MyReads.domain.FormatRepository;
import sof03.MyReads.domain.Genre;
import sof03.MyReads.domain.GenreRepository;
import sof03.MyReads.domain.StatusRepository;

@Controller
public class GenreController {
	
	@Autowired
	private GenreRepository genreRepository;
	
	@Autowired
	private FormatRepository formatRepository;
	
	@Autowired
	private StatusRepository statusRepository;
	
	
	//List all genres, formats, and statuses
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping(value="/attributes")
	public String getAllGenres(Model model) {
		model.addAttribute("genres", genreRepository.findAll());
		model.addAttribute("formats", formatRepository.findAll());
		model.addAttribute("statuses", statusRepository.findAll());
		return "attributelist";
	}
	
	//Add genre
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping(value = "/addgenre")
	public String addGenre(Model model) {
		model.addAttribute("genre", new Genre());
		return "addgenre";
	}
	
	//Save genre
	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping(value="/savegenre")
	public String saveGenre(@ModelAttribute @Valid Genre genre, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "addgenre";
		}
		genreRepository.save(genre);
		return "redirect:/attributes";
	}
	
	//Edit genre
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value="/editgenre/{id}")
	public String editGenre(@PathVariable("id")Long id, Model model) {
		model.addAttribute("genre", genreRepository.findById(id));
		return "editgenre";
	}

}

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
import sof03.MyReads.domain.Format;
import sof03.MyReads.domain.FormatRepository;

@Controller
public class FormatController {
	
	@Autowired
	private FormatRepository formatRepository;
	
	//Add format
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping(value = "/addformat")
	public String addFormat(Model model) {
		model.addAttribute("format", new Format());
		return "addformat";
	}
	
	//Save format
	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping(value="/saveformat")
	public String saveFormat(@ModelAttribute @Valid Format format, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "addformat";
		}
		formatRepository.save(format);
		return "redirect:/attributes";
	}
	
	//Edit format
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value="/editformat/{id}")
	public String editFormat(@PathVariable("id")Long id, Model model) {
		model.addAttribute("format", formatRepository.findById(id));
		return "editformat";
	}

}

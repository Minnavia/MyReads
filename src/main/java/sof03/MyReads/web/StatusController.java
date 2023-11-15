package sof03.MyReads.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.validation.Valid;
import sof03.MyReads.domain.Status;
import sof03.MyReads.domain.StatusRepository;

@Controller
public class StatusController {

	@Autowired
	private StatusRepository statusRepository;
	
	
	//Add status
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping(value = "/addstatus")
	public String addStatus(Model model) {
		model.addAttribute("status", new Status());
		return "addstatus";
	}
	
	//Save status
	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping(value="/savestatus")
	public String saveStatus(@ModelAttribute @Valid Status status, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "addstatus";
		}
		statusRepository.save(status);
		return "redirect:/attributes";
	}
	
}

package com.app.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.model.Family;
import com.app.model.Partner;
import com.app.model.User;
import com.app.service.FamilyService;
import com.app.service.PartnerService;
import com.app.service.UserService;

@Controller
public class WebController {

	@Autowired
	FamilyService familyService;

	@Autowired
	PartnerService partnerService;

	@Autowired
	UserService userService;

	@Autowired
	AuthenticationManager authenticationManager;

	@GetMapping("/")
	public String home() {
		return "index";
	}

	@PostMapping("/validateLoggin")
	public String validateLoggin(@ModelAttribute User user, HttpServletRequest request, Model model)
			throws IOException {
		try {
			UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
					user.getUserName(), user.getPassword());
			final Authentication auth = authenticationManager.authenticate(authRequest);
			if (auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
				SecurityContextHolder.getContext().setAuthentication(auth);
				request.getSession().setAttribute("usuario", user.getUserName());
				request.getSession().setAttribute("auth", auth);
			}
		} catch (BadCredentialsException e) {
			System.out.println("Credenciales incorrectas");
		}
		return "redirect:/";
	}

	@GetMapping("/loggout")
	public String loggout(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			request.getSession().removeAttribute("usuario");
			request.getSession().removeAttribute("auth");
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/";
	}

	@GetMapping("/registerNewUser")
	public String createNewUser() {
		return "createUser";
	}

	@PostMapping("/saveNewUser")
	public String saveNewUser(@ModelAttribute User user, Model model) {
		String password = user.getPassword();
		String passConfirm = user.getConfirmPassword();
		if (password.equals(passConfirm)) {
			user.setRoll("ROLE_" + user.getRoll());
			try {
				userService.save(user);
				model.addAttribute("mensaje", "successUser");
			} catch (TransactionSystemException e) {
				System.out.println("exception catched: " + e);
				model.addAttribute("mensaje", "emptyFields");
			} catch (DataIntegrityViolationException e) {
				System.out.println("exception catched: " + e);
				model.addAttribute("mensaje", "notUniqueUsername");
			}
		} else
			model.addAttribute("mensaje", "notMatchPass");
		model.addAttribute("User", user);
		return "createUser";
	}

	@GetMapping("/callFromSearch")
	public String interceptCallFromList(@RequestParam(name = "search") String name, Model model,
			HttpServletRequest request) {
		if (request.getParameter("buscar") != null) {
			Iterable<Family> list;
			if (name.equals(""))
				list = familyService.findAll();
			else
				list = familyService.findByNombre(name);
			model.addAttribute("list", list);
			model.addAttribute("path", "findAllFamily");
			return "famList";
		} else {
			return "addFamMemb";
		}
	}

	@GetMapping("/callFromSearchPart")
	public String interceptCallFromPartList(@RequestParam(name = "search") String name, Model model,
			HttpServletRequest request) {
		if (request.getParameter("buscar") != null) {
			List<Partner> list;
			if (name.equals(""))
				list = partnerService.findAll();
			else
				list = partnerService.findByNombre(name);
			model.addAttribute("list", list);
			return "partList";
		} else {
			return "addPartner";
		}
	}

	@GetMapping("/findAllFamily")
	public String findAllFamily(Model model) {
		List<Family> list = familyService.findAll();
		model.addAttribute("list", list);
		return "famList";
	}

	@GetMapping("/findAllPartner")
	public String findAllPartner(Model model) {
		List<Partner> list = partnerService.findAll();
		model.addAttribute("list", list);
		return "partList";
	}

	@PostMapping("/saveFamMemb")
	public String saveFamMemb(@ModelAttribute Family family, Model model, HttpServletRequest request) {
		if (request.getParameter("Guardar") != null) {
			try {
				familyService.save(family);
				model.addAttribute("mensaje", "successFam");
			} catch (TransactionSystemException e) {
				System.out.println("exception catched: " + e);
				model.addAttribute("mensaje", "emptyFields");
				family.setId(null);
				model.addAttribute("Family", family);
			}
		} else if (request.getParameter("Actualizar") != null) {
			try {
				familyService.save(family);
				model.addAttribute("mensaje", "successFamUpdate");
			} catch (TransactionSystemException e) {
				System.out.println("exception catched: " + e);
				model.addAttribute("mensaje", "emptyFields");
				model.addAttribute("Family", family);
			}
		} else {
			familyService.delete(family);
			model.addAttribute("mensaje", "successDeletedFam");
		}
		return "/addFamMemb";
	}

	@PostMapping("/savePartner")
	public String savePartner(@ModelAttribute Partner partner, Model model, HttpServletRequest request) {
		if (request.getParameter("Guardar") != null) {
			try {
				partnerService.save(partner);
				model.addAttribute("mensaje", "successPartner");
			} catch (TransactionSystemException e) {
				System.out.println("exception catched: " + e);
				model.addAttribute("mensaje", "emptyFields");
				partner.setId(null);
				model.addAttribute("Partner", partner);
			}
		} else if (request.getParameter("Actualizar") != null) {
			try {
				partnerService.save(partner);
				model.addAttribute("mensaje", "successPartnerUpdate");
			} catch (TransactionSystemException e) {
				System.out.println("exception catched: " + e);
				model.addAttribute("mensaje", "emptyFields");
				model.addAttribute("Partner", partner);
			}
		} else {
			partnerService.delete(partner);
			model.addAttribute("mensaje", "successDeletedPartner");
		}
		return "/addPartner";
	}

	@GetMapping("/editFam")
	public String editFam(@RequestParam(name = "idFam") Long idFam, Model model) {
		Family fam = null;
		try {
			fam = familyService.findById(idFam);
		} catch (Exception e) {
			return "redirect:/findAllFamily";
		}
		model.addAttribute("Family", fam);
		return "addFamMemb";
	}

	@GetMapping("/editPartner")
	public String editPartner(@RequestParam(name = "idPartner") Long idPartner, Model model) {
		Partner partner = null;
		try {
			partner = partnerService.findById(idPartner);
		} catch (Exception e) {
			return "redirect:/findAllPartner";
		}
		model.addAttribute("Partner", partner);
		return "addPartner";
	}
}

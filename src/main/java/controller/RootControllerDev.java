package controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class RootControllerDev {
	
	Logger logger = LoggerFactory.getLogger(RootControllerDev.class);

	@RequestMapping(method = RequestMethod.GET)
	public String root() {
//		setDefaultUser();
		return "redirect:/index.html";
	}
//	private void setDefaultUser() {
//		if (!SecurityContextHolder.getContext().getAuthentication().getName().equals("admin")) {
//			Collection<GrantedAuthority> authorities = new HashSet();
//			GrantedAuthority a = new SimpleGrantedAuthority("ROLE_ADMIN");
//			authorities.add(a);
//			SecurityContextHolder.getContext()
//					.setAuthentication(new UsernamePasswordAuthenticationToken("admin", "admin", authorities));
//
//		}
//	}
}

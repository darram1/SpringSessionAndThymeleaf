package es.iesjandula.springsessionandthymeleaf.rest;

import es.iesjandula.springsessionandthymeleaf.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ThymleafController
{
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showForm(Model model)
    {
        model.addAttribute("user", new User());
        return "form.html";
    }

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public String showUser(@ModelAttribute User user)
    {
        return "user.html";
    }

    @RequestMapping(value = "/otro_ejemplo", method = RequestMethod.GET)
    public String getExampleHTML(Model model)
    {
        model.addAttribute("tittle", "UD2. El mundo de los microservicios");
        model.addAttribute("description", "<strong>Spring Boot</strong> y sus múltiples usos");
        model.addAttribute("evaluacion", "<strong>2º Evalucion</strong>");
        model.addAttribute("curso", "<strong>2ºDAM</strong>");
        return "otro_ejemplo.html";
    }
}

package es.iesjandula.springsessionandthymeleaf.rest;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RestController
public class MyRestController
{
    public MyRestController()
    {
        // Empty constructor because of Spring dependency
    }

    @RequestMapping(method = RequestMethod.GET, value =  "/userVisits")
    public ResponseEntity<Integer>  handleRequest(HttpSession session)
    {
        // Get the value of session key (int)
        Integer userVisits = (Integer) session.getAttribute("userVisits");

        // Check if value is null to initialize it
        if(userVisits == null)
        {
            userVisits = 0;
        }

        // Increment the event
        userVisits ++;

        // Get the previos value
        System.out.println("User visits: " + userVisits);

        // Store int this session the current value
        session.setAttribute("userVisits", userVisits);

        // Return the value
        return ResponseEntity.ok().body(userVisits);
    }

    @RequestMapping(method = RequestMethod.GET, value =  "/ordenarEnteros/{numeroEntero}")
    public ResponseEntity<List<Integer>>  ordenarEnteros(HttpSession session, @PathVariable(value="numeroEntero")  int numeroEntero )
    {
        // Get the value of session key (int)
        List<Integer> listaNumeros= (List<Integer>) session.getAttribute("ordenarEnteros");

        // Check if value is null to initialize it
        if(listaNumeros == null)
        {
            listaNumeros = new ArrayList<>();
        }

        listaNumeros.add(numeroEntero);

        Collections.sort(listaNumeros);

        // Get the previos value
        System.out.println("Lista numeros: " + listaNumeros);

        // Store int this session the current value
        session.setAttribute("ordenarEnteros", listaNumeros);

        // Return the value
        return ResponseEntity.ok().body(listaNumeros);
    }
}

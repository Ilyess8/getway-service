package org.sid.getwayservice.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class CountriesController {
    @GetMapping("/defaultCountries")
   public Map<String,String> countries() {
        Map<String,String> data = new HashMap<>() ;
        data.put("message" , "default countries") ;
        data.put("countries" , "tunisie , algérie , marroc ...");
        return data ;
    }

    @GetMapping("/defaultsalat")
    public Map<String,String> muslim() {
        Map<String,String> data = new HashMap<>() ;
        data.put("message" , "Horaire salawat en tunisie") ;
        data.put("fajer" , "3:55");
        data.put("dohor " , "12:28 ") ;
        return data ;
    }
}

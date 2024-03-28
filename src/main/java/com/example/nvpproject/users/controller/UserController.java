package com.example.nvpproject.users.controller;
import com.example.nvpproject.Seller.dto.SellerDto;
import com.example.nvpproject.users.dto.UserDto;
import com.example.nvpproject.users.service.UserServcie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/aqrab/api", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class UserController {
    private UserServcie userServcie  ;

    @Autowired
   public UserController(UserServcie userServcie  ){
       this.userServcie=userServcie;
    }

    @RequestMapping(value = "/auth/register", method = RequestMethod.POST)
    public UserDto saveUser(@RequestBody UserDto userdto) throws Exception{
        try {
            UserDto userDto=userServcie.createUser(userdto);
            return userDto;
        }catch (Exception e){
            throw  new Exception("Message :"+e);
        }
    }
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public UserDto login(@RequestParam("email") String email, @RequestParam("password") String password) {
        UserDto userDto = userServcie.login(email, password);
        return userDto;
    }

    @RequestMapping(value = "/listSeller", method = RequestMethod.GET)
    public List<SellerDto> getListSeller(){
        List<SellerDto> sellerDtoList = userServcie.getListSeller();
        return sellerDtoList;
    }

  /*  @RequestMapping(path = "{idUtilisateur}", method = RequestMethod.GET)
    public UtilisateurDTO findUser(@PathVariable(name = "idUtilisateur") Integer idUtilisateur, @LoggedInActor Utilisateur actor) {
        return utilisateurService.getUser(idUtilisateur, actor, false);
    }
    */


/*
    @GetMapping("/public")
    public String publicEndpoint() {
        return "This is a public endpoint";
    }

    @GetMapping("/private")
    public String privateEndpoint() {
        return "This is a private endpoint";
    }*/
}




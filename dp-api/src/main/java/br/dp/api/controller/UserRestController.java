package br.dp.api.controller;

import br.dp.api.service.UserService;
import br.dp.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = "*")
public class UserRestController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Usuario> login(@RequestHeader("Authorization") final String encodedData) {

        System.out.println("Chegou request com base64: " + encodedData);

        final Usuario user = userService.validateLogin(encodedData);

        if (user == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(user);

    }


}

package br.dp.web.controller;

import br.dp.model.Usuario;
import br.dp.web.security.provider.DpAuthenticationProvider;
import br.dp.web.service.FileService;
import br.dp.web.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.AuthProvider;

@Controller
@RequestMapping("/perfil")
public class ProfileWebController {

    @Autowired
    private DpAuthenticationProvider authProvider;

    @Autowired
    private FileService fileService;

    @GetMapping("/detalhes")
    public String getProfileDetailPage(Model model) {

        Usuario user = authProvider.getAuthenticatedUser();
        String file = fileService.downloadUserFile(user.getId());
        user.setProfileImg(file != null ? file : Constants.USER_DEFAULT_IMG);
        model.addAttribute("usuario", user);

        return "profile/profile-detail-page";
    }

    @GetMapping("/editar-perfil/{id}")
    public String getEditProfilePage(@RequestParam("id") Long id, Usuario user, Model model) {

        model.addAttribute("user", user);
        return "profile/profile-edit-page";
    }

    @GetMapping("/alterar-senha/{senha}")
    public String getEditPasswordPage(@PathVariable("senha") final String senha) throws InterruptedException {
        System.out.println("ola a senha e: " + senha);
        return "profile/profile-detail-page";
    }

}

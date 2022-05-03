package rentABike.web.controller;


import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import rentABike.model.AddToFavourites;
import rentABike.model.User;
import rentABike.service.FavouritesService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/favourites")
public class FavouritesController {

    private final FavouritesService favouritesService;

    public FavouritesController (FavouritesService favouritesService) {
        this.favouritesService = favouritesService;
    }

    @GetMapping
    public String getFavouritesPage(@RequestParam(required = false) String error, HttpServletRequest req, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        String username = req.getRemoteUser();
        AddToFavourites addToFavourites = this.favouritesService.getActiveFavourites(username);
        model.addAttribute("rent", this.favouritesService.listAll(addToFavourites.getId()));
        return "favourites";
    }

    @PostMapping("/add/{id}")
    public String addToFavourites(@PathVariable Long id, HttpServletRequest req, Authentication authentication) {
        try {
            User user = (User) authentication.getPrincipal();
            AddToFavourites addToFavourites = this.favouritesService.addToFavourites(user.getUsername(), id);
            return "redirect:/favourites";
        }catch (RuntimeException exception) {
            return "redirect:/favouritest?error=" + exception.getMessage();
        }
    }
}

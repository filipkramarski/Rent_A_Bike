package rentABike.web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import rentABike.model.Accessories;
import rentABike.model.Brand;
import rentABike.model.Category;
import rentABike.model.Rent;
import rentABike.repository.jpa.AccessoriesRepository;
import rentABike.service.AccessoriesService;
import rentABike.service.CategoryService;

import java.util.List;

@Controller
@RequestMapping("/accessories")
public class AccessoriesController {

    private final CategoryService categoryService;
    private final AccessoriesService accessoriesService;
    private final AccessoriesRepository accessoriesRepository;

    public AccessoriesController (CategoryService categoryService, AccessoriesService accessoriesService, AccessoriesRepository accessoriesRepository) {
        this.categoryService = categoryService;
        this.accessoriesService = accessoriesService;
        this.accessoriesRepository = accessoriesRepository;
    }

    @GetMapping
    public String getAccessoriesPage(@RequestParam(required = false) String error, Model model) {
        if(error != null && !error.isEmpty()){
            model.addAttribute("hasError",true);
            model.addAttribute("error",error);
        }
        List<Accessories> accessories = this.accessoriesService.findAll();
        model.addAttribute("accessories", accessories);
        return "accessories.html";
    }
}

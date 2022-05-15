package rentABike.web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
        model.addAttribute("bodyContent","accessories");
        return "master-template";
    }

    @GetMapping("/addNew")
    public String addPage(Model model) {
        List<Category> categories = this.categoryService.listCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("bodyContent","addNewAccessory");
        return "master-template";
    }

    @PostMapping("/add")
    public String saveNew(@RequestParam String name,
                          @RequestParam Double price,
                          @RequestParam Integer quantity,
                          @RequestParam Long category) {
        this.accessoriesService.save(name,price,quantity,category);
        return "redirect:/accessories";
    }

    @GetMapping("/edit-form/{id}")
    public String editRentPage(@PathVariable Long id, Model model) {
        if(this.accessoriesService.findById(id).isPresent()){
            Accessories accessories = this.accessoriesService.findById(id).get();
            List<Category> categories = this.categoryService.listCategories();
            model.addAttribute("categories", categories);
            model.addAttribute("bodyContent","addNewAccessory");
            return "master-template";
        }
        return "redirect:/accessories?error=ProductNotFound";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteRentCity(@PathVariable Long id) {
        this.accessoriesService.deleteById(id);
        return "redirect:/accessories";
    }

}

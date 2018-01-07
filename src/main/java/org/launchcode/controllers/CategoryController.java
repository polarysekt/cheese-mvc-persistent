package org.launchcode.controllers;

import org.launchcode.models.Category;
import org.launchcode.models.data.CategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryDao categoryDao;

    public void CategoryController() {

    }

    @RequestMapping(value="")
    public String indexHandler(Model model) {
        Iterable<Category> cat = categoryDao.findAll();
        model.addAttribute( "title", "Categories");
        model.addAttribute( "cats", cat );
        return "category/index";
    }

    @RequestMapping(value="add", method = RequestMethod.GET)
    public String catHandler(Model model) {
        model.addAttribute( new Category() );
        model.addAttribute( "title", "New Category");
        return "category/add";
    }

    @RequestMapping(value="add", method = RequestMethod.POST)
    public String catPoster(Model model, @ModelAttribute @Valid Category category, Errors errors){

        if( errors.hasErrors() ) {
            model.addAttribute( "title", "New Category");
            return "category/add";
        }

        categoryDao.save(category);
        return "redirect:";

    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveCatForm(Model model) {
        model.addAttribute("cats", categoryDao.findAll());
        model.addAttribute("title", "Remove Categories");
        return "category/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveCatForm(@RequestParam int[] catIds) {

        for (int catId : catIds) {
            categoryDao.delete(catId);
        }

        return "redirect:";
    }


}

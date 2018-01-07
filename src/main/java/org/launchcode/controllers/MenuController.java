package org.launchcode.controllers;

import org.launchcode.models.Cheese;
import org.launchcode.models.Menu;
import org.launchcode.models.data.CheeseDao;
import org.launchcode.models.data.MenuDao;
import org.launchcode.models.forms.AddMenuItemForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping(value="menu")
public class MenuController {
    @Autowired
    MenuDao menuDao;

    @Autowired
    CheeseDao cheeseDao;

    @RequestMapping(value="")
    public String index(Model model) {

        model.addAttribute("menus", menuDao.findAll());
        model.addAttribute("title", "Menus");

        return "menu/index";
    }

    @RequestMapping(value="add", method=RequestMethod.GET)
    public String showAdd( Model model ) {
        model.addAttribute( "title", "Add Menu");
        model.addAttribute( "menu", new Menu() );
        return "menu/add";
    }

    @RequestMapping(value="add", method=RequestMethod.POST)
    public String processAdd(Model model, @ModelAttribute @Valid Menu menu, Errors errors){

        if( errors.hasErrors() ) {
            model.addAttribute( "title", "Add Menu");
            //model.addAttribute( "menu", menu );
            return "menu/add";
        }

        menuDao.save(menu);
        return "redirect:view/" + menu.getId();
    }


    @RequestMapping(value="view/{menuID}", method=RequestMethod.GET)
    public String viewMenu( Model model, @PathVariable int menuID ) {
        Menu thisMenu = menuDao.findOne(menuID);
        model.addAttribute( "title", thisMenu.getName());
        model.addAttribute( "menu", thisMenu );
        return "menu/view";
    }

    @RequestMapping(value="add-item/{itemID}", method=RequestMethod.GET)
    public String addItem( Model model, @PathVariable int itemID ) {
        AddMenuItemForm amif = new AddMenuItemForm( menuDao.findOne(itemID), cheeseDao.findAll() );

        model.addAttribute( "title", "Add item to menu: " + amif.getMenu().getName() );
        model.addAttribute( "form", amif );
        //model.addAttribute( "cheeses", cheeseDao.findAll() );

        return "menu/add-item";
    }

    @RequestMapping(value="add-item/{itemID}", method=RequestMethod.POST)
    public String processAddItem(Model model, @ModelAttribute @Valid AddMenuItemForm form, Errors errors){

        if (errors.hasErrors() ) {
            model.addAttribute( "form", form );
            model.addAttribute( "title", "Add item to menu: " + form.getMenu().getName() );
            return "menu/add-item";
        }
System.out.println("post had happened!");
//        Menu theMenu = form.getMenu();
//        theMenu.addItem( cheeseDao.findOne(form.getCheeseId()));
//        menuDao.save(theMenu);

        Menu theMenu = menuDao.findOne( form.getMenuId() );
        Cheese theCheese = cheeseDao.findOne(form.getCheeseId());
        theMenu.addItem( theCheese );

        System.out.println( menuDao.save( theMenu ) );
        //menuDao.save( menuDao.findOne().addItem(cheeseDao.findOne(form.getCheeseId())));

        return "redirect:/menu/view/" + form.getMenuId();

    }

}

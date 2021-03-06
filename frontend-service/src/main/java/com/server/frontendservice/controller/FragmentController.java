package com.server.frontendservice.controller;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.server.common.model.Fragment;
import com.server.frontendservice.service.FragmentService;

@Controller
public class FragmentController extends BaseController
{
    private static final String PATH = "configuration/fragments";

    @Autowired
    private FragmentService fragmentService;

    @GetMapping(PATH)
    public void fragments(Model model) throws InterruptedException, ExecutionException {
        model.addAttribute("fragments", fragmentService.getAll().get());
        css(model, "data-tables", "data-tables/dashboards", "font-awesome.min");
        js(model, "data-tables", "font-awesome.min");
    }

    @GetMapping(value = PATH + "/create")
    public String createView(Model model) {
        model.addAttribute("item", new Fragment());
        return "configuration/fragments/edit";
    }

    @GetMapping(PATH + "/{id}")
    public String dashboard(Model model, @PathVariable("id") Long id) {
        model.addAttribute("item", fragmentService.get(id));
        return "/configuration/fragments/edit";
    }

    @PostMapping(value = PATH + "/update")
    public String update(Model model,
                         @ModelAttribute("fragment") Fragment fragment,
                         RedirectAttributes redirect)
    {
        return create(model, fragment, redirect);
    }

    @PostMapping(value = PATH + "/create")
    public String create(Model model,
                         @ModelAttribute("fragment") Fragment fragment,
                         RedirectAttributes redirect)
    {
        fragmentService.update(fragment);
        model.addAttribute("item", fragment);

        toast("Successfully updated fragment", redirect);
        return "redirect:/configuration/fragments";
    }
}

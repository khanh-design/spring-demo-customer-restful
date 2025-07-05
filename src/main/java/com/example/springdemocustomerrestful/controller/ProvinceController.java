package com.example.springdemocustomerrestful.controller;

import com.example.springdemocustomerrestful.model.Customer;
import com.example.springdemocustomerrestful.model.Province;
import com.example.springdemocustomerrestful.service.impl.CustomerService;
import com.example.springdemocustomerrestful.service.impl.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/provinces")
public class ProvinceController {
    @Autowired
    private ProvinceService provinceService;

    @Autowired
    private CustomerService customerService;

    @GetMapping("")
    public ModelAndView showListProvinces() {
        ModelAndView modelAndView = new ModelAndView("/province/home");
        Iterable<Province> provinces = provinceService.findAll();
        modelAndView.addObject("provinces", provinces);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView createProvince() {
        ModelAndView modelAndView = new ModelAndView("/province/create");
        modelAndView.addObject("province", new Province());
        return modelAndView;
    }

    @PostMapping("/create")
    public String createProvince(@ModelAttribute("province") Province province, RedirectAttributes redirectAttributes) {
        provinceService.save(province);
        redirectAttributes.addFlashAttribute("message", "successful creations province");
        return "redirect:/provinces";
    }

    @GetMapping("/update/{id}")
    public ModelAndView updateProvince(@PathVariable Long id) {
        Optional<Province> province = provinceService.findById(id);
        if (province.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/province/update");
            modelAndView.addObject("province", province.get());
            return modelAndView;
        } else {
            return new ModelAndView("/error_404");
        }
    }

    @PostMapping("/update/{id}")
    public String updateProvince(@ModelAttribute("province") Province province, RedirectAttributes redirectAttributes) {
        provinceService.save(province);
        redirectAttributes.addFlashAttribute("message", "successful updating province");
        return "redirect:/provinces";
    }

    @GetMapping("/view-province/{id}")
    public ModelAndView viewProvince(@PathVariable Long id) {
        Optional<Province> provinceOptional = provinceService.findById(id);
        if(!provinceOptional.isPresent()){
            return new ModelAndView("/error_404");
        }

        Iterable<Customer> customers = customerService.findAllByProvince(provinceOptional.get());

        ModelAndView modelAndView = new ModelAndView("/customer/list");
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }
}

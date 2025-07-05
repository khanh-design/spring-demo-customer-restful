package com.example.springdemocustomerrestful.controller;

import com.example.springdemocustomerrestful.exception.DuplicateEmailException;
import com.example.springdemocustomerrestful.model.Customer;
import com.example.springdemocustomerrestful.model.Province;
import com.example.springdemocustomerrestful.service.ICustomerService;
import com.example.springdemocustomerrestful.service.IProvinceService;
import com.example.springdemocustomerrestful.service.impl.CustomerService;
import com.example.springdemocustomerrestful.service.impl.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IProvinceService provinceService;

    @ModelAttribute("provinces")
    public Iterable<Province> listProvinces() {
        return provinceService.findAll();
    }

//    @GetMapping("")
//    public String showlist(Model model) {
//        Iterable<Customer> customers = customerService.findAll();
//        model.addAttribute("customers", customers);
//        return "/customer/list";
//    }
    @GetMapping("")
    public ModelAndView showlist(@PageableDefault(size = 2) Pageable pageable) {
        Page<Customer> customers = customerService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("/customer/list");
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showcreate() {
        ModelAndView modelAndView = new ModelAndView("/customer/create");
        modelAndView.addObject("customer", new Customer());
        return modelAndView;
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("customer") Customer customer, RedirectAttributes redirectAttributes) {
//        try {
//            customerService.save(customer);
//            redirectAttributes.addFlashAttribute("message", "Customer created successfully");
//            return "redirect:/customers";
//        } catch (DuplicateEmailException e) {
//            return "redirect:/inputs-not-acceptable";
//        }
        customerService.save(customer);
        redirectAttributes.addFlashAttribute("message", "Customer created successfully");
        return "redirect:/customers";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showedit(@PathVariable Long id) {
        Optional<Customer> customer = customerService.findById(id);
        if (customer.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/customer/edit");
            modelAndView.addObject("customer", customer.get());
            return modelAndView;
        } else {
            return new ModelAndView("/error_404");
        }
    }

    @PostMapping("/edit/{id}")
    public String edit(@ModelAttribute("customer") Customer customer, RedirectAttributes redirectAttributes) throws DuplicateEmailException {
//        try {
//            customerService.save(customer);
//            redirectAttributes.addFlashAttribute("message", "Customer updated successfully");
//            return "redirect:/customers";
//        } catch (DuplicateEmailException e) {
//            return "redirect:/inputs-not-acceptable";
//        }
        customerService.save(customer);
        redirectAttributes.addFlashAttribute("message", "Customer updated successfully");
        return "redirect:/customers";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        customerService.remove(id);
        redirectAttributes.addFlashAttribute("message", "Customer deleted successfully");
        return "redirect:/customers";
    }

    @ExceptionHandler(DuplicateEmailException.class)
    public ModelAndView showInputNotAcceptable() {
        return new ModelAndView("/inputs-not-acceptable");
    }
}

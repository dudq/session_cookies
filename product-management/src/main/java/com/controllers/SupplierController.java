package com.controllers;

import com.models.Product;
import com.models.Supplier;
import com.services.ProductService;
import com.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SupplierController {
    @Autowired
    private SupplierService supplierService;

    @Autowired
    private ProductService productService;

    @GetMapping("/suppliers")
    public String showSupplierList(Model model) {
        model.addAttribute("suppliers", supplierService.findAll());
        return "/supplier/list";
    }

    @GetMapping("/create-supplier")
    public String showCreateSupplierForm(Model model) {
        model.addAttribute("supplier", new Supplier());
        return "/supplier/create";
    }

    @PostMapping("/create-supplier")
    public String saveNewSupplier(@ModelAttribute("supplier") Supplier supplier, Model model) {
        supplierService.save(supplier);
        model.addAttribute("supplier", new Supplier());
        model.addAttribute("message", "Added supplier successfully");
        return "/supplier/create";
    }

    @GetMapping("/edit-supplier/{id}")
    public String showEditSupplierForm(@PathVariable("id") Long id, Model model) {
        Supplier supplier = supplierService.findById(id);
        if (supplier != null) {
            model.addAttribute("supplier", supplier);
            return "/supplier/edit";
        } else {
            return "error.404";
        }
    }

    @PostMapping("/edit-supplier")
    public String updateSupplier(@ModelAttribute("supplier") Supplier supplier, Model model) {
        supplierService.save(supplier);
        model.addAttribute("supplier", supplier);
        model.addAttribute("message", "Updated supplier successfully");
        return "/supplier/edit";
    }

    @GetMapping("/delete-supplier/{id}")
    public String showDeleteSupplierForm(@PathVariable("id") Long id, Model model) {
        Supplier supplier = supplierService.findById(id);
        if (supplier != null) {
            model.addAttribute("supplier", supplier);
            return "/supplier/delete";
        } else {
            return "error.404";
        }
    }

    @PostMapping("/delete-supplier")
    public String deleteSupplier(@ModelAttribute("supplier") Supplier supplier) {
        supplierService.remove(supplier.getId());
        return "redirect:suppliers";
    }

    @GetMapping("supplier/{id}")
    public String viewSupplier(@PathVariable("id") Long id, Model model) {
        Supplier supplier = supplierService.findById(id);
        if (supplier != null) {
            Iterable<Product> products = productService.findAllBySupplier(supplier);
            model.addAttribute("products", products);
            model.addAttribute("supplier", supplier);
            return "/supplier/view";
        }
        return "error.404";
    }
}

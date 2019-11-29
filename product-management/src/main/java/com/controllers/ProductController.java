package com.controllers;

import com.models.Product;
import com.models.Supplier;
import com.services.ProductService;
import com.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
//@SessionAttributes("product")
public class ProductController {

//    @ModelAttribute("product")
//    public Product setUpProduct() {
//        return new Product();
//    }

    @Autowired
    private ProductService productService;

    @Autowired
    private SupplierService supplierService;

    @ModelAttribute("suppliers")
    public Iterable<Supplier> suppliers() {
        return supplierService.findAll();
    }

    @GetMapping("/find-products")
    public String showFoundBySupplier(@ModelAttribute("supplier") Long id, Model model) {
        Supplier supplier = supplierService.findById(id);
        Iterable<Product> products = productService.findAllBySupplier(supplier);
        model.addAttribute("products", products);
        model.addAttribute("supplier", supplier);
        return "/supplier/view";
    }

    @GetMapping("/sort-products")
    public String showSortProductList(Model model, @PageableDefault(size = 5, page = 0, sort = "price", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Product> products = productService.findAll(pageable);
        model.addAttribute("products", products);
        return "product/list";
    }

    @GetMapping("/products")
    public String showProductList(Model model,
                                  @PageableDefault(size = 5, page = 0, sort = "id", direction = Sort.Direction.ASC) Pageable pageable
    ) {

        Page<Product> products = productService.findAll(pageable);
        model.addAttribute("products", products);
        model.addAttribute("product", new Product());
        return "product/list";
    }

//    @GetMapping("/find-product")
//    public String showFindProductForm(Model model,)

    @GetMapping("/create-product")
    public String showCreateProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "product/create";
    }

    @PostMapping("/create-product")
    public String saveNewProduct(@ModelAttribute("product") Product product, Model model) {
        productService.save(product);

        model.addAttribute("product", new Product());
        model.addAttribute("message", "New product created successfully");
        return "product/create";
    }

    @GetMapping("/edit-product/{id}")
    public String showEditProductForm(@PathVariable("id") Long id, Model model) {
        Product product = productService.findById(id);

        if (product != null) {
            model.addAttribute("product", product);
            return "product/edit";
        } else {
            return "error.404";
        }
    }

    @PostMapping("/edit-product")
    public String updateProduct(@ModelAttribute("product") Product product, Model model) {
        productService.save(product);

        model.addAttribute("product", product);
        model.addAttribute("message", "Product updated successfully");
        return "product/edit";
    }

    @GetMapping("/delete-product/{id}")
    public String showDeleteProductForm(@PathVariable("id") Long id, Model model) {
        Product product = productService.findById(id);
        if (product != null) {
            model.addAttribute("product", product);
            return "product/delete";
        } else {
            return "error.404";
        }
    }

    @PostMapping("/delete-product")
    public String deleteProduct(@ModelAttribute("product") Product product) {
        productService.remove(product.getId());
        return "redirect:products";

    }

    @GetMapping("/product/{id}")
    public String viewProduct(@PathVariable("id") Long id, Model model) {
        Product product = productService.findById(id);
        if (product != null) {
            model.addAttribute("product", product);
            return "product/view";
        } else {
            return "error.404";
        }
    }

//    @PostMapping("addtocart")
//    public String addProductToCart(@ModelAttribute("product") Product product, Model model, HttpServletResponse response, String setProduct) {
//        setProduct = product.getName();
//        Cookie cookie = new Cookie(setProduct,setProduct);
//        cookie.setMaxAge(24*60*60);
//        response.addCookie(cookie);
//        model.addAttribute("message","Added to cart");
//        model.addAttribute("product",product);
//        return "product/view";
//    }

//    @PostMapping("/toCart")
//    public String showCartForm(HttpServletRequest request, Model model) {
//        Cookie[] cookies = request.getCookies();
//        List<Product> cartProduct = new ArrayList<>();
//        Iterable<Product> products = productService.findAll();
//        for (Product product: products){
//            for (Cookie c: cookies) {
//                if (product.getName().equals(c.getValue())) {
//                    cartProduct.add(product);
//                }
//            }
//        }
//        model.addAttribute("cartProduct",cartProduct);
//        return "cart/list";
//    }
}

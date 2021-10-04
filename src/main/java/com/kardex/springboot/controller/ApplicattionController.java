package com.kardex.springboot.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kardex.springboot.constant.EntityPurchaseStatus;
import com.kardex.springboot.constant.EntityStatus;
import com.kardex.springboot.form.ProductForm;
import com.kardex.springboot.form.ProductPurchaseForm;
import com.kardex.springboot.model.Employee;
import com.kardex.springboot.model.Product;
import com.kardex.springboot.model.ProductPurchase;
import com.kardex.springboot.model.Purchase;
import com.kardex.springboot.model.Role;
import com.kardex.springboot.service.IEmployeeService;
import com.kardex.springboot.service.IProductService;
import com.kardex.springboot.service.IPurchaseService;
import com.kardex.springboot.service.IRoleService;

@Controller
public class ApplicattionController {
	
	private static Logger LOG =  LoggerFactory.getLogger(ApplicattionController.class);

	@Autowired
	private IEmployeeService employeeService;
	
	@Autowired
	private IRoleService roleServices;
	
	@Autowired
	private IProductService productServices;
	
	@Autowired
	private IPurchaseService purchaseService;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	/**
	 * Acceso a la home
	 * @return
	 */
	@GetMapping("/")
	public String home1() {
		return "/home";
	}

	/**
	 * Acceso a la home
	 * @return
	 */
	@GetMapping("/home")
	public String home() {
		return "/home";
	}

	/**
	 * Acceso a la pagina del administrador
	 * @return
	 */
	@GetMapping("/admin")
	public String admin(Model model) {
		return "/admin";
	}

	/**
	 * Acceso al login
	 * @return
	 */
	@GetMapping("/login")
	public String login() {
		return "/login";
	}

	/**
	 * devuelve la lista de empleados 
	 * @param model
	 * @return
	 */
	@GetMapping("/getAllEmployees")
	public String getAllEmployees(Model model) {

		List<Employee> employees = employeeService.findByStatus(EntityStatus.ACTIVE);
		model.addAttribute("employees", employees);
		return "/employeeList";
		
	}
	
	/**
	 * devuelve la lista de productos 
	 * @param model
	 * @return
	 */
	@GetMapping("/getAllProducts")
	public String getAllProducts(Model model) {

		List<Product> products = productServices.findByStatus(EntityStatus.ACTIVE);
		model.addAttribute("products", products);
		return "/productsList";
		
	}
	
	/**
	 * Elimina un empleado 
	 * @param idEmployee
	 * @param model
	 * @return
	 */
	@GetMapping("/deleteEmployee")
	public String deleteEmployee(@RequestParam(name = "idEmployee",required = true) Long idEmployee, Model model) {
		
		employeeService.deleteByid(idEmployee);
		
		List<Employee> employees = employeeService.findByStatus(EntityStatus.ACTIVE);
		model.addAttribute("employees", employees);
		return "/employeeList";
		
	}
	
	/**
	 * Elimina un producto
	 * @param idProduct
	 * @param model
	 * @return
	 */
	@GetMapping("/deleteProduct")
	public String deleteProducts(@RequestParam(name = "idProduct",required = true) Long idProduct, Model model) {
		
		productServices.deleteById(idProduct);
		
		List<Product> products = productServices.findByStatus(EntityStatus.ACTIVE);
		model.addAttribute("products", products);
		return "/productsList";
		
	}
	
	/**
	 * Muestra el formulario de alta de un empleado
	 * @param employee
	 * @return
	 */
	@GetMapping("/showFormSaveEmployee")
	public String showFormSaveEmployee(Employee employee) {
		return "/saveEmployee";
	}
	
	/**
	 * Persiste un empleado
	 * @param employee
	 * @param bindingResult
	 * @param model
	 * @return
	 */
	@PostMapping("/saveEmployee")
	public String saveEmployee(@Valid Employee employee, BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			return "saveEmployee";
		}
		
        Employee employeeExists = employeeService.findByUserName(employee.getUserName());
        if (employeeExists != null) {
            bindingResult
                    .rejectValue("userName", "error.employee",
                            "El usuario ya existe debe ingresar otro");
            return "saveEmployee";
        }
		
		
		Role role = roleServices.findByName("ADMIN");
		List<Employee> employees = role.getEmployees();
		
		if (employees == null) {
			employees = new ArrayList<Employee>();
		}
			
		employee.setPassword(encoder.encode(employee.getPassword()));
		employee.setStatus(EntityStatus.ACTIVE);
		employees.add(employee);
		roleServices.save(role);
		
		List<Employee> employeesView = employeeService.findByStatus(EntityStatus.ACTIVE);
		model.addAttribute("employees", employeesView);
		
		return "/employeeList";
	}
	
	/**
	 * Muestra el formulario de alta de un producto
	 * @param model
	 * @return
	 */
	@GetMapping("/showFormSaveProduct")
	public String showFormSaveProduct(Model model) {
		ProductForm productForm = new ProductForm();
		model.addAttribute("productForm", productForm);
		return "/saveProduct";
	}
	
	/**
	 * Persiste un producto  
	 * @param productForm
	 * @param bindingResult
	 * @param model
	 * @return
	 */
	@PostMapping("/saveProduct")
	public String saveProduct(@Valid ProductForm productForm, BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			return "saveProduct";
		}
		
		Product product = new Product();
		product.setName(productForm.getName());
		product.setDescription(productForm.getDescription());
		product.setPrice(productForm.getPrice());
		product.setStatus(EntityStatus.ACTIVE);
		product.setStock(productForm.getStock());
		
		productServices.save(product);

		List<Product> productsView = productServices.findByStatus(EntityStatus.ACTIVE);
		model.addAttribute("products", productsView);
		
		return "/productsList";
	}
	
	
	/**
	 * Muestra la pantala de compra
	 * @param purchase
	 * @param productPurchaseForm
	 * @param model
	 * @param request
	 * @return
	 */
	@GetMapping("/showFormSavePurchase")
	public String showFormSavePurchase(Purchase purchase, ProductPurchaseForm productPurchaseForm, Model model, HttpServletRequest request) {

		List<Product> products = productServices.findByStatus(EntityStatus.ACTIVE);
		model.addAttribute("products", products);
		
		Employee employee = employeeService.getEmployeeSession();
		
		purchase.setEmployee(employee);
		purchase.setProductPurchases(new ArrayList<ProductPurchase>());
		purchase.setTotal(0.00);
		purchase.setStatusPurchase(EntityPurchaseStatus.IN_PROGRESS);
		purchase.setStatus(EntityStatus.ACTIVE);
		model.addAttribute("purchase", purchase);
		
		HttpSession mySession= request.getSession();
		mySession.setAttribute("purchase", purchase);
		
		return "/savePurchase";
	}
	
	/**
	 * Agrega un producto a la compra, revisando el stock del mismo  
	 * @param productPurchaseForm
	 * @param bindingResult
	 * @param model
	 * @param request
	 * @return
	 */
	
	@PostMapping("/addProductPurchase")
	public String addProductPurchase(@Valid ProductPurchaseForm productPurchaseForm, BindingResult bindingResult, Model model, HttpServletRequest request) {

		//Recupero los productos activos
		List<Product> products = productServices.findByStatus(EntityStatus.ACTIVE);
		
		HttpSession mySession= (HttpSession) request.getSession();
		Purchase purchase = (Purchase)mySession.getAttribute("purchase");
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("products", products);
			model.addAttribute("productPurchases", purchase.getProductPurchases());
			return "/savePurchase";
		}
		
		
		/*Verifico si el producto ya no existe en la lista*/
		boolean existProductInPurchase = false;
		Integer quantityTotal = productPurchaseForm.getQuantity();
		
		Product product = productServices.findById(productPurchaseForm.getId());
		int stock = product.getStock().intValue();

		Iterator<ProductPurchase> it = purchase.getProductPurchases().iterator();
		while (it.hasNext() && !existProductInPurchase) {
			
			ProductPurchase productPurchase = it.next();
			if(productPurchase.getProduct().getId()  == productPurchaseForm.getId()) {
				quantityTotal += productPurchase.getQuantity();
				Double subtotal = productPurchase.getSubTotal() + (product.getPrice()*productPurchaseForm.getQuantity());
				
				if ((stock - quantityTotal) >= 0) {				
					productPurchase.setQuantity(quantityTotal);
					productPurchase.setSubTotal(subtotal);
				}	
				existProductInPurchase = true;
				
			}
		}
		
        if ((stock - quantityTotal) < 0) {
            bindingResult
                    .rejectValue("quantity", "error.quantity",
                            "El producto no tiene stock. El stock es " + product.getStock().intValue());
            
			model.addAttribute("products", products);
			model.addAttribute("productPurchases", purchase.getProductPurchases());
            return "savePurchase";
        }
        
		if (!existProductInPurchase) {
	        ProductPurchase productPurchase = new ProductPurchase();
			productPurchase.setName(product.getName());
			productPurchase.setQuantity(productPurchaseForm.getQuantity());
			productPurchase.setProduct(product);
			productPurchase.setPurchase(purchase);
			productPurchase.setSubTotal(product.getPrice()*productPurchaseForm.getQuantity());
			
			purchase.getProductPurchases().add(productPurchase);
		}
		
		model.addAttribute("products", products);
		model.addAttribute("productPurchases", purchase.getProductPurchases());

		mySession.setAttribute("purchase", purchase);

		return "/savePurchase";
	}
	
	
	/**
	 * Elimina un producto de la compra
	 * @param idProduct
	 * @param model
	 * @param request
	 * @param productPurchaseForm
	 * @return
	 */
	@GetMapping("/deleteProductPurchase")
	public String deleteProductPurchase(@RequestParam(name = "idProduct",required = true) Long idProduct, Model model, HttpServletRequest request, ProductPurchaseForm productPurchaseForm) {
		
		HttpSession mySession= (HttpSession) request.getSession();
		Purchase purchase = (Purchase)mySession.getAttribute("purchase");
		
		List<ProductPurchase> productPurchasesNews = new ArrayList<ProductPurchase>();
		
		Iterator<ProductPurchase> it = purchase.getProductPurchases().iterator();
		while (it.hasNext()) {
			ProductPurchase productPurchase = it.next();
			if(productPurchase.getProduct().getId() != idProduct) {
				productPurchasesNews.add(productPurchase);
			}
		}
		
		purchase.setProductPurchases(productPurchasesNews);
		
		//Recupero los productos activos
		List<Product> products = productServices.findByStatus(EntityStatus.ACTIVE);

		model.addAttribute("products", products);
		model.addAttribute("productPurchases", purchase.getProductPurchases());
		mySession.setAttribute("purchase", purchase);
		
		return "/savePurchase";
	}	
	
	
	/**
	 * Persiste una compra con sus productos 
	 * @param request
	 * @param model
	 * @return
	 */
	@GetMapping("/savePurchase")
	public String savePurchase(HttpServletRequest request, Model model) {
		
		HttpSession mySession= (HttpSession) request.getSession();
		Purchase purchase = (Purchase)mySession.getAttribute("purchase");
		
		boolean hasProductInPurchase = true;
		if(purchase.getProductPurchases().size() == 0)
			hasProductInPurchase=false;
		
		
		boolean hasStock = true;
		Iterator<ProductPurchase> iterator = purchase.getProductPurchases().iterator();
		while (iterator.hasNext() && hasStock) {
			ProductPurchase productPurchase = iterator.next();
			Product product = productServices.findById(productPurchase.getProduct().getId());
			int stock = product.getStock()-productPurchase.getQuantity();
			if(stock<0) {
				hasStock = false;
			}
		}
		

		if (hasProductInPurchase && hasStock) {
			double total = 0;
			Iterator<ProductPurchase> it = purchase.getProductPurchases().iterator();
			while (it.hasNext()) {

				ProductPurchase productPurchase = it.next();
				Product product = productPurchase.getProduct();
				product.setStock(product.getStock() - productPurchase.getQuantity());

				productServices.save(product);

				total += productPurchase.getSubTotal();
			}

			/* registro la compra terminada */
			purchase.setStatusPurchase(EntityPurchaseStatus.CONFIRMED);
			purchase.setTotal(total);

			purchaseService.save(purchase);
			model.addAttribute("message", "Felicitaciones su compra fue exitosa con un importe de " + purchase.getTotal());
		}
		else if(!hasStock) {
			model.addAttribute("message", "Alguno de sus productos seleccionados ya no tiene stock");
		}
		else {
			model.addAttribute("message", "Su compra no fue registrada porque NO agrego productos");
		}

		return "/result";
	}	

}

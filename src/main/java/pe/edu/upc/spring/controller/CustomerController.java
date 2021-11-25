package pe.edu.upc.spring.controller;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.text.ParseException;
import pe.edu.upc.spring.model.Customer;
import pe.edu.upc.spring.service.ICustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private ICustomerService cService;
	private Customer idCus;
	private String CusId;
	private List<Customer> listCus;
	@RequestMapping("/bienvenido")
	public String irPaginaBienvenida() {
		return "bienvenido";
	}
	@RequestMapping("/cliente")
	public String irPaginaBienvenida2() {
		return "cliente/cliente2";
	}
         @RequestMapping("/bienvenido1")
	public String irPaginaBienvenida1() {
			return "seleccionar";
	}

	@RequestMapping("/")
	public String irPaginaListadoCustomers(Map<String, Object> model) {
		model.put("listaCustomers", cService.listar());
		return "cliente/listCliente";
	}

	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		model.addAttribute("customer", new Customer());
		return "cliente/cliente";
	}

	@RequestMapping("/registrar")
	public String registrar(@Valid @ModelAttribute Customer objCustomer, BindingResult binRes, Model model) throws ParseException {
		if (binRes.hasErrors())
			return "cliente/cliente";
		else {
			boolean flag = cService.insertar(objCustomer);
			if (flag)
				return "redirect:/customer/listar";
			else {
				model.addAttribute("mensaje", "Ocurrió un error");
				return "redirect:/customer/irRegistrar";
			}
		}
	}

	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) throws ParseException {
		Optional<Customer> objCustomer = cService.listarId(id);
		if (objCustomer == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrió un error");
			return "redirect:/customer/listar";
		} else {
			model.addAttribute("customer", objCustomer);
			return "cliente/cliente";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id>0) {
				cService.eliminar(id);
				model.put("listaCustomers", cService.listar());
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "Ocurrio un error");
			model.put("listaCustomers", cService.listar());
		}
		return "cliente/listCliente";
		}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaCustomers", cService.listar());
		return "cliente/listCLiente";
	}
	
	
	@RequestMapping("/reporte")
	public String listarCust(Model model) {
		CusId=String.valueOf(idCus.getIdCustomer());
		listCus=cService.buscarPlanner(CusId);
		model.addAttribute("listarPlanner", idCus);
		model.addAttribute("ListCustByPla", listCus);
		return "consulta/consCliente";
	}
	
}
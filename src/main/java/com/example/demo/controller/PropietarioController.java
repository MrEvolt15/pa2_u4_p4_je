package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repository.modelo.Propietario;
import com.example.demo.service.IPropietarioService;

@RestController
@RequestMapping("/propietarios")
public class PropietarioController {
	
	@Autowired
	private IPropietarioService iPropietarioService;
	//http://localhost:8080/concesionario/propietarios/buscar
	//debe redireccionar una vista, el nombre de la vista(String)
	@GetMapping("/buscar")
	public String buscarTodos(Model modelo) {
		List<Propietario> lista = this.iPropietarioService.buscarTodos();
		modelo.addAttribute("propietarios",lista);
		return "vistaListaPropietarios";
	}
	
	//http://localhost:8080/concesionario/propietarios/buscarPorId/?   ?=unaID
	@GetMapping("/buscarPorId/{idPropietario}")
	//pathvariable: un dato que se envia 
	public String buscarPorId(@PathVariable("idPropietario") Integer id, Model modelo) {
		Propietario prop = this.iPropietarioService.buscarPorId(id);
		modelo.addAttribute("propietario",prop);
		return "vistaPropietario";
	}
	@PutMapping("/actualizar/{idPropietario}")
	public String actualizarPorpietario(@PathVariable("idPropietario") Integer id,Propietario propietario) {
		this.iPropietarioService.actualizar(propietario);
		return "redirect:/propietarios/buscar";
		
	}
	//http://localhost:8080/concesionario/propietarios/borrar/?      ?=una ID
	@DeleteMapping("/borrar/{idPropietario}")
	public String borrarPorID(@PathVariable("idPropietario") Integer id){
		this.iPropietarioService.borrar(id);
		return "redirect:/propietarios/buscar";
	}

	@PostMapping("/guardar")
	public String insertarPropietario(Propietario propietario){
		this.iPropietarioService.guardar(propietario);
		return"redirect:/propietarios/buscar";
	}
	//metodo de pagina de redireccionamiento
	@GetMapping("/nuevo")
	public String paginaNuevoPropietario(Propietario propietario){
		return "vistaNuevoPropietario";
	}
}

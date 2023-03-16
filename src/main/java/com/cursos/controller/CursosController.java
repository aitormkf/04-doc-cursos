package com.cursos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cursos.model.Curso;
import com.cursos.service.CursosService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags="curso")
@CrossOrigin("*")
@RestController
@RequestMapping("curso")
public class CursosController {
	
	@Autowired
	CursosService service;
	
	
	@ApiOperation(value="Devuelve la lista de todos las cursos")
	@GetMapping(value="/lista",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Curso> ListaCursos() {
		
		return service.cursos();
	}
	
	@ApiOperation(value="Alta de un curso")
	@PostMapping(value="/alta",produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	public List<Curso> AltaCurso(@ApiParam(value="JSON con los datos del curso")@RequestBody Curso curso) {
		
		return service.altaCurso(curso);
	}
	
	@ApiOperation(value="Actualización de las horas  de un curso")
	@PutMapping(value="/actualizar/{codCurso}/{horas}",consumes=MediaType.APPLICATION_JSON_VALUE)
	public void ActualizarCurso(@ApiParam(value="Codigo del curso a actualizar")@PathVariable("codCurso") String codCurso,@ApiParam(value="Horas del curso")@PathVariable("horas") int horas) {
		
		 service.actualizarCurso(codCurso,horas);
	}
	
	@ApiOperation(value="Busca un curso por su codigo")
	@GetMapping(value="/buscar/{codCurso}",produces=MediaType.APPLICATION_JSON_VALUE)
	public Curso BuscarCurso(@ApiParam(value="Codigo del curso a buscar")@PathVariable("codCurso") String codCurso) {
		
		return service.buscarCurso(codCurso);
	}
	
	@ApiOperation(value="Devuelve los cursos que esten en un rango de precios")
	@GetMapping(value="rango/{precioMin}/{precioMax}",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Curso> CursoRango(@ApiParam(value="Precio minimo")@PathVariable("precioMin") String precioMin,@ApiParam(value="Precio maximo")@PathVariable("precioMax") String precioMax) {
		
		return service.BuscarCursosRango(Double.parseDouble(precioMin),Double.parseDouble(precioMax));
	}
	
	@ApiOperation(value="Elimina un curso a partir de su codigo")
	@DeleteMapping(value="/eliminar/{codCurso}",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Curso> EliminarCurso(@ApiParam(value="Codigo del curso a eliminar")@PathVariable("codCurso") String codCurso) {
		
		return service.eliminarCurso(codCurso);
	}
	
}

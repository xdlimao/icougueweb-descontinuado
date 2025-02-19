package com.klb.icougue.http.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.multipart.MultipartFile;

import com.klb.icougue.entity.Foto;
import com.klb.icougue.service.FotoService;

// https://www.youtube.com/watch?v=HHXn-nT3g10  Top 10 videos da internet.
// https://www.youtube.com/watch?v=sYSye8UiXNw

@RestController
@RequestMapping("/simg")
public class FotoController {
		
        private static String campast = "C:\\Users\\Daniel\\Desktop\\Desktop\\GitHub\\icougueweb\\iCougue\\src\\main\\resources\\static\\images\\";
        private static int xd = 0;
        
        
		@Autowired
		private FotoService fotoService;
		
		@Autowired
		private ModelMapper modelMapper;
		
		@PostMapping
		public Foto salvar(Foto foto) {
			return fotoService.salvar(foto);
		}
		
	/* @PostMapping
		public void uploadfoto (@RequestParam("file") MultipartFile arquivo, @RequestParam("id") String id) {
		    try {
		        if(!arquivo.isEmpty()) {
		            xd = xd + 1;
		            byte[] bytes = arquivo.getBytes();
		            Path caminho = Paths.get(campast+xd+arquivo.getOriginalFilename());
		            Files.write(caminho, bytes);
		        }
		    }catch(IOException e){
		       e.printStackTrace();
		    }
		}
	*/
		
		@GetMapping("/image")
		public ModelAndView simgt() {
			ModelAndView mv = new ModelAndView();
			mv.setViewName("imagem.html");
			return mv;
		}
		
		@GetMapping
		@ResponseStatus(HttpStatus.OK)
		public List<Foto> listaFoto(){
			return fotoService.listaFoto();
		}
		
		@GetMapping("/{id}")
		@ResponseStatus(HttpStatus.OK)
		public Foto buscarFotoPorId(@PathVariable("id") Long id) {
			return fotoService.buscarPorId(id)
					.orElseThrow();
		}
		
		@DeleteMapping("/del/{id}")
		@ResponseStatus(HttpStatus.NO_CONTENT)
		public void removerFoto(@PathVariable("id") Long id) {
			fotoService.buscarPorId(id)
					.map(foto -> {
						fotoService.removerPorId(foto.getId());
						return Void.TYPE;
					}).orElseThrow();
		}
		
		@PutMapping("/put/{id}")
		@ResponseStatus(HttpStatus.NO_CONTENT)
		public void atualizarFoto(@PathVariable("id") Long id, @RequestBody Foto foto){
			fotoService.buscarPorId(id)
			.map(fotoBase -> {
				modelMapper.map(foto, fotoBase);
				fotoService.salvar(fotoBase);
				return Void.TYPE;
			}).orElseThrow();
		}
}

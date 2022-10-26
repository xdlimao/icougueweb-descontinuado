package com.klb.icougue.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klb.icougue.repository.*;
import com.klb.icougue.entity.*;

@Service
public class FotoService {
	
	@Autowired
	private FotoRepository fotoRepository;
	
		public Foto salvar(Foto foto) {
		    return fotoRepository.save(foto);
		}
	
		public List<Foto> listaFoto(){
			return fotoRepository.findAll();
		}

		public Optional<Foto> buscarPorId(Long id){
			return fotoRepository.findById(id);
		}
	
		public void removerPorId(Long id) {
			fotoRepository.deleteById(id);
		}

		
}

package com.felipemotagodoy.Projeto_Web_Services_Com_Spring_Boot_e_JPA_Hibernate.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.felipemotagodoy.Projeto_Web_Services_Com_Spring_Boot_e_JPA_Hibernate.entities.Document;
import com.felipemotagodoy.Projeto_Web_Services_Com_Spring_Boot_e_JPA_Hibernate.entities.User;
import com.felipemotagodoy.Projeto_Web_Services_Com_Spring_Boot_e_JPA_Hibernate.repositories.DocumentRepository;
import com.felipemotagodoy.Projeto_Web_Services_Com_Spring_Boot_e_JPA_Hibernate.repositories.UserRepository;
import com.felipemotagodoy.Projeto_Web_Services_Com_Spring_Boot_e_JPA_Hibernate.services.dto.DocumentDTO;
import com.felipemotagodoy.Projeto_Web_Services_Com_Spring_Boot_e_JPA_Hibernate.services.exceptions.DatabaseException;
import com.felipemotagodoy.Projeto_Web_Services_Com_Spring_Boot_e_JPA_Hibernate.services.exceptions.ResourceNotFoundException;

@Service
public class DocumentService {
	
	@Autowired
	private UserService userService;

	@Autowired
	private DocumentRepository documentRepository;

	public List<Document> findAll() {
		return documentRepository.findAll();
	}

	public Document findById(Long id) {
		Optional<Document> obj = documentRepository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public Document insert(DocumentDTO documentDTO) {
	     Document document = new Document();
	     document.setTipoDocumento(documentDTO.getTipoDocumento());
	     document.setDescricao(documentDTO.getDescricao());
	     document.setDataInclusao(LocalDate.parse(documentDTO.getDataInclusao()));
	     document.setDataAtualizacao(LocalDate.now()); 
	        		
		User client = userService.findById(documentDTO.getClientId());
		document.setClient(client);
		documentRepository.save(document);
		return document;
	}

	public void delete(Long id) {
		try {
			documentRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	public Document update(Long id, Document obj) {
		try {
			Document entity = documentRepository.getOne(id);
			updateData(entity, obj);
			return documentRepository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(Document entity, Document obj) {
		entity.setTipoDocumento(obj.getTipoDocumento());
		entity.setDescricao(obj.getDescricao());
	}
}

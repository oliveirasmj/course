package com.example.course.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.course.entities.User;
import com.example.course.repositories.UserRepository;
import com.example.course.services.exceptions.DatabaseException;
import com.example.course.services.exceptions.ResourceNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public List<User> findAll() {
		return repository.findAll(); // findAll ja existe porque UserRepository extende o JpaRepository
	}
	
	public User findById(Long id) {
		Optional<User> obj = repository.findById(id); // findById ja existe porque UserRepository extende o JpaRepository
		//return obj.get(); //forma 1
		return obj.orElseThrow(() -> new ResourceNotFoundException(id)); //forma 2 - lanca a excepcao caso o id nao exista
	}
	
//	public User findByNome(String nome) {
//		Optional<User> obj = Optional.ofNullable(repository.findByNome(nome)); // findById ja existe porque UserRepository extende o JpaRepository
//		return obj.orElseThrow(() -> new ResourceNotFoundException(nome)); //forma 2 - lanca a excepcao caso o id nao exista
//	}
	
	public User findByNif(int nif) {
		Optional<User> obj = Optional.ofNullable(repository.findByNif(nif)); // findById ja existe porque UserRepository extende o JpaRepository
		return obj.orElseThrow(() -> new ResourceNotFoundException(nif)); //forma 2 - lanca a excepcao caso o id nao exista
	}
	
	public User insert(User obj) {
		return repository.save(obj);
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id); //lancar excepcao caso se tente eliminar um id que nao existe
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage()); //lancao excepcao caso se tete eliminar um user com algo associado
		}
	}
	
	public User update(Long id, User obj) {
		try {
			User entity = repository.getOne(id); //getOne - nao vai à BD buscar apenas o prepara
			updateData(entity, obj);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id)
;		}
	}

	private void updateData(User entity, User obj) {
		entity.setMorada(obj.getMorada());
		entity.setNif(obj.getNif());
		entity.setNome(obj.getNome());
		entity.setTelefone(obj.getTelefone());
	}
}

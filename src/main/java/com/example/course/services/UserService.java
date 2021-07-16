package com.example.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.course.entities.User;
import com.example.course.repositories.UserRepository;
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
	
	public User insert(User obj) {
		return repository.save(obj);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	public User update(Long id, User obj) {
		User entity = repository.getOne(id); //getOne - nao vai à BD buscar apenas o prepara
		updateData(entity, obj);
		return repository.save(entity);
	}

	private void updateData(User entity, User obj) {
		entity.setMorada(obj.getMorada());
		entity.setNif(obj.getNif());
		entity.setNome(obj.getNome());
		entity.setTelefone(obj.getTelefone());
	}
}

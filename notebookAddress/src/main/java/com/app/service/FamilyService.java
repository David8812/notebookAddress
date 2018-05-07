package com.app.service;

import java.util.List;

import com.app.model.Family;

public interface FamilyService {
	
	public List<Family> findAll();
	
	public List<Family> findByNombre(String nombre);
	
	public Family save(Family family);
	
	public void delete(Family family);
	
	public Family findById(Long id);
	
	public void deleteById(Long id);
}

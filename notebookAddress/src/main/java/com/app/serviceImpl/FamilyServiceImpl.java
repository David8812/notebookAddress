package com.app.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.data.FamilyRepository;
import com.app.model.Family;
import com.app.service.FamilyService;

@Component
public class FamilyServiceImpl implements FamilyService {
	
	@Autowired
	FamilyRepository familyRepository;

	@Override
	public List<Family> findAll() {
		return familyRepository.findAll();
	}

	@Override
	public List<Family> findByNombre(String nombre) {
		return familyRepository.findByNombre(nombre);
	}

	@Override
	public Family save(Family family) {
		return familyRepository.save(family);
	}

	@Override
	public void delete(Family family) {
		familyRepository.delete(family);
	}

	@Override
	public Family findById(Long id) {
		return familyRepository.findById(id).get();
	}

	@Override
	public void deleteById(Long id) {
		familyRepository.deleteById(id);		
	}

}

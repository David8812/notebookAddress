package com.app.data;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.model.Family;

public interface FamilyRepository extends JpaRepository<Family, Long> {
	
	@Query(value = "select *, 1 as clazz_ from family left join partner on family.id = partner.id where partner.id is NULL and family.name = :nombre", nativeQuery=true)
	List<Family> findByNombre(@Param("nombre") String nombre);
	
	@Query(value = "select *, 1 as clazz_ from family left join partner on family.id = partner.id where partner.id is NULL", nativeQuery=true)
	List<Family> findAll();
	
	@Query(value = "select *, 1 as clazz_ from family left join partner on family.id = partner.id where partner.id is NULL and family.id = :id", nativeQuery=true)
	Optional<Family> findById(@Param("id") Long id);
}

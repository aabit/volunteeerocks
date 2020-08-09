/**
 * Volunteer repository, extends CrudRepository
 */
package com.example.demo_addFunc.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo_addFunc.model.VolunteerEntity;

/**
 * VolunteerRepository, extends CrudRepository
 */
@Repository
public interface VolunteerRepository
    extends CrudRepository<VolunteerEntity, Long> {
    
}
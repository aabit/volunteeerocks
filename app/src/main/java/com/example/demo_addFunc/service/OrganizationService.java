/**
 * Implements the Organization service functions
 */
package com.example.demo_addFunc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo_addFunc.exception.RecordNotFoundException;
import com.example.demo_addFunc.model.OrganizationEntity;
import com.example.demo_addFunc.repository.OrganizationRepository;

@Service
public class OrganizationService {
    
	
	@Autowired
	OrganizationRepository repository;
	
	/**
	 * Generate a list of organizations
	 * @return List of OrganizationEntity objects or new ArrayList of OrganizationEntity if list is empty
	 */
	public List<OrganizationEntity> getAllOrganizations()
	{
		List<OrganizationEntity> result = (List<OrganizationEntity>) repository.findAll();
		
		if(result.size() > 0) {
			return result;
		} else {
			return new ArrayList<OrganizationEntity>();
		}
	}
	
	/**
	 * Retrieve an organization using the id for the index/key
	 * @param id - unique id of organization to retrieve
	 * @return organization object matching the id parameter
	 * @throws RecordNotFoundException
	 */
	public OrganizationEntity getOrganizationById(Long id) throws RecordNotFoundException 
	{
		Optional<OrganizationEntity> organization = repository.findById(id);
		
		if(organization.isPresent()) {
			return organization.get();
		} else {
			throw new RecordNotFoundException("No organization record exist for given id");
		}
	}
	
	/**
	 * Create a new or update an existing organization
	 * @param entity - organization to update, if empty then create a new organization
	 * @return organization entity object just edited or created
	 */
	public OrganizationEntity createOrUpdateOrganization(OrganizationEntity entity) 
	{
		if(entity.getId()  == null) 
		{
			entity = repository.save(entity);
			
			return entity;
		} 
		else 
		{
			Optional<OrganizationEntity> organization = repository.findById(entity.getId());
			
			if(organization.isPresent()) 
			{
				OrganizationEntity newEntity = organization.get();
                newEntity.setTitle(entity.getTitle());
                newEntity.setDescription(entity.getDescription());
				newEntity.setLocation(entity.getLocation());
				newEntity.setVolunteers(entity.getVolunteers());

				newEntity = repository.save(newEntity);
				
				return newEntity;
			} else {
				entity = repository.save(entity);
				
				return entity;
			}
		}
	} 
	
	/**
	 * Delete an organization 
	 * @param id - unique id of the organization to delete
	 * @throws RecordNotFoundException
	 */
	public void deleteOrganizationById(Long id) throws RecordNotFoundException 
	{
		Optional<OrganizationEntity> organization = repository.findById(id);
		
		if(organization.isPresent()) 
		{
			repository.deleteById(id);
		} else {
			throw new RecordNotFoundException("No organization record exist for given id");
		}
	} 
}
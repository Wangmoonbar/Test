package com.animal.model;

import java.util.List;

public interface AnimalDAO_interface {
	
	public void insert(AnimalVO animalVO);
	public void update(AnimalVO animalVO);
	public void delete(Integer id);
	public AnimalVO findByPrimaryKey(Integer id);
	public List<AnimalVO> getAll();

}

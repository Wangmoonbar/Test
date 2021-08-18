package com.animal.model;

import java.util.List;

public class AnimalService {
	
	private AnimalDAO_interface dao;

	public AnimalService() {
		dao = new AnimalDAO();
	}
	
	public AnimalVO addAnimal(String name, Integer age, Double weight, byte[] picture, Integer areano) {

		AnimalVO animalVO = new AnimalVO();

		animalVO.setName(name);
		animalVO.setAge(age);
		animalVO.setWeight(weight);
		animalVO.setPicture(picture);
		animalVO.setAreano(areano);
			
		dao.insert(animalVO);

		return animalVO;
	}
	
	
	public AnimalVO updateAnimal(Integer id, String name, Integer age, Double weight, byte[] picture, Integer areano) {

		AnimalVO animalVO = new AnimalVO();

		animalVO.setId(id);
		animalVO.setName(name);
		animalVO.setAge(age);
		animalVO.setWeight(weight);
		animalVO.setPicture(picture);
		animalVO.setAreano(areano);
		
		dao.update(animalVO);

		return animalVO;
	}

	public void deleteAnimal(Integer id) {
		dao.delete(id);
	}

	public AnimalVO getOneAnimal(Integer id) {
		return dao.findByPrimaryKey(id);
	}

	public List<AnimalVO> getAll() {
		return dao.getAll();
	}
	

}

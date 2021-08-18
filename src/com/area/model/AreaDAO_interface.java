package com.area.model;

import java.util.List;

public interface AreaDAO_interface {
	
	public void insert(AreaVO areaVO);
	public void update(AreaVO areaVO);
	public void delete(Integer areano);
	public AreaVO findByPrimaryKey(Integer areano);
	public List<AreaVO> getAll();

}

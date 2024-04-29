package com.global.book.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.global.book.entity.Auther;
import com.global.book.repository.AutherRepo;

@Service
public class AutherService {
	
	@Autowired
	 private AutherRepo autherRepo;
	
	public Auther findById(long id) {
			return autherRepo.findById(id).orElseThrow();
	}
	
	public List<Auther> findAll() {
		return autherRepo.findAll();
	}
	
	public Auther insert (Auther auther) {
		
//		  if( auther.getId() != null ) {
//			  throw new RuntimeException();
//			}
		  
		 	return autherRepo.save(auther);
	}
	
	public Auther update (Auther auther) {
		Auther auther2 = findById(auther.getId());
		
		auther2.setName(auther.getName());
		
		return autherRepo.save(auther2);
	}
	
	public void deleteById(long id) {
		 autherRepo.deleteById(id);
	}
	
	
} 




























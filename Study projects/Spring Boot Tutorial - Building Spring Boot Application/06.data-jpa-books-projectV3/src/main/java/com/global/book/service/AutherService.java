package com.global.book.service;
import java.util.List;

import com.global.book.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.global.book.entity.Auther;
import com.global.book.repository.AutherRepo;

@Service
public class AutherService extends BaseService<Auther, Long> {

	public Auther update(Auther auther){

		Auther auther2 = findById(auther.getId());

		auther2.setName(auther.getName());

		return super.update(auther2);
	}

}






























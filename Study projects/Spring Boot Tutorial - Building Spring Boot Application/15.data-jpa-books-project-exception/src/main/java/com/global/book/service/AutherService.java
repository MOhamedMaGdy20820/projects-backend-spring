package com.global.book.service;
import java.util.List;
import java.util.Optional;

import com.global.book.base.BaseService;
import com.global.book.entity.AutherSearch;
import com.global.book.error.DaplicateRecoredException;
import com.global.book.repository.AutherSpec;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.global.book.entity.Auther;
import com.global.book.repository.AutherRepo;

@Service
public class AutherService extends BaseService<Auther, Long>  {

	@Autowired
	private  AutherRepo autherRepo;

	@Override
	public Auther insert(Auther entity) {

		if(!entity.getEmail().isEmpty() && entity.getEmail() != null){
			Optional<Auther> auther = autherRepo.findByEmail(entity.getEmail());
			if(auther.isPresent()){
				throw new DaplicateRecoredException("Email already exists");
			}
		}
		return super.insert(entity);
	}

	public Auther update(Auther auther){

		Auther auther2 = findById(auther.getId());

		auther2.setName(auther.getName());
		auther2.setIpAddress(auther.getIpAddress());
		auther2.setEmail(auther.getEmail());

		return super.update(auther2);
	}

	public List<Auther> findByAutherSpec(AutherSearch autherSearch) {

		AutherSpec spec = new AutherSpec(autherSearch);

		//  spec == condition

		return autherRepo.findAll(spec);

	}

	public Optional<Auther> findByEmail(String email) {
		return autherRepo.findByEmail(email);
	}

}





























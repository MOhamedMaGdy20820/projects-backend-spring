package com.global.book.service;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import com.global.book.base.BaseService;
import com.global.book.config.MessageUtils;
import com.global.book.entity.AutherSearch;
import com.global.book.error.DaplicateRecoredException;
import com.global.book.repository.AutherSpec;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.global.book.entity.Auther;
import com.global.book.repository.AutherRepo;

@Service
public class AutherService extends BaseService<Auther, Long>  {

	@Autowired
	private  AutherRepo autherRepo;
    @Autowired
    private MessageUtils messageUtils;

	Logger log = LoggerFactory.getLogger(AutherService.class);

	@Override
	@Cacheable(value = "auther", key = "#root.methodName")
	public List<Auther> findAll() {
		return super.findAll();
	}

	@Override
	@Cacheable(value = "auther", key = "#id")
	public Auther findById(Long aLong) {
		return super.findById(aLong);
	}

	@Override
	@CacheEvict(value = {"auther"} , key ="#root.methodName", allEntries = true)   // بيشليل الفليوز دي من الكاش لو حصل عليها تعديل
	public Auther insert(Auther entity) {

		log.info("auther name is {} and email is {}", entity.getName(), entity.getEmail());
		if(!entity.getEmail().isEmpty() && entity.getEmail() != null){
			Optional<Auther> auther = autherRepo.findByEmail(entity.getEmail());
			if(auther.isPresent()){
				String msg = messageUtils.getMessage("validation.DaplicateRecoredEmail.message");
				log.error(msg);
				throw new DaplicateRecoredException(msg);
			}
		}
		return super.insert(entity);
	}

	@CacheEvict(value = {"auther"} , key ="#root.methodName", allEntries = true)
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


	@Async(value = "threadPoolTaskExecutor")  // with return
	public CompletableFuture<Auther> findByEmailA(String email) {
		return CompletableFuture.completedFuture(autherRepo.findByEmail(email).get());
	}



}






























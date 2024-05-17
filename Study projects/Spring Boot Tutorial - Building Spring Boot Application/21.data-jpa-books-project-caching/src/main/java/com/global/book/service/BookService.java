package com.global.book.service;

//import com.global.book.base.BaseService;
import com.global.book.base.BaseService;
import com.global.book.entity.Book;
import com.global.book.repository.BookRepo;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.log4j.Log4j2;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

//import javax.persistence.EntityManager;
//import javax.persistence.ParameterMode;
//import javax.persistence.StoredProcedureQuery;
import java.util.List;

@Service
//@RequiredArgsConstructor
//@Log4j2
public class BookService extends BaseService<Book, Long>  {

    @Autowired
	private  BookRepo bookRepo;


	public Book update(Book entity) {
		Book book = super.findById(entity.getId());
		book.setName(entity.getName());
		return super.update(book);
	}

	public int deleteByAutherId (Long id) {
		return bookRepo.deleteByAutherId(id);
	}


	@Override
	@Cacheable(key = "#root.methodName" , value = "book")
	public Book findById(Long aLong) {
		return super.findById(aLong);
	}
}

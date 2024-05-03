package com.global.book.service;

//import com.global.book.base.BaseService;
import com.global.book.base.BaseService;
import com.global.book.entity.Book;
import com.global.book.repository.BookRepo;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
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

    // private final static String USERS_PROC = ".INSERT_JP_USERS";

     // private final EntityManager entityManager;

    //@Autowired
    // private  Environment env;


//    public Book addUsers(Book book) {
//        String dbName = env.getProperty("spring.jpa.properties.hibernate.default_schema");
//
//        StoredProcedureQuery query = this.entityManager.createStoredProcedureQuery(dbName + USERS_PROC);
//
//        query.registerStoredProcedureParameter("Email_Param", String.class, ParameterMode.IN);
//        query.registerStoredProcedureParameter("First_Name_Param", String.class, ParameterMode.IN);
//        query.registerStoredProcedureParameter("Middle_Name_Param", String.class, ParameterMode.IN);
//        query.registerStoredProcedureParameter("Last_Name_Param", String.class, ParameterMode.IN);
//        query.registerStoredProcedureParameter("Gender_Param", String.class, ParameterMode.IN);
//        query.registerStoredProcedureParameter("Phone_Number_Param", String.class, ParameterMode.IN);
//        query.registerStoredProcedureParameter("Summary_Param", String.class, ParameterMode.IN);
//        query.registerStoredProcedureParameter("Experience_Param", Integer.class, ParameterMode.IN);
//        query.registerStoredProcedureParameter("Profile_Img_Param", String.class, ParameterMode.IN);
//
//        query.registerStoredProcedureParameter("Result_Param", Integer.class, ParameterMode.OUT);
//        log.info("=========>> ");
////        query.setParameter("Email_Param", book.getEmail());
////        query.setParameter("First_Name_Param", book.getFirstName());
////        query.setParameter("Middle_Name_Param", book.getMiddleName());
////        query.setParameter("Last_Name_Param", book.getLastName());
////        query.setParameter("Gender_Param", book.getGender());
////        query.setParameter("Phone_Number_Param", book.getPhoneNumber());
////        query.setParameter("Summary_Param", book.getSummary());
////        query.setParameter("Experience_Param", book.getExperience());
////        query.setParameter("Profile_Img_Param", book.getProfileImg());
//
//        int count = ((Number) query.getOutputParameterValue("Result_Param")).intValue();
//
////        if (count == 1) {
////            AddUserResponse usrResp = new AddUserResponse(Constant.STATUS_TRUE, Constant.SUCCESS);
////            usrResp.setData(new AddUserSPResponse("User added successfully."));
////            return usrResp;
////        } else {
////            AddUserResponse usrResp = new AddUserResponse(Constant.STATUS_TRUE, Constant.SUCCESS);
////            usrResp.setData(new AddUserSPResponse("User updated successfully."));
////            return usrResp;
////        }
//
//        return null;
//    }
//
//
//
//
//
//	public List<Book> insertAll(List<Book> entities) {
//
//		return bookRepo.saveAll(entities);
//	}
//
//	public Book update(Book entity) {
//
//		Book book = findById(entity.getId());
//
//		book.setName(entity.getName());
//		book.setPrice(entity.getPrice());
//		book.setAuther(entity.getAuther());
//
//		return bookRepo.save(book);
//	}
//
//
//	public Book findById(long id) {
//        return bookRepo.findById(id).orElse(null);
//    }
//
//


//
//	public void deleteById(Long id) {
//		bookRepo.deleteById(id);
//	}
//
//	public List<Book> findAll() {
//		return bookRepo.findAll();
//	}
//
//	public Book insert(Book book) {
//		return bookRepo.save(book);
//	}


	public List<Book> insertAll(List<Book> entities) {

		return bookRepo.saveAll(entities);
	}


	public Book update(Book entity) {

		Book book = super.findById(entity.getId());

		book.setName(entity.getName());

		return super.update(book);
	}


	public int deleteByAutherId (Long id) {
		return bookRepo.deleteByAutherId(id);
	}
}

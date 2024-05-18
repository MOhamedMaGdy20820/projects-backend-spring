package com.global.book.repository;

import com.global.book.base.BaseRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import com.global.book.entity.Auther;

import java.util.List;
import java.util.Optional;

@Repository
public interface AutherRepo extends BaseRepository <Auther, Long> , JpaSpecificationExecutor<Auther> {

//    @Transactional
//    @Query(value = "UPDATE Auther a SET a.isDeleted = false WHERE a.id = ?1")
//    @Modifying
//    public void restoreById(Long id);

    Optional<Auther> findByEmail(String email);


    @Override
	@EntityGraph(attributePaths = "books")
    List<Auther> findAll() ;

    @Override
	@EntityGraph(attributePaths = "books")
    Optional<Auther> findById(Long id) ;

}
 
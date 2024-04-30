package com.global.book.repository;

import com.global.book.base.BaseRepository;
import com.global.book.dto.BookDto;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.global.book.entity.Book;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepo extends BaseRepository<Book, Long> {

    @Override
    @EntityGraph(value = "loadAuther") //  use with LAZY to make it eager
    Optional<Book> findById(Long id);

    @Override
    @EntityGraph(attributePaths = {"auther"})
    List<Book> findAll();


    @Transactional
    @Modifying
    @Query("delete from Book where auther.id = :id")
    int deleteByAutherId (Long id);

}

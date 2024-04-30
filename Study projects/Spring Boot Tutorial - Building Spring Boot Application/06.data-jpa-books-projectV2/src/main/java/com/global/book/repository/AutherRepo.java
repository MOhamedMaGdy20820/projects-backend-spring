package com.global.book.repository;

import com.global.book.base.BaseRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.global.book.entity.Auther;

@Repository
public interface AutherRepo extends BaseRepository <Auther, Long>  {

}
 
package com.global.book.repository;

import com.global.book.base.*;
import com.global.book.entity.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface BookFavorateRepo extends BaseRepository<BookFavorate, Long> {
	
	
	Optional<BookFavorate> findByAutherIdAndBookId (Long autherId , Long bookId);
	
}

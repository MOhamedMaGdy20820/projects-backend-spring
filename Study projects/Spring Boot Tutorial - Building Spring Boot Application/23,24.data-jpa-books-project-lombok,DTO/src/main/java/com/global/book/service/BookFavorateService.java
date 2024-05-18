package com.global.book.service;

import com.global.book.base.*;
import com.global.book.entity.*;
import com.global.book.repository.*;
import lombok.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
@RequiredArgsConstructor
public class BookFavorateService extends BaseService<BookFavorate, Long> {
	
	
	private final BookFavorateRepo bookFavorateRepo;
	
	public Optional<BookFavorate> findByAutherIdAndBookId (Long autherId , Long bookId){
		
		return bookFavorateRepo.findByAutherIdAndBookId(autherId, bookId);
	}

}

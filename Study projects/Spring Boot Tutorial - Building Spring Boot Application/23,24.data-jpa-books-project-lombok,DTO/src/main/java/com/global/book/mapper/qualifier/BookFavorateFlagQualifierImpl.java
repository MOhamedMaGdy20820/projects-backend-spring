package com.global.book.mapper.qualifier;

import com.global.book.entity.*;
import com.global.book.mapper.qualifier.BookFavorateQualifier.*;
import com.global.book.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@BookFavorateQualifier
@Component
public class BookFavorateFlagQualifierImpl {

	@Autowired
	private BookFavorateService bookFavorateService;

	@BookFavorateFlagMethodQualifier
	public Boolean getIsFavorate (Book entity) {

		Optional<BookFavorate> bookFavorate = bookFavorateService.findByAutherIdAndBookId(entity.getId(), entity.getAuther().getId());

		return bookFavorate.isPresent() ? true : false;
	}

}

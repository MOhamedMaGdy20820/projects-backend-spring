package com.global.book.mapper;

import com.global.book.dto.*;
import com.global.book.entity.*;
import com.global.book.mapper.qualifier.*;
import com.global.book.mapper.qualifier.BookFavorateQualifier.*;
import org.mapstruct.*;

@Mapper(uses = {AutherMapper.class, BookFavorateFlagQualifierImpl.class})
public interface BookMapper {

	@Mapping(target = "auther" , ignore = true)
	@Mapping(target = "autherName" , source = "auther.fullName")
	@Mapping(target = "autherEmail" , source = "auther.email")
	// @Mapping(source = "." , target = "isFavorate" , qualifiedBy = {BookFavorateQualifier.class, BookFavorateFlagMethodQualifier.class})
	BookDto map(Book entity);

	@Mapping(source = "autherName" , target = "auther.fullName")
	@Mapping(source = "autherEmail" , target = "auther.email")
	Book unMap(BookDto dto);



}

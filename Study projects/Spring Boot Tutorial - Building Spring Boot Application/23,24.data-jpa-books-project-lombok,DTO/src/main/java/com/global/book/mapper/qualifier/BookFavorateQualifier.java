package com.global.book.mapper.qualifier;

import org.mapstruct.*;

import java.lang.annotation.*;

@Qualifier
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
public @interface BookFavorateQualifier {
	
	@Qualifier
	@Target(ElementType.METHOD)
	@Retention(RetentionPolicy.CLASS)
	public @interface BookFavorateFlagMethodQualifier {

	}
	
}

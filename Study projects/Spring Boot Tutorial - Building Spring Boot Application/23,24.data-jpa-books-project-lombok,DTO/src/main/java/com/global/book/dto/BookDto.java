package com.global.book.dto;


import com.global.book.base.BaseDto;
import com.global.book.entity.Auther;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.*;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
//@Builder
public class BookDto extends BaseDto<Long> {

	@NotBlank
	private String name ;

	@Min(value = 5)
	@Max(value = 5000)
	private double price;

	@NotNull
	private AutherDto auther;

	private String autherName;

	private String autherEmail;

	private Boolean isFavorate;


}

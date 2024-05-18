package com.global.book.dto;

import com.global.book.base.*;
import com.global.book.validator.IpAddress;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.*;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

import java.util.Date;

@Setter
@Getter
//@Builder
public class AutherDto extends BaseDto<Long> {

	@NotBlank
	private String name;

	@IpAddress()
	private String ipAddress;

	@Email(message = "{validation.constraints.email.message}")
	private String email;

	private long bookCount;

	private String imagePath;

	private Date createdDate;

}

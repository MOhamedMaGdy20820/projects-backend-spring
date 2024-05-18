package com.global.book.base;

import lombok.*;

@Setter
@Getter
public abstract class BaseDto<ID> {

	private ID id;

	private String statusCode;

	private boolean isDeleted;

}

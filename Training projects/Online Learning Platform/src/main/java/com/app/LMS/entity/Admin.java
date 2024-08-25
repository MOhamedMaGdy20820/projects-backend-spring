package com.app.LMS.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Entity
@Builder
@AllArgsConstructor
public class Admin extends BaseUser<Long> {
}

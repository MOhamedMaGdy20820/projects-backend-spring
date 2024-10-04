package com.alibou.book.handler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
//تعني أنه سيتم استثناء (عدم تضمين) الحقول التي تكون فارغة عند تحويل الكائن إلى JSON. "فارغة" هنا تعني:
@JsonInclude(JsonInclude.Include.NON_EMPTY) // لو في متغير قيمته Enpty مش بتكتب في رد ال json بيتعمله ignore
public class ExceptionResponse {

    private Integer businessErrorCode;
    private String businessErrorDescription;
    private String error;
    private Set<String> validationErrors;
    private Map<String, String> errors;
}

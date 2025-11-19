package com.ecommerce.core.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
public class ResponseEntity<T> {
    private int status;
    private T date;
    private Long count; //페이징처리를 위함

}

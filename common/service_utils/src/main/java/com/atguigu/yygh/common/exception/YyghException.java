package com.atguigu.yygh.common.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author damu
 * @date 2022/10/19 0:47
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class YyghException extends RuntimeException{

    private Integer code;

    private String msg;


}

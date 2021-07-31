package com.qxk.springall.springbootjdbc;

import lombok.Builder;
import lombok.Data;

/**
 * @author laijianzhen
 * @date 2021/07/31
 */

@Data
@Builder
public class Foo {
    private Long id;
    private String bar;
}

package com.wulang.gateway;

import lombok.Data;

import java.time.LocalTime;

/**
 * @author wulang
 * @create 2020/1/7/17:23
 */
@Data
public class TimeBeweenConfig {
    private LocalTime start;
    private LocalTime end;
}

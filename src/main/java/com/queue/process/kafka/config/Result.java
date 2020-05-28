package com.queue.process.kafka.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Result<T> {
    /** 错误码. */
    private Integer code;

    /** 成功标识 */
    private Boolean success;

    /** 提示信息. */
    private String msg;

    /** 具体的内容. */
    private T data;


}


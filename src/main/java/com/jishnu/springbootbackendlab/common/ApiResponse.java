package com.jishnu.springbootbackendlab.common;

import lombok.Builder;

@Builder
public record ApiResponse<T>(
        int httpStatus,
        String message,
        T data
) {
}

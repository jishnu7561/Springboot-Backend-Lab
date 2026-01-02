package com.jishnu.springbootbackendlab.common;

import java.time.LocalDateTime;

public record ApiError(
        int status,
        String message,
        LocalDateTime timeStamp
) {
}

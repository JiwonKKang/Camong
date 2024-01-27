package com.bonjung.camong.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    EXPERIENCE_NOT_FOUND(HttpStatus.NOT_FOUND, "experience not founded"),
    PAGE_NOT_FOUND(HttpStatus.NOT_FOUND, "page not founded"),
    INVALID_REQUEST(HttpStatus.BAD_REQUEST, "invalid request"),
    INVALID_PASSWORD(HttpStatus.NOT_FOUND, "invalid password"),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "bad request"),
    UNAUTHORIZED_USER(HttpStatus.UNAUTHORIZED, "unauthorized user"),
    IMAGE_UPLOAD_FAIL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "image upload fail"),
    NO_PERMISSION_ERROR(HttpStatus.FORBIDDEN, "user have no permission"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "there is internal server error");

    private final HttpStatus httpStatus;
    private final String message;

}

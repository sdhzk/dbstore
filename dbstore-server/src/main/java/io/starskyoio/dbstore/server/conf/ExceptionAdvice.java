package io.starskyoio.dbstore.server.conf;

import io.starskyoio.dbstore.common.constants.ResponseCode;
import io.starskyoio.dbstore.common.constants.ResponseMessage;
import io.starskyoio.dbstore.common.exception.ServerException;
import io.starskyoio.dbstore.common.model.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 统一异常处理器
 */
@RestControllerAdvice
@Slf4j
public class ExceptionAdvice {

    @ExceptionHandler(value = ServerException.class)
    public ResponseEntity handleServerException(ServerException ex) {
        log.error("系统出现异常" + ex.getMessage());
        ex.printStackTrace();
        return ResponseEntity.ok(Response.build(ex.getCode(), ex.getMessage()));
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity handleException(Exception ex) {
        log.error("系统出现异常" + ex.getMessage());
        ex.printStackTrace();
        return ResponseEntity.ok(Response.build(ResponseCode.SERVER_ERROR, ResponseMessage.SERVER_ERROR));
    }
}

package io.starskyoio.dbstore.common.exception;

import lombok.Data;

/**
 * 服务器异常
 */
@Data
public class ServerException extends RuntimeException {
    /**
     * 异常代码
     */
    private int code;

    public ServerException(int code, String message) {
        super(message);
        this.code = code;
    }

    public ServerException(int code, String message, Throwable e) {
        super(message, e);
        this.code = code;
    }

    public static ServerException build(int code, String message) {
        return new ServerException(code, message);
    }

    public static ServerException build(int code, String message, Throwable e) {
        return new ServerException(code, message, e);
    }
}

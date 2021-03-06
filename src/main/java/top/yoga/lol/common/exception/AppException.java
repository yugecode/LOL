package top.yoga.lol.common.exception;

import top.yoga.lol.common.ResponseTemplate;

/**
 * @author luojiayu
 */
public class AppException extends RuntimeException {

    private String errorCode = ResponseTemplate.APP_EXCEPTION;

    public AppException(String message) {
        super(message);
    }

    public AppException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public AppException(String errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public AppException(String errorCode, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
    }

    public AppException(String errorCode, String message, Throwable cause,
                        boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}

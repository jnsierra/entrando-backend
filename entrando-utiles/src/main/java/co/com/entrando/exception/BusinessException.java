package co.com.entrando.exception;


import co.com.entrando.exception.enumeration.TYPE_EXCEPTION;

public class BusinessException extends RuntimeException {
    private final Long code;
    private final TYPE_EXCEPTION type;
    public BusinessException(Long code, TYPE_EXCEPTION type, String message) {
        super(message);
        this.code = code;
        this.type = type;
    }
    public BusinessException(Long code, TYPE_EXCEPTION type, String message, Exception e) {
        super(message, e);
        this.code = code;
        this.type = type;
    }

    public Long getCode() {
        return code;
    }

    public TYPE_EXCEPTION getType() {
        return type;
    }
}

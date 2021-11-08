package net.futureorigin.architecture.sample.cola.common.exception;

/**
 * BizExceptionCode
 * <p></p>
 *
 * @author Leander Lee on 2021/9/2.
 */
public enum BizExceptionCode implements ExceptionCode {

    AUTH_FAILURE("401", "认证信息已失效或非法，请重新登录。"),
    ACCESS_FORBIDDEN("403", "无权限访问。"),

    BIZ_FAILURE("E000", "业务操作失败。"),
    OBJ_NOT_FOUND("E404", "对象未找到。"),
    OBJ_CHECK_FAILURE("E400", "对象校验失败。"),

    USER_ADD_FAILURE("U000", "用户添加失败"),
    USER_UPDATE_FAILURE("U001", "用户更新失败。"),
    USER_NOT_FOUND("U404", "用户未找到。"),

    CREDIT_ADD_FAILURE("C000", "积分添加失败。"),
    CREDIT_NOT_ENOUGH("C001", "没有足够的积分。");

    private String code;
    private String message;

    BizExceptionCode(String code, String message) {
        this.code = code;
        this.message = message;
    }


    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}

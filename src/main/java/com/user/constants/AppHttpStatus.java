package com.user.constants;

import com.fasterxml.jackson.annotation.JsonValue;

public enum AppHttpStatus {
    /**
     * User authentication failed
     */
    AUTH_FAILED("4001"),

    /**
     * User authentication failed
     */
    USER_BUYING("C002001"),

    USER_DELETED("A004001"),

    PRODUCT_BUYING("D001001"),

    PRODUCT_BUYING_OUT_OF_DATE("D001002"),

    REQUEST_OVER_REVENUE("K010001"),
    /**
     * Temporary password is invalid. Please check the input contents.
     */
    TEMPORARY_PASSWORD_INVALID("4002"),
    /**
     * There are already users logged in with this ID.
     */
    ALREADY_EXISTS_USER_ID("4010"),
    /**
     * There are already floor.
     */
    ALREADY_EXISTS_FLOOR("4011"),
    /**
     * Data acquisition failed.
     */
    FAILED_TO_GET_DATA("4041"),
    /**
     * Failed to save data
     */
    FAILED_TO_SAVE_DATA("4042"),
    /**
     * Failed to delete data
     */
    FAILED_TO_DELETE_DATA("4043"),
    /**
     * Failed to save data
     */
    FAILED_TO_UPDATE_DATA("4044"),
    /**
     * Failed to register data
     */
    FAILED_TO_REGISTER_DATA("4045"),
    /**
     * Invalid file format.
     */
    INVALID_FILE_FORMAT("4060"),
    /**
     * Not selectable
     */
    NOT_SELECTABLE("4061"),

    /**
     * expired temporary password
     */
    EXPIRED_TEMPORARY_PASSWORD("4091"),
    /**
     * this e-mail address is already registered
     */
    EMAIL_ALREADY_REGISTERED("4092"),

    /**
     * this e-mail address is already registered
     */
    EMAIL_NEED_TO_CONFIRM("4093"),

    /**
     * Application error occurred
     */
    INTERNAL_SERVER_ERROR("5000"),

    SUCCESS("200"),

    ;

    /*-----------------------------------------------
     * Property
     *-----------------------------------------------*/
    /**
     * HTTP Status code.
     */
    private String code;

    /*-----------------------------------------------
     * Constructor
     *-----------------------------------------------*/
    AppHttpStatus(String code) {
        this.code = code;
    }
    /*-----------------------------------------------
     * Getter, Setter
     *-----------------------------------------------*/

    @JsonValue
    public String getCode() {
        return code;
    }

}
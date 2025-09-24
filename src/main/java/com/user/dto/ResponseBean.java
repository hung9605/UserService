package com.user.dto;

import com.user.constants.AppHttpStatus;

public class ResponseBean {

    private Object data;
    private String message;
    private AppHttpStatus status = AppHttpStatus.SUCCESS;
    
    public ResponseBean() {
		super();
	}

	public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public AppHttpStatus getStatus() {
        return status;
    }

    public void setStatus(AppHttpStatus status) {
        this.status = status;
    }

	public ResponseBean(Object data) {
		super();
		this.data = data;
	}
    
   
}
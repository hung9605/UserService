package com.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.user.constants.AppHttpStatus;
import com.user.constants.CommonConstant;
import com.user.dto.ResponseBean;

@Controller
public class BaseController {

    public static final String ERROR = "error";
    public static final String RESPONSE_NULL = "Response null!";
    
    /**
     * logger
     */
    public final Logger logger = LoggerFactory.getLogger(this.getClass());

    
    /**
     * @param responseBean
     * @return
     */
    protected ResponseEntity<ResponseBean> response(ResponseBean responseBean) {
        if (responseBean == null) {
            throw new IllegalArgumentException(RESPONSE_NULL);
        }
        return new ResponseEntity<>(responseBean, HttpStatus.OK);
    }

    /**
     * exception
     * @param responseBean
     * @param exception
     * @return
     */
    protected ResponseEntity<ResponseBean> responseError(ResponseBean responseBean, Exception exception) {
        responseBean.setStatus(AppHttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(responseBean, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    protected ResponseEntity<ResponseBean> defaultResponse() {
		return response(new ResponseBean(CommonConstant.OK));
	}


}

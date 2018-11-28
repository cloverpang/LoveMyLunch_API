package com.lovemylunch.api.service;

import com.lovemylunch.common.beans.ServiceCallResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AuthenticationService {

    ServiceCallResult userLogin(String userName, String password, HttpServletRequest request);

	ServiceCallResult removeAPIToken(HttpServletRequest request, HttpServletResponse response);
}

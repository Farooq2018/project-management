package com.farooq.project_management.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppErrorController implements ErrorController {

    @GetMapping("/error")
    public String handleError(HttpServletRequest httpServletRequest) {
        Object status = httpServletRequest.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if (statusCode == HttpStatus.NOT_FOUND.value()){
                return "errorpages/error-404";
            }
            else if (statusCode == HttpStatus.FORBIDDEN.value()){
                return "errorpages/error-403";
            }
            else if (statusCode == HttpStatus.METHOD_NOT_ALLOWED.value()){
                return "errorpages/error-405";
            }
            else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()){
                return "errorpages/error-500";
            }
        }

        return "errorpages/error";
    }
}

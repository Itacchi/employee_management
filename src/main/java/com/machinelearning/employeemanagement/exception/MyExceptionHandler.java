package com.machinelearning.employeemanagement.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;


@ControllerAdvice
public class MyExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(MyExceptionHandler.class);

    @ExceptionHandler(SQLException.class)
    public String handleSQLException(HttpServletRequest request, SQLException e, Model model) {
        model.addAttribute("message", "SQL Exception!");
        logger.info("SQLException Occured:: URL=" + request.getRequestURL());
        return "index";
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "IOException occured")
    @ExceptionHandler(IOException.class)
    public void handleIOException() {
        logger.info("IOException Occured :: 404 error code");
    }

    @ExceptionHandler(NullPointerException.class)
    public String handleNullPointerException(Model theModel, NullPointerException e) {
        theModel.addAttribute("message", "NullPointerException");
        logger.info("NullPointerException Occured");
        return "index";
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public String handleMaxSizeException(Model model, MaxUploadSizeExceededException e) {
        model.addAttribute("message", "File is too large!");
        logger.info("FileSizeException Occured");
        return "upload_excel";
    }

    @ExceptionHandler(InvalidFileFormatException.class)
    public String handleInvalidFileFormatException(Model model, InvalidFileFormatException e) {
        model.addAttribute("message", "Invalid File Format");
        logger.info("Invalid File Format occured");
        return "upload_excel";
    }

}

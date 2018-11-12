package com.parus.reply.challenge.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Exception handler that converts internal exceptions into adequate HTTP status response code.
 *
 * @author Przemyslaw Parus
 *
 */
@ControllerAdvice
public class ChallengeExceptionHandler {

    @ResponseBody
    @ExceptionHandler
    public void handleIllegalArgumentException(
            IllegalArgumentException e, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value());
    }

}

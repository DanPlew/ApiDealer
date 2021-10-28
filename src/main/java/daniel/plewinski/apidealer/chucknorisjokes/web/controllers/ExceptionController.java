package daniel.plewinski.apidealer.chucknorisjokes.web.controllers;

import daniel.plewinski.apidealer.chucknorisjokes.web.models.ErrorDTO;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.PrintWriter;
import java.io.StringWriter;

@ControllerAdvice
@EnableWebMvc
public class ExceptionController extends ResponseEntityExceptionHandler{

    @ExceptionHandler(value = HttpClientErrorException.class)
    @ResponseBody
    public ErrorDTO handleHttpClientError(HttpClientErrorException httpClientErrorException){
        return new ErrorDTO(
                httpClientErrorException.getClass().toString(),
                "There was an error with external server",
                getPrintStackTrace(httpClientErrorException));
    }

    private String getPrintStackTrace(Exception exception){
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        exception.printStackTrace(printWriter);
        return stringWriter.toString();
    }
}

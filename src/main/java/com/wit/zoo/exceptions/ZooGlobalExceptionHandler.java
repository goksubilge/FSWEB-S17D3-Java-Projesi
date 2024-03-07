package com.wit.zoo.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ZooGlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ZooErrorResponse> handException(ZooException zooExc){
       ZooErrorResponse errResp = new ZooErrorResponse(zooExc.getStatus().value(), zooExc.getMessage(), System.currentTimeMillis());

       return new ResponseEntity<>(errResp, zooExc.getStatus());
    }

    @ExceptionHandler
    public ResponseEntity<ZooErrorResponse> handException(Exception exception){
        ZooErrorResponse errResp = new ZooErrorResponse(HttpStatus.BAD_REQUEST.value(), exception.getMessage(), System.currentTimeMillis());

        log.error(exception.getMessage());
        return new ResponseEntity<>(errResp, HttpStatus.BAD_REQUEST);
    }
}

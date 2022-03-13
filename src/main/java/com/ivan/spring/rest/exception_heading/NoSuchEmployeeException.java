package com.ivan.spring.rest.exception_heading;

public class NoSuchEmployeeException  extends  RuntimeException{
    //создание Эксепшена который будет выбрасываться при методе getEmployee
    public NoSuchEmployeeException(String message){
        super(message);
    }
}

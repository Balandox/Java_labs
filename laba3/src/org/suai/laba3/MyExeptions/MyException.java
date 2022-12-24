package org.suai.laba3.MyExeptions;


public class MyException extends RuntimeException { // собственный класс исключений

    private String ErrorMessage;

    public MyException(String ErrorString){
        this.ErrorMessage = ErrorString;
    }

    public String getErrorMessage(){
        return ErrorMessage;
    }

}

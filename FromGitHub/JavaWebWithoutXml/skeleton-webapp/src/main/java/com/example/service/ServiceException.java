package com.example.service;

import com.example.exceptions.ErrorCode;


public class ServiceException extends Exception {

   private static final long serialVersionUID = 1327465625810721651L;

   private ErrorCode errorCode;
   
   public ServiceException(ErrorCode errorCode, String msg){
      super(msg);
      this.errorCode = errorCode;
   }
   
   public ServiceException(ErrorCode errorCode, String msg, Throwable e){
      super(msg, e);
      this.errorCode = errorCode;
   }
   
   public ServiceException(ErrorCode errorCode, Throwable e){
      super(e);
      this.errorCode = errorCode;
   }

   
   public ErrorCode getErrorCode() {
      return errorCode;
   }

   
   public void setErrorCode(ErrorCode errorCode) {
      this.errorCode = errorCode;
   }
   
   public String toString(){
      return String.format("Service exception with error code [%s] and message [%s]", errorCode, getMessage());
   }
}

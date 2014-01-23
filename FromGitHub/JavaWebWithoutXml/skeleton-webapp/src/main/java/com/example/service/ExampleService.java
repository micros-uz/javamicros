package com.example.service;

import org.springframework.stereotype.Service;

@Service
public interface ExampleService {

   /**
    * @param input
    * @return
    * @throws ServiceException
    */
   public Integer doSomething(Integer input) throws ServiceException;
   
}

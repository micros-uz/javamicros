package mikeyp.spikes.spring.social.auth;




import java.util.concurrent.Callable;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

public  abstract  class   RequestScopeCallable <T> implements Callable<T>{
  private final RequestAttributes requestAttributes;
  private Thread thread;

  public RequestScopeCallable() {
    this.requestAttributes = RequestContextHolder.getRequestAttributes();
    this.thread = Thread.currentThread();
  }

  public T call() throws Exception{
    try {
      RequestContextHolder.setRequestAttributes(requestAttributes);
      return onCall();
    } finally {
      if (Thread.currentThread() != thread) {
        RequestContextHolder.resetRequestAttributes();
      }
      thread = null;
    }
  }

  protected abstract  T  onCall() throws Exception;
} 

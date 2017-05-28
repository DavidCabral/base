package br.com.modelo.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@XmlRootElement
@JsonInclude(Include.NON_NULL)
public class ErrorMessage {

    private List<String> errors;
    private String error;
    private int status;
    private String path;
    private long timestamp; 
    

    public ErrorMessage() {
    }

    public ErrorMessage(int status , List<String> errors) {
        this.errors = errors;
        this.status = status;
    }

    public ErrorMessage(int status , String error) {
      this.error = error;
      this.status = status;
  }
    
    
    public ErrorMessage(List<String> errors) {      
      this.errors = errors;
  }

    public ErrorMessage(String error) {
        this(Collections.singletonList(error));
    }

    public ErrorMessage(String ... errors) {
        this(Arrays.asList(errors));
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public String getError() {
      return error;
    }

    public void setError(String error) {
      this.error = error;
    }

    public int getStatus() {
      return status;
    }

    public void setStatus(int status) {
      this.status = status;
    }

    public String getPath() {
      return path;
    }

    public void setPath(String path) {
      this.path = path;
    }

    public long getTimestamp() {
      return timestamp;
    }

    public void setTimestamp(long timestamp) {
      this.timestamp = timestamp;
    }
    
    
    
}

package springboot.restapi.app.Exception;

public class APIException extends RuntimeException{
    public APIException(String message){
        super(message);
    }
}

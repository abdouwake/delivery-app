package deliveryapp.restaurantmanager.ResponseEntity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class ResponseEntity {

    private String message;
    private HttpStatus httpStatus;
    private Object data;



    public static org.springframework.http.ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object responseObj) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("message", message);
        map.put("status", status.value());
        map.put("data", responseObj);
        return new org.springframework.http.ResponseEntity<Object>(map,status);
    }

}

package json;

import java.util.List;

public class Message {
    private String status;
    private String message;
    private List<Object> code;

    public Message(String status, String message, List<Object> code) {
        this.status = status;
        this.message = message;
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Object> getCode() {
        return code;
    }

    public void setCode(List<Object> code) {
        this.code = code;
    }
}

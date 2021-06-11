package extranet.proyecto.models;


public class MessageResponse {

    private Integer status;
    private String error;
    private Object object;

    public MessageResponse(Integer status, String error, Object object) {
        this.status = status;
        this.error = error;
        this.object = object;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}

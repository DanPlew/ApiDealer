package daniel.plewinski.apidealer.chucknorisjokes.web.models;

public class ErrorDTO {

    private String errorName;
    private String message;
    private String printStackTrace;

    public ErrorDTO() {
    }

    public ErrorDTO(String errorName, String message, String printStackTrace) {
        this.errorName = errorName;
        this.message = message;
        this.printStackTrace = printStackTrace;
    }

    public String getErrorName() {
        return errorName;
    }

    public void setErrorName(String errorName) {
        this.errorName = errorName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPrintStackTrace() {
        return printStackTrace;
    }

    public void setPrintStackTrace(String printStackTrace) {
        this.printStackTrace = printStackTrace;
    }

    @Override
    public String toString() {
        return "ErrorDTO{" +
                "errorName='" + errorName + '\'' +
                ", message='" + message + '\'' +
                ", printStackTrace='" + printStackTrace + '\'' +
                '}';
    }
}

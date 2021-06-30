package uk.gov.hmcts.reform.fprl.framework.exceptions;

/*
 * Use it in Task when data is not found / invalid.
 */
public class InvalidDataForTaskException extends TaskException {

    public InvalidDataForTaskException(Throwable cause) {
        super(cause);
    }

    public InvalidDataForTaskException(String message) {
        super(message);
    }

}

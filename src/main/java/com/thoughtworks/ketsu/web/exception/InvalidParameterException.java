package com.thoughtworks.ketsu.web.exception;

import java.util.ArrayList;
import java.util.List;

public class InvalidParameterException extends RuntimeException {

    private List<InvalidParamMessage> invalidParamMessageList = new ArrayList<>();

    public InvalidParameterException(String message) {
        super(message);
    }

    public InvalidParameterException() {
        super();
    }

    public InvalidParameterException(Exception e) {
        super(e);
    }

    public InvalidParameterException(List<String> invalidParamsList) {

        for (String invalidParam : invalidParamsList) {
            invalidParamMessageList.add(new InvalidParamMessage(invalidParam));
        }
    }

    public List<InvalidParamMessage> getInvalidParamMessageList() {
        return invalidParamMessageList;
    }
}

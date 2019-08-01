package com.rnrmedia.social.messenger.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ErrorMessage {

    private String errorMessage;
    private String documentation;
    private int errorCode;


    public ErrorMessage () {

    }

    public ErrorMessage(int errorCode, String errorMessage, String documentation) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.documentation = documentation;
    }

    public void setErrorMessage( String errorMessage ) {

        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public void setErrorCode( int errorCode ) {
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public void setDocumentation( String documentation ) {
        this.documentation = documentation;
    }

    public String getDocumentation() {
        return this.documentation;
    }
}


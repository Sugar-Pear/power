package com.lyp.model;

import java.sql.Timestamp;

public class Message {
    private int messageId;
    private int fromId;
    private String fromName;
    private int touserNumber;
    private String messageText;
    private Timestamp messageDate;

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public int getFromId() {
        return fromId;
    }

    public void setFromId(int fromId) {
        this.fromId = fromId;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public int getTouserNumber() {
        return touserNumber;
    }

    public void setTouserNumber(int touserNumber) {
        this.touserNumber = touserNumber;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public Timestamp getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(Timestamp messageDate) {
        this.messageDate = messageDate;
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageId=" + messageId +
                ", fromId=" + fromId +
                ", fromName='" + fromName + '\'' +
                ", touserNumber=" + touserNumber +
                ", messageText='" + messageText + '\'' +
                ", messageDate=" + messageDate +
                '}';
    }
}

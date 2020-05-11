package com.proforce.proforcecore.exception;

public class EventLogNotFound extends RuntimeException {

    public EventLogNotFound() {
        super("Event Log with the given id does not exist");
    }
}

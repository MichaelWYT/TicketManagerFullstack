package com.barclays.ticketmanager.domain;

public enum Status {
    TODO("To do"),
    INPROGRESS("In progress"),
    DONE("Done");

    private String statusString;

    Status(String statusString) {
        this.statusString = statusString;
    }

    public String getStatus() {
        return this.statusString;
    }

    @Override
    public String toString() {
        switch (this) {
            case TODO:
                return "To do";
            case INPROGRESS:
                return "In progress";
            case DONE:
                return "Done";
        }
        return super.toString();
    }
}

package com.ua.glebkorobov.eighthpractice.dto;

import java.util.List;

public enum Status {
    PLANNED{
        @Override
        public boolean changeStatus(Status status) {
            List<Status> statuses = List.of(PROGRESS, NOTIFIED, DONE, CANCELED);
            return statuses.contains(status);
        }
    },
    PROGRESS{
        @Override
        public boolean changeStatus(Status status) {
            List<Status> statuses = List.of(NOTIFIED, DONE, CANCELED);
            return statuses.contains(status);
        }
    },
    NOTIFIED{
        @Override
        public boolean changeStatus(Status status) {
             List<Status> statuses = List.of(DONE, CANCELED);
            return statuses.contains(status);
        }
    },
    DONE{
        @Override
        public boolean changeStatus(Status status) {
            return false;
        }
    },
    CANCELED{
        @Override
        public boolean changeStatus(Status status) {
            return false;
        }
    };

    public abstract boolean changeStatus(Status status);
}

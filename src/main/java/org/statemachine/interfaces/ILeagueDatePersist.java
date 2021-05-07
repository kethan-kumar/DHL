package org.statemachine.interfaces;

import java.time.LocalDate;

public interface ILeagueDatePersist {
    LocalDate getCurrentDate();

    void setCurrentDate(LocalDate date);
}

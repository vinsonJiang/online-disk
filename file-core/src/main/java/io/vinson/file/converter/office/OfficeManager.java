package io.vinson.file.converter.office;

import io.vinson.file.converter.LiefCycle;
import io.vinson.file.converter.exception.OfficeException;
import io.vinson.file.converter.task.OfficeTask;

public interface OfficeManager extends LiefCycle {

    void execute(OfficeTask task) throws OfficeException;

    void start() throws OfficeException;

    void stop() throws OfficeException;

    boolean isRunning();

}

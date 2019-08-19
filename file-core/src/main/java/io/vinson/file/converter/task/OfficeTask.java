package io.vinson.file.converter.task;

import io.vinson.file.converter.exception.OfficeException;
import io.vinson.file.converter.office.OfficeConnection;

public interface OfficeTask {

    void execute(OfficeConnection connection) throws OfficeException;

}

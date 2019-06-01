package io.vinson.file.converter.office;

import io.vinson.file.converter.exception.OfficeException;

public interface OfficeTask {

    void execute(OfficeConnection connection) throws OfficeException;

}

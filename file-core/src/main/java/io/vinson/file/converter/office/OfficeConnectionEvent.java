package io.vinson.file.converter.office;

import java.util.EventObject;

class OfficeConnectionEvent extends EventObject {

    private static final long serialVersionUID = -1L;

    public OfficeConnectionEvent(OfficeConnection source) {
        super(source);
    }

}
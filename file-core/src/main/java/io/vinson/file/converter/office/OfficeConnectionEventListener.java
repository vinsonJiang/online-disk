package io.vinson.file.converter.office;

public interface OfficeConnectionEventListener {
    public void connected(OfficeConnectionEvent event);
    public void disconnected(OfficeConnectionEvent event);
}

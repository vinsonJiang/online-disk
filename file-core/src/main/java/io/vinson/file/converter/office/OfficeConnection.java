package io.vinson.file.converter.office;

import com.sun.star.lang.XComponent;
import com.sun.star.lang.XMultiComponentFactory;
import com.sun.star.uno.XComponentContext;
import io.vinson.file.converter.exception.OfficeException;

import java.util.ArrayList;
import java.util.List;

public class OfficeConnection {

    private XComponent bridgeComponent;
    private XComponentContext componentContext;
    private XMultiComponentFactory serviceManager;

    private final UnoUrl unoUrl;

    private volatile boolean connected = false;

    private final List<OfficeConnectionEventListener> connectionEventListeners = new ArrayList<OfficeConnectionEventListener>();

    public void addConnectionEventListener(OfficeConnectionEventListener connectionEventListener) {
        connectionEventListeners.add(connectionEventListener);
    }

    public OfficeConnection(UnoUrl unoUrl) {
        this.unoUrl = unoUrl;
    }


    public void init() {

    }

    public void connection() {

    }

    public void disConnection() {

    }

    public boolean isConnected() {
        return connected;
    }

    public Object getService(String serviceName) {
        try {
            return serviceManager.createInstanceWithContext(serviceName, componentContext);
        } catch (Exception exception) {
            throw new OfficeException(String.format("failed to obtain service '%s'", serviceName), exception);
        }
    }


}

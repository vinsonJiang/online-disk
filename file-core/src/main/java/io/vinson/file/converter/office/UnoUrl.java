package io.vinson.file.converter.office;

class UnoUrl {

    private final String acceptString;
    private final String connectString;

    private UnoUrl(String acceptString, String connectString) {
        this.acceptString = acceptString;
        this.connectString = connectString;
    }

    public static UnoUrl socket(int port) {
        String socketString = "socket,host=127.0.0.1,port=" + port;
        return new UnoUrl(socketString, socketString + ",tcpNoDelay=1");
    }

    public static UnoUrl pipe(String pipeName) {
        String pipeString = "pipe,name=" + pipeName;
        return new UnoUrl(pipeString, pipeString);
    }

    public String getAcceptString() {
        return acceptString;
    }

    public String getConnectString() {
        return connectString;
    }

    @Override
    public String toString() {
        return connectString;
    }

}

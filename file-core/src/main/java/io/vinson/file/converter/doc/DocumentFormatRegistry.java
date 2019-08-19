package io.vinson.file.converter.doc;

import io.vinson.file.converter.util.DocumentFamily;

import java.util.Set;

public interface DocumentFormatRegistry {

    public DocumentFormat getFormatByExtension(String extension);

    public DocumentFormat getFormatByMediaType(String mediaType);

    public Set<DocumentFormat> getOutputFormats(DocumentFamily family);

}

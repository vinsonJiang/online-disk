package io.vinson.file.converter.doc;

import io.vinson.file.converter.util.DocumentFamily;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SimpleDocumentFormatRegistry implements DocumentFormatRegistry {

	private List<DocumentFormat> documentFormats = new ArrayList<DocumentFormat>();

	public void addFormat(DocumentFormat documentFormat) {
		documentFormats.add(documentFormat);
	}

	public DocumentFormat getFormatByExtension(String extension) {
        if (extension == null) {
            return null;
        }
        String lowerExtension = extension.toLowerCase();
        //TODO keep a documentByExtension map instead
		for (DocumentFormat format : documentFormats) {
			if (format.getExtension().equals(lowerExtension)) {
				return format;
			}
		}
		return null;
	}

	public DocumentFormat getFormatByMediaType(String mediaType) {
        if (mediaType == null) {
            return null;
        }
        //TODO keep a documentByMediaType map instead
        for (DocumentFormat format : documentFormats) {
            if (format.getMediaType().equals(mediaType)) {
                return format;
            }
        }
	    return null;
	}

	public Set<DocumentFormat> getOutputFormats(DocumentFamily family) {
	    Set<DocumentFormat> formats = new HashSet<DocumentFormat>();
        for (DocumentFormat format : documentFormats) {
            if (format.getStoreProperties(family) != null) {
                formats.add(format);
            }
        }
	    return formats;
	}

}

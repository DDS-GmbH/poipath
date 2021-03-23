package com.docutools.poipath;

import org.apache.poi.ooxml.POIXMLDocument;

import java.util.Optional;

public class POIUtils {

    public static Optional<String> findLanguage(POIXMLDocument document) {
        return document.getProperties()
                .getCoreProperties()
                .getUnderlyingProperties()
                .getLanguageProperty();
    }

    private POIUtils() {
    }
}

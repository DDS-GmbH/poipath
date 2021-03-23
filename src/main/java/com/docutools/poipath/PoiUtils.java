package com.docutools.poipath;

import java.util.Optional;
import org.apache.poi.ooxml.POIXMLDocument;

public class PoiUtils {

  /**
   * Tries to find the language setting of the given {@link POIXMLDocument} in the documents
   * properties.
   *
   * @param document the document
   * @return the language code
   */
  public static Optional<String> findLanguage(POIXMLDocument document) {
    return document.getProperties()
        .getCoreProperties()
        .getUnderlyingProperties()
        .getLanguageProperty();
  }

  private PoiUtils() {
  }
}

package com.docutools.poipath.xwpf;

import com.docutools.poipath.PoiUtils;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

public record XWPFDocumentWrapper(XWPFDocument document, String language) {

  public XWPFDocumentWrapper(XWPFDocument document) {
    this(document, PoiUtils.findLanguage(document).orElse(null));
  }

  public BodyElementWrapper bodyElement(int index) {
    return new BodyElementWrapper(document.getBodyElements().get(index));
  }

}

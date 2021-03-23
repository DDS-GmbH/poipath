package com.docutools.poipath.xwpf;

import com.docutools.poipath.POIUtils;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

public record XWPFDocumentWrapper(XWPFDocument document, String language) {

  public XWPFDocumentWrapper(XWPFDocument document) {
    this(document, POIUtils.findLanguage(document).orElse(null));
  }

  public BodyElementWrapper bodyElement(int index) {
    return new BodyElementWrapper(document.getBodyElements().get(index));
  }

  public ParagraphWrapper paragraph(int index) {
    return bodyElement(index).asParagraph();
  }

  public TableWrapper table(int index) {
      return bodyElement(index).asTable();
  }

}

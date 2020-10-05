package com.docutools.poipath.xwpf;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

public class XWPFDocumentWrapper {
  private final XWPFDocument document;

  private XWPFDocumentWrapper(XWPFDocument document) {
    this.document = document;
  }

  public static XWPFDocumentWrapper parse(XWPFDocument document) {
    return new XWPFDocumentWrapper(document);
  }

  public ParagraphListWrapper paragraphs() {
    return new ParagraphListWrapper(document.getParagraphs());
  }

  public ParagraphWrapper paragraph(int i) {
    return new ParagraphWrapper(document.getParagraphArray(i));
  }

  public TableListWrapper tables() {
    return new TableListWrapper(document.getTables());
  }

  public TableWrapper table(int i) {
    return new TableWrapper(document.getTableArray(i));
  }

  public int length() {
    return document.getParagraphs().size();
  }

  public String language() {
    return document.getProperties().getCoreProperties().getUnderlyingProperties().getLanguageProperty().get();
  }
}

package com.docutools.poipath.xwpf;

import org.apache.poi.xwpf.usermodel.XWPFTableCell;

public record CellWrapper(XWPFTableCell cell) {

  public ParagraphListWrapper paragraphs() {
    return new ParagraphListWrapper(cell.getParagraphs());
  }

  public ParagraphWrapper paragraph(int i) {
    return new ParagraphWrapper(cell.getParagraphArray(i));
  }

}

package com.docutools.poipath.xwpf;

import java.util.List;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

public record ParagraphListWrapper(List<XWPFParagraph> paragraphs) {

  public ParagraphWrapper get(int i) {
    return new ParagraphWrapper(paragraphs.get(i));
  }

  public int length() {
    return paragraphs.size();
  }
}

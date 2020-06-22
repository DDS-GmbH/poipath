package com.docutools.poipath.xwpf;

import java.util.List;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

public class ParagraphListWrapper {
  private final List<XWPFParagraph> paragraphs;

  public ParagraphListWrapper(List<XWPFParagraph> paragraphs) {
    this.paragraphs = paragraphs;
  }

  public ParagraphWrapper get(int i) {
    return new ParagraphWrapper(paragraphs.get(i));
  }

  public int length() {
    return paragraphs.size();
  }
}

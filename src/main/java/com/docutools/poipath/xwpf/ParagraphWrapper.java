package com.docutools.poipath.xwpf;

import org.apache.poi.xwpf.usermodel.XWPFParagraph;

public record ParagraphWrapper(XWPFParagraph paragraph) {

  public RunListWrapper runs() {
    return new RunListWrapper(paragraph.getRuns());
  }

  public RunWrapper run(int i) {
    return new RunWrapper(paragraph.getRuns().get(i));
  }

  public String text() {
    return paragraph.getText();
  }

}

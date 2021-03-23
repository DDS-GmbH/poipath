package com.docutools.poipath.xwpf;

import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.util.List;
import java.util.stream.Collectors;

public record ParagraphWrapper(XWPFParagraph paragraph) {

  public List<RunWrapper> runs() {
    return paragraph.getRuns().stream()
            .map(RunWrapper::new)
            .collect(Collectors.toList());
  }

  public RunWrapper run(int i) {
    return new RunWrapper(paragraph.getRuns().get(i));
  }

  public String text() {
    return paragraph.getText();
  }

}

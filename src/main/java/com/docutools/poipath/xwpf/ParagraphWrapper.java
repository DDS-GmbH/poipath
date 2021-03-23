package com.docutools.poipath.xwpf;

import java.util.List;
import java.util.stream.Collectors;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

public record ParagraphWrapper(XWPFParagraph paragraph) {

  /**
   * Returns the {@link org.apache.poi.xwpf.usermodel.XWPFRun}s of this paragraph.
   *
   * @return the {@link RunWrapper}s
   */
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

package com.docutools.poipath.xwpf;

import org.apache.poi.xwpf.usermodel.XWPFRun;

public class RunWrapper {
  private final XWPFRun xwpfRun;

  public RunWrapper(XWPFRun xwpfRun) {
    this.xwpfRun = xwpfRun;
  }

  public String text() {
    return xwpfRun.text();
  }
}

package com.docutools.poipath.xwpf;

import java.util.List;
import org.apache.poi.xwpf.usermodel.XWPFRun;

public record RunListWrapper(List<XWPFRun> runs) {

  public RunWrapper get(int i) {
    return new RunWrapper(runs.get(i));
  }
}

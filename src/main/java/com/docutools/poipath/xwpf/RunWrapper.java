package com.docutools.poipath.xwpf;

import java.util.List;
import org.apache.poi.xwpf.usermodel.XWPFPicture;
import org.apache.poi.xwpf.usermodel.XWPFRun;

public record RunWrapper(XWPFRun xwpfRun) {

  public String text() {
    return xwpfRun.text();
  }

  public XWPFPicture picture(int i) {
    return xwpfRun.getEmbeddedPictures().get(i);
  }

  public List<XWPFPicture> pictures() {
    return xwpfRun.getEmbeddedPictures();
  }
}

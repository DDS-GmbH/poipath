package com.docutools.poipath.xwpf;

import java.util.List;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

public class RowListWrapper {
  private final List<XWPFTableRow> rows;

  public RowListWrapper(List<XWPFTableRow> rows) {
    this.rows = rows;
  }
}

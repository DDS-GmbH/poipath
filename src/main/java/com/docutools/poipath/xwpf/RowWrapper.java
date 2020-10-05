package com.docutools.poipath.xwpf;

import org.apache.poi.xwpf.usermodel.XWPFTableRow;

public class RowWrapper {
  private final XWPFTableRow row;

  public RowWrapper(XWPFTableRow row) {
    this.row = row;
  }

  public CellWrapper column(int i) {
    return new CellWrapper(row.getCell(i));
  }
}

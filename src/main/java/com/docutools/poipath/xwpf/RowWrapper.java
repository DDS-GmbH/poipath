package com.docutools.poipath.xwpf;

import org.apache.poi.xwpf.usermodel.XWPFTableRow;

public record RowWrapper(XWPFTableRow row) {

  public CellWrapper column(int i) {
    return new CellWrapper(row.getCell(i));
  }
}

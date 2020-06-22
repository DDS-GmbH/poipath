package com.docutools.poipath.xssf;

import org.apache.poi.xssf.usermodel.XSSFRow;

public class RowWrapper {
  private final XSSFRow row;

  public RowWrapper(XSSFRow row) {
    this.row = row;
  }

  public CellWrapper cell(int i) {
    return new CellWrapper(row.getCell(i));
  }
}

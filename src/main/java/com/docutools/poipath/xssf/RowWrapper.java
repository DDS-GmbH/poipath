package com.docutools.poipath.xssf;

import org.apache.poi.xssf.usermodel.XSSFRow;

public record RowWrapper(XSSFRow row) {

  public CellWrapper cell(int i) {
    return new CellWrapper(row.getCell(i));
  }

}

package com.docutools.poipath.xssf;

import org.apache.poi.xssf.usermodel.XSSFSheet;

public record SheetWrapper(XSSFSheet sheet) {

  public RowWrapper row(int i) {
    return new RowWrapper(sheet.getRow(i));
  }

}

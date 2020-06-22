package com.docutools.poipath.xssf;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XSSFWorkbookWrapper {
  private final XSSFWorkbook workbook;

  private XSSFWorkbookWrapper(XSSFWorkbook workbook) {
    this.workbook = workbook;
  }

  public static XSSFWorkbookWrapper parse(XSSFWorkbook workbook) {
    return new XSSFWorkbookWrapper(workbook);
  }

  public SheetWrapper sheet(int i) {
    return new SheetWrapper(workbook.getSheetAt(i));
  }

  public SheetWrapper sheet(String name) {
    return new SheetWrapper(workbook.getSheet(name));
  }

  public String language() {
    return workbook.getProperties().getCoreProperties().getUnderlyingProperties().getLanguageProperty().get();
  }
}

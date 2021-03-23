package com.docutools.poipath.xssf;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.Optional;

public record XSSFWorkbookWrapper(XSSFWorkbook workbook, String language) {

  public XSSFWorkbookWrapper(XSSFWorkbook workbook) {
    this(workbook, findWorkbookLanguage(workbook).orElse(null));
  }

  public SheetWrapper sheet(int i) {
    return new SheetWrapper(workbook.getSheetAt(i));
  }

  public SheetWrapper sheet(String name) {
    return new SheetWrapper(workbook.getSheet(name));
  }

  public int numberOfSheets() {
    return workbook.getNumberOfSheets();
  }

  private static Optional<String> findWorkbookLanguage(XSSFWorkbook workbook) {
    return workbook.getProperties()
            .getCoreProperties()
            .getUnderlyingProperties()
            .getLanguageProperty();
  }

}

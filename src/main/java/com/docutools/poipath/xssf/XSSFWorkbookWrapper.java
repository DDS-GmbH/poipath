package com.docutools.poipath.xssf;

import com.docutools.poipath.POIUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public record XSSFWorkbookWrapper(XSSFWorkbook workbook, String language) {

  public XSSFWorkbookWrapper(XSSFWorkbook workbook) {
    this(workbook, POIUtils.findLanguage(workbook).orElse(null));
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

}

package com.docutools.poipath.xssf;

import com.docutools.poipath.PoiUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public record XSSFWorkbookWrapper(XSSFWorkbook workbook, String language) {

  public XSSFWorkbookWrapper(XSSFWorkbook workbook) {
    this(workbook, PoiUtils.findLanguage(workbook).orElse(null));
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

  public static XSSFWorkbookWrapper wrap(XSSFWorkbook workbook) {
    return new XSSFWorkbookWrapper(workbook);
  }

}

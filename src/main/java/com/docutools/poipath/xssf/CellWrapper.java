package com.docutools.poipath.xssf;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;

public record CellWrapper(XSSFCell cell) {

  public CellType cellType() {
    return cell.getCellType();
  }

  /**
   * Returns the text content of the cell.
   *
   * @return the text content of the cell
   */
  public String text() {
    return switch (cell.getCellType()) {
      case _NONE, BLANK -> "";
      case NUMERIC -> String.valueOf(cell.getNumericCellValue());
      case STRING -> cell.getStringCellValue();
      case FORMULA -> cell.getCellFormula();
      case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
      case ERROR -> cell.getErrorCellString();
    };
  }

  public String stringValue() {
    return cell.getStringCellValue();
  }

  public boolean booleanValue() {
    return cell.getBooleanCellValue();
  }

  public double doubleValue() {
    return cell.getNumericCellValue();
  }

  public float floatValue() {
    return (float) doubleValue();
  }

  public long longValue() {
    return Math.round(doubleValue());
  }

  public int intValue() {
    return Math.round(floatValue());
  }

  public LocalDate localDate() {
    var date = cell.getDateCellValue();
    return LocalDate.ofInstant(date.toInstant(), ZoneId.systemDefault());
  }

  public LocalDateTime localDateTime() {
    var date = cell.getDateCellValue();
    return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
  }

  public ZonedDateTime zonedDateTime() {
    var date = cell.getDateCellValue();
    return ZonedDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
  }
}

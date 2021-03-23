package com.docutools.poipath.xssf;

import com.docutools.poipath.Workbooks;
import org.apache.poi.ss.usermodel.CellType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@DisplayName("Excel Tests")
class XSSFTests {

  @Test
  void queryNumberOfSheets() throws IOException {
    try(var workbook = Workbooks.resource("/XSSF/multipleSheets.xlsx")) {
      assertThat(new XSSFWorkbookWrapper(workbook).numberOfSheets(), is(2));
    }
  }

  @Test
  void getSheetByIndex() throws IOException {
    try(var workbook = Workbooks.resource("/XSSF/multipleSheets.xlsx")) {
      assertThat(new XSSFWorkbookWrapper(workbook).sheet(0).sheetName(), equalTo("Sheet1"));
    }
  }

  @Test
  void getCellText() throws IOException {
    try (var workbook = Workbooks.resource("/XSSF/simpleTest.xlsx")) {
      assertThat(new XSSFWorkbookWrapper(workbook).sheet("OG").row(0).cell(0).text(), equalTo("No Peace"));
    }
  }

  @Test
  void getNumericCellValues() throws IOException {
    try (var workbook = Workbooks.resource("/XSSF/numberTest.xlsx")) {
      var sheet = new XSSFWorkbookWrapper(workbook).sheet("OG");

      assertThat(sheet.row(0).cell(1).floatValue(), is(12.45f));
      assertThat(sheet.row(1).cell(1).doubleValue(), is(300000.000006));
      assertThat(sheet.row(2).cell(1).intValue(), is(1312));
      assertThat(sheet.row(3).cell(1).longValue(), is(9875438234L));
    }
  }

  @Test
  void getDateTimeValues() throws IOException {
    try (var workbook = Workbooks.resource("/XSSF/dates.xlsx")) {
      var sheet = new XSSFWorkbookWrapper(workbook).sheet(0);

      assertThat(sheet.row(0).cell(1).localDate(), equalTo(LocalDate.of(2021, 12, 13)));
      assertThat(sheet.row(1).cell(1).localDateTime(), equalTo(LocalDateTime.of(2021, 12, 13, 13, 12, 21)));
      assertThat(sheet.row(1).cell(1).zonedDateTime(), equalTo(ZonedDateTime.of(2021, 12, 13, 13, 12, 21, 0, ZoneId.systemDefault())));
    }
  }

  @Test
  void getBooleanValue() throws IOException {
    try (var workbook = Workbooks.resource("/XSSF/cellTypes.xlsx")) {
      var sheet = new XSSFWorkbookWrapper(workbook).sheet(0);

      assertThat(sheet.row(3).cell(1).booleanValue(), is(true));
    }
  }

  @Test
  void getFormula() throws IOException {
    try (var workbook = Workbooks.resource("/XSSF/cellTypes.xlsx")) {
      var sheet = new XSSFWorkbookWrapper(workbook).sheet(0);

      assertThat(sheet.row(5).cell(1).text(), equalTo("1+1"));
    }
  }

  @Test
  void getFormulaResultAsNumeric() throws IOException {
    try (var workbook = Workbooks.resource("/XSSF/cellTypes.xlsx")) {
      var sheet = new XSSFWorkbookWrapper(workbook).sheet(0);

      assertThat(sheet.row(5).cell(1).intValue(), is(2));
    }
  }

  @Test
  void getFormulaResultAsBoolean() throws IOException {
    try (var workbook = Workbooks.resource("/XSSF/cellTypes.xlsx")) {
      var sheet = new XSSFWorkbookWrapper(workbook).sheet(0);

      assertThat(sheet.row(6).cell(1).booleanValue(), is(true));
    }
  }

  @Test
  void getFormulaResultAsText() throws IOException {
    try (var workbook = Workbooks.resource("/XSSF/cellTypes.xlsx")) {
      var sheet = new XSSFWorkbookWrapper(workbook).sheet(0);

      assertThat(sheet.row(7).cell(1).stringValue(), equalTo("Hello, World!"));
    }
  }

  @Test
  void getCellTypes() throws IOException {
    try (var workbook = Workbooks.resource("/XSSF/cellTypes.xlsx")) {
      var sheet = new XSSFWorkbookWrapper(workbook).sheet(0);

      assertThat(sheet.row(0).cell(1).cellType(), is(CellType.STRING));
      assertThat(sheet.row(1).cell(1).cellType(), is(CellType.NUMERIC));
      assertThat(sheet.row(2).cell(1).cellType(), is(CellType.NUMERIC));
      assertThat(sheet.row(3).cell(1).cellType(), is(CellType.BOOLEAN));
      assertThat(sheet.row(5).cell(1).cellType(), is(CellType.FORMULA));
    }
  }
}

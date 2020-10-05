package com.docutools.poipath.xssf;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Excel Tests")
class XSSFTests {
  XSSFWorkbook workbook;

  @AfterEach
  void cleanup() throws IOException {
    if (workbook != null) {
      workbook.close();
    }
  }

  @Test
  void simpleNavigationTest() throws IOException {
    workbook = new XSSFWorkbook(XSSFTests.class.getResourceAsStream("/XSSF/simpleTest.xlsx"));
    var text = XSSFWorkbookWrapper.parse(workbook).sheet("OG").row(0).cell(0).content();

    assertThat(text, equalTo("No Peace"));
  }

  @Test
  void doubleTest() throws IOException {
    workbook = new XSSFWorkbook(XSSFTests.class.getResourceAsStream("/XSSF/numberTest.xlsx"));
    var cell = XSSFWorkbookWrapper.parse(workbook).sheet("OG").row(0).cell(0);
    var stringContent = cell.content();
    var doubleContent = cell.doubleValue();

    assertThat(stringContent, equalTo("1312.0"));
    assertThat(doubleContent, closeTo(1312.0, 0.1));
  }

}

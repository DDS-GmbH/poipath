package com.docutools.poipath.xssf;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Objects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.equalTo;

@DisplayName("Excel Tests")
class XSSFTests {

  @Test
  void simpleNavigationTest() throws IOException {
    try (var stream = Objects.requireNonNull(XSSFTests.class.getResourceAsStream("/XSSF/simpleTest.xlsx"))) {
      var workbook = new XSSFWorkbook(stream);
      var text = new XSSFWorkbookWrapper(workbook).sheet("OG").row(0).cell(0).content();

      assertThat(text, equalTo("No Peace"));
    }
  }

  @Test
  void doubleTest() throws IOException {
    try (var stream = Objects.requireNonNull(XSSFTests.class.getResourceAsStream("/XSSF/numberTest.xlsx"))) {
      var workbook = new XSSFWorkbook(stream);
      var cell = new XSSFWorkbookWrapper(workbook).sheet("OG").row(0).cell(0);
      var stringContent = cell.content();
      var doubleContent = cell.doubleValue();

      assertThat(stringContent, equalTo("1312.0"));
      assertThat(doubleContent, closeTo(1312.0, 0.1));
    }
  }

}

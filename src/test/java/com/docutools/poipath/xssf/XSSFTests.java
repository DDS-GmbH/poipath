package com.docutools.poipath.xssf;

import com.docutools.poipath.Workbooks;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.equalTo;

@DisplayName("Excel Tests")
class XSSFTests {

  @Test
  void simpleNavigationTest() throws IOException {
    try (var workbook = Workbooks.resource("/XSSF/simpleTest.xlsx")) {
      var text = new XSSFWorkbookWrapper(workbook).sheet("OG").row(0).cell(0).content();

      assertThat(text, equalTo("No Peace"));
    }
  }

  @Test
  void doubleTest() throws IOException {
    try (var workbook = Workbooks.resource("/XSSF/numberTest.xlsx")) {
      var cell = new XSSFWorkbookWrapper(workbook).sheet("OG").row(0).cell(0);
      var stringContent = cell.content();
      var doubleContent = cell.doubleValue();

      assertThat(stringContent, equalTo("1312.0"));
      assertThat(doubleContent, closeTo(1312.0, 0.1));
    }
  }

}

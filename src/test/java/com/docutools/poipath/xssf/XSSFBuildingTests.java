package com.docutools.poipath.xssf;

import static com.docutools.poipath.xssf.XSSFWorkbookWrapper.wrap;
import static com.docutools.poipath.xssf.XSSFBuilder.buildWorkbook;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import org.apache.poi.ss.usermodel.IndexedColors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("XSSF Building")
public class XSSFBuildingTests {

  @Test
  @DisplayName("Build empty Workbook")
  void buildEmptyWorkbook() {
    // Act
    var workbook = buildWorkbook()
        .build();

    // Assert
    assertThat(workbook, notNullValue());
  }

  @Test
  @DisplayName("Create sheet")
  void createSheet() {
    // Act
    var workbook = buildWorkbook()
        .sheet()
        .end()
        .build();

    // Assert
    assertThat(workbook.getNumberOfSheets(), is(1));
  }

  @Test
  @DisplayName("Create cell")
  void createCell() {
    // Act
    var workbook = buildWorkbook()
        .sheet()
        .row(0)
        .cell(0)
        .value("Hello, World!", String.class)
        .build();

    // Assert
    assertThat(wrap(workbook).sheet(0).row(0).cell(0).text(), equalTo("Hello, World!"));
  }

  @Test
  @DisplayName("Modify cell")
  void modifyCell() {
    // Arrange
    var red = IndexedColors.RED.getIndex();

    // Act
    var workbook = buildWorkbook()
        .sheet()
        .row(0)
        .cell(0)
        .value("Hello, World!", String.class)
        .modify(cell -> {
          var cellStyle = cell.getCellStyle();
          if(cellStyle == null)
            cellStyle = cell.getSheet().getWorkbook().createCellStyle();
          cellStyle.setFillForegroundColor(red);
          cell.setCellStyle(cellStyle);
        })
        .build();

    // Assert
    assertThat(wrap(workbook).sheet(0).row(0).cell(0).cellStyle().getFillForegroundColor(), is(red));
  }
}

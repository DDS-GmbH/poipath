# poipath
> Testing library for [Apache POI](https://poi.apache.org/) documents.

*poipath* let's you easily navigate through [XSSFWorkbook](https://poi.apache.org/apidocs/dev/org/apache/poi/xssf/usermodel/XSSFWorkbook.html)
or [XWPFDocument](https://poi.apache.org/apidocs/dev/org/apache/poi/xwpf/usermodel/XWPFDocument.html)
datastructures from *Apache POI* which can be quite useful in testing.

```xml
<dependency>
  <groupId>com.docu-tools</groupId>
  <artifactId>poipath</artifactId>
  <version>1.0.0</version>
</dependency>
```

Here an example for testing an Excel report:

```java
package myreportingapp;

import java.io.File;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import com.docutools.poipath.PoiPath;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

class TestReports {
  
  @Test
  void testMyReport() throws Exception {
    try(var report = PoiPath.xssf(new XSSFWorkbook(new File("/path/to/file.xlsx")))) {
      
      Assertions.assertEquals(report.sheet("Overview")
          .row(2).cell(3).dateValue(), LocalDate.of(2021, 12, 13));
      
      Assertions.assertEquals(report.sheet("Overview")
          .row(3).cell(3).stringValue(), "Performance Report");
      
    }
  }
  
}
```

## Defining XWPF/XSSF Datastructures

With version `2.0.0` one can now also define XWPF and XSSF data structures:

```java
package myreportingapp;

import static com.docutools.poipath.xssf.XSSFWorkbookWrapper.wrap;
import static com.docutools.poipath.xssf.XSSFBuilder.buildWorkbook;
import static com.docutools.poipath.xwpf.XWPFDocumentWrapper.wrap;
import static com.docutools.poipath.xwpf.XWPFBuilder.buildDocument;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.Objects;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CreateTestDocuments {

  @Test
  @DisplayName("Modify XSSF cell")
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

  @Test
  @DisplayName("Modify XWPF paragraph.")
  void modifyParagraph() {
    // Act
    var document = buildDocument()
        .paragraph()
        .run("Hello, World!")
        .modify(paragraph -> {
          paragraph.setAlignment(ParagraphAlignment.CENTER);
        })
        .end()
        .build();

    // Assert
    assertThat(Objects.requireNonNull(wrap(document).bodyElement(0).asParagraph()).paragraph().getAlignment(), is(ParagraphAlignment.CENTER));
  }
  
}

```

## Feature Overview

### XSSF

- [x] Navigate Sheets, Rows, Cells
- [x] Extract primitively typed values based on cell type (Number, String, Boolean)
- [x] Get raw and executed formulas
- [x] Parse dates
- [ ] Monetary amounts
- [ ] Control elements
- [ ] Pictures or diagrams
- [ ] Header/footer
- [ ] Document/page settings
- [x] Define XSSF Datastructures

### XWPF

- [x] Navigate body elements (paragraphs and tables)
- [x] Extract runs and pictures from paragraphs
- [x] Extract rows and cells from tables
- [x] Easily load full text from paragraphs and table cells
- [ ] Header/footer
- [ ] Document/page settings
- [ ] Footer notes
- [ ] Table of contents
- [x] Define XWPF Datastructures
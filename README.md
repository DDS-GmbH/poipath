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

### XWPF

- [x] Navigate body elements (paragraphs and tables)
- [x] Extract runs and pictures from paragraphs
- [x] Extract rows and cells from tables
- [x] Easily load full text from paragraphs and table cells
- [ ] Header/footer
- [ ] Document/page settings
- [ ] Footer notes
- [ ] Table of contents
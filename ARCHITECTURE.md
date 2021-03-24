# POIPath Architecture

*POIPath*'s intention is to deliver easy means for testing the correctness of generated
`XWPFDocument`s or `XSSFWorkbook`s. It was developed for the [jocument](https://github.com/DDS-GmbH/jocument)
library, which wraps a template engine around Apache POI.

## Design

The design idea is to have wrapper classes (or [Java Records](https://docs.oracle.com/javase/specs/jls/se14/preview/specs/records-jls.html))
on each XWPF/XSS element to quickly navigate through a document structure and parse the
actual content out of it. The following example shows how to access a date cell of an
`XSSWorkbook`:

```java
package org.example.popath;

import java.util.LocalDate;
import com.docutools.poipath.PoiPath;
import com.docutools.poipath.xssf.*;
import import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class PoiPathExample {
  
  public static void main(String[] args) {
    try(var workbook = new XSSFWorkbook(new File(args[0]))) {
      LocalDate date = PoiPath.xssf(workbook)
          .sheet(0)
          .row(0)
          .cell(0)
          .dateValue();
      
      assert date.equals(LocalDate.of(2000, 1, 1));
    }
  }
  
}
```

## Code Map

### [PoiPath](src/main/java/com/docutools/poipath/PoiPath.java)

Entrypoint to load the corresponding wrapper class for the given POI document.

### [com.docutools.poipath.xssf](src/main/java/com/docutools/poipath/xssf)

This package contains all wrapper recrods to navigate through `XSSFWorkbook`s.

### [com.docutools.poipath.xwpf](src/main/java/com/docutools/poipath/xwpf)

This package contains all wrapper records to navigate through `XWPFDocument`s.

## Corss-Cutting Concerns

### Testing

Test files (`.XLSX` or `.DOCX`) are added as resources to [src/test/resources](src/test/resources)
within the corresponding file type folder.

We provide easy resource loading via the utility classes:

* [Documents](src/test/java/com/docutools/poipath/Documents.java)
* [Workbooks](src/test/java/com/docutools/poipath/Workbooks.java)

A typical test case may look like:

```java
package com.docutools.poipath.xssf;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import com.docutools.poipath.Workbooks;

class SomeXssfTests {
  
  @Test
  void someXssfTest() throws IOException {
    try(var workbook = Workbooks.resource("/XSSF/myExcelResource.xlsx")) {
      assert PoiPath.xssf(workbook).numberOfSheets() == 2;
    }
  }
  
}
```
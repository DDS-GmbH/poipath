package com.docutools.poipath.xwpf;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Word Tests")
class XWPFTests {
  XWPFDocument document;

  @AfterEach
  void cleanup() throws IOException {
    if (document != null) {
      document.close();
    }
  }

  @Test
  void simpleNavigationTest() throws IOException {
    document = new XWPFDocument(XWPFTests.class.getResourceAsStream("/XWPF/simpleTest.docx"));
    var text = XWPFDocumentWrapper.parse(document).paragraph(0).text();

    assertThat(text, equalTo("No Justice"));
  }

}
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
    var text = new XWPFDocumentWrapper(document).paragraph(0).text();

    assertThat(text, equalTo("No Justice"));
  }

  @Test
  void getPictureTest() throws IOException {
    document = new XWPFDocument(XWPFTests.class.getResourceAsStream("/XWPF/pictureTest.docx"));
    var picture = new XWPFDocumentWrapper(document).paragraph(0).run(0).pictures();

    assertThat(picture.size(), equalTo(1));
  }

}

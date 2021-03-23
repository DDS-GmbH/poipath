package com.docutools.poipath.xwpf;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Objects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@DisplayName("Word Tests")
class XWPFTests {

  @Test
  void simpleNavigationTest() throws IOException {
    try (var stream = Objects.requireNonNull(XWPFTests.class.getResourceAsStream("/XWPF/simpleTest.docx"))) {
      var document = new XWPFDocument(stream);
      var text = new XWPFDocumentWrapper(document).paragraph(0).text();

      assertThat(text, equalTo("No Justice"));
    }
  }

  @Test
  void getPictureTest() throws IOException {
    try (var stream = Objects.requireNonNull(XWPFTests.class.getResourceAsStream("/XWPF/pictureTest.docx"))) {
      var document = new XWPFDocument(stream);
      var picture = new XWPFDocumentWrapper(document).paragraph(0).run(0).pictures();

      assertThat(picture.size(), equalTo(1));
    }
  }

}

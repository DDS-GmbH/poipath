package com.docutools.poipath.xwpf;

import com.docutools.poipath.Documents;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@DisplayName("Word Tests")
class XWPFTests {

  @Test
  void simpleNavigationTest() throws IOException {
    try (var document = Documents.resource("/XWPF/simpleTest.docx")) {
      var text = new XWPFDocumentWrapper(document).paragraph(0).text();

      assertThat(text, equalTo("No Justice"));
    }
  }

  @Test
  void getPictureTest() throws IOException {
    try (var document = Documents.resource("/XWPF/pictureTest.docx")) {
      var picture = new XWPFDocumentWrapper(document).paragraph(0).run(0).pictures();

      assertThat(picture.size(), equalTo(1));
    }
  }

}

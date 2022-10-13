package com.docutools.poipath.xwpf;

import com.docutools.poipath.Documents;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Objects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@DisplayName("Word Tests")
class XWPFTests {

  @Test
  void findParagraphs() throws IOException {
    try (var document = Documents.resource("/XWPF/paragraphs.docx")) {
      var wrapper = new XWPFDocumentWrapper(document);
      assertThat(Objects.requireNonNull(wrapper.bodyElement(0).asParagraph()).text(), equalTo("ABC"));
      assertThat(Objects.requireNonNull(wrapper.bodyElement(1).asParagraph()).text(), equalTo("DEF"));
    }
  }

  @Test
  void findRun() throws IOException {
    try (var document = Documents.resource("/XWPF/mixed.docx")) {
      var wrapper = new XWPFDocumentWrapper(document);

      var paragraph = Objects.requireNonNull(wrapper.bodyElement(0).asParagraph());
      assertThat(paragraph.run(2).text(), equalTo("Hello"));
    }
  }

  @Test
  void findTables() throws IOException {
    try (var document = Documents.resource("/XWPF/mixed.docx")) {
      var wrapper = new XWPFDocumentWrapper(document);
      var table = wrapper.bodyElement(1).asTable();
      assertThat(table, notNullValue());
      assertThat(table.numberOfRows(), is(2));
      assertThat(table.row(0).cell(0).text(), equalTo("A"));
    }
  }

  @Test
  void findPictures() throws IOException {
    try (var document = Documents.resource("/XWPF/pictureTest.docx")) {
      var picture = Objects.requireNonNull(new XWPFDocumentWrapper(document).bodyElement(0).asParagraph()).run(0).pictures();

      assertThat(picture.size(), equalTo(1));
    }
  }

}

package com.docutools.poipath.xwpf;

import static com.docutools.poipath.xwpf.XWPFDocumentWrapper.wrap;
import static com.docutools.poipath.xwpf.XWPFBuilder.buildDocument;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.startsWith;

import java.util.Objects;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("XWPF Building")
class XWPFBuildingTests {

  @Test
  @DisplayName("Create empty document.")
  void createEmptyDocument() {
    // Act
    var document = buildDocument()
        .build();

    // Assert
    assertThat(document, is(notNullValue()));
    assertThat(document.getBodyElements(), is(empty()));
  }

  @Test
  @DisplayName("Add a paragraph.")
  void addParagraph() {
    // Arrange
    var text = "Hello, World!";

    // Act
    var document = buildDocument()
        .paragraph()
          .run(text)
        .end()
        .build();

    // Assert
    assertThat(Objects.requireNonNull(wrap(document).bodyElement(0).asParagraph()).text(), equalTo(text));
  }

  @Test
  @DisplayName("Modify paragraph.")
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

  @Test
  @DisplayName("Add multile paragraphs at once.")
  void addMultipleParagraphs() {
    // Arrange
    var text = """
        Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
        Tellus id interdum velit laoreet id donec ultrices tincidunt arcu.""";

    // Act
    var document = buildDocument()
        .paragraph(text)
        .build();

    // Assert
    assertThat(Objects.requireNonNull(wrap(document).bodyElement(0).asParagraph()).text(), startsWith("Lorem ipsum"));
    assertThat(Objects.requireNonNull(wrap(document).bodyElement(0).asParagraph()).text(), endsWith("arcu."));
  }

  @Test
  @DisplayName("Create table.")
  void createTable() {
    // Act
    var document = buildDocument()
        .table(2, 2)
          .row(0)
            .cell(0).text("Name").end()
            .cell(1).text("Age").end()
          .end()
          .row(1)
            .cell(0).text("Alex").end()
            .cell(1).text("28").end()
          .end()
        .build();

    // Assert
    var tableWrapper = wrap(document).bodyElement(0).asTable();
    assertThat(tableWrapper, notNullValue());
    var headerRow = tableWrapper.row(0);
    assertThat(headerRow, notNullValue());
    assertThat(headerRow.cell(0).text(), equalTo("Name"));
    assertThat(headerRow.cell(1).text(), equalTo("Age"));
    var infoRow = tableWrapper.row(1);
    assertThat(infoRow, notNullValue());
    assertThat(infoRow.cell(0).text(), equalTo("Alex"));
    assertThat(infoRow.cell(1).text(), equalTo("28"));
  }

}

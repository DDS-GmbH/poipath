package com.docutools.poipath.xwpf;

import java.util.function.Consumer;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

/**
 * Provides a simple builder pattern for defining XWPF data structures.
 *
 * @param <T> the XWPF type {@link XWPFParagraph}, {@link XWPFRun}, {@link XWPFTable}, ...
 */
public record XWPFBuilder<T>(XWPFBuilder<?> parent, T bodyElement) {

  /**
   * Applies a given function on the XWPF body element.
   *
   * @return {@code this}
   */
  public XWPFBuilder<T> modify(Consumer<T> modifier) {
    modifier.accept(bodyElement);
    return this;
  }

  /**
   * Creates a new {@link XWPFParagraph}.
   *
   * @return the {@link XWPFBuilder} for {@link XWPFParagraph}
   */
  public XWPFBuilder<XWPFParagraph> paragraph() {
    if (bodyElement instanceof XWPFDocument document) {
      return new XWPFBuilder<>(this, document.createParagraph());
    }
    if (bodyElement instanceof XWPFTableCell cell) {
      return new XWPFBuilder<>(this, cell.addParagraph());
    }
    throw isUnsupported(XWPFParagraph.class);
  }

  /**
   * Creates a new {@link XWPFParagraph} with the given text.
   *
   * @param text paragraph content
   * @return {@code this}
   */
  public XWPFBuilder<T> paragraph(String text) {
    XWPFParagraph paragraph;
    if (bodyElement instanceof XWPFDocument document) {
      paragraph = document.createParagraph();
    } else if (bodyElement instanceof XWPFTableCell cell) {
      paragraph = cell.addParagraph();
    } else {
      throw isUnsupported(XWPFParagraph.class);
    }
    paragraph.createRun().setText(text);
    return this;
  }

  /**
   * Creates a new {@link XWPFRun}.
   *
   * @return the {@link XWPFBuilder} for {@link XWPFRun}
   */
  public XWPFBuilder<XWPFRun> run() {
    if (bodyElement instanceof XWPFParagraph paragraph) {
      return new XWPFBuilder<>(this, paragraph.createRun());
    }
    throw isUnsupported(XWPFRun.class);
  }

  /**
   * Creates a new {@link XWPFRun} with the given text.
   *
   * @param text runs content
   * @return {@code this}
   */
  public XWPFBuilder<T> run(String text) {
    if (bodyElement instanceof XWPFParagraph) {
      run().text(text);
    } else {
      throw isUnsupported(XWPFRun.class);
    }
    return this;
  }

  /**
   * Creates a new {@link XWPFTable}.
   *
   * @param rows # of rows
   * @param cols # of cols
   * @return the {@link XWPFBuilder} for {@link XWPFTable}
   */
  public XWPFBuilder<XWPFTable> table(int rows, int cols) {
    if (bodyElement instanceof XWPFDocument document) {
      return new XWPFBuilder<>(this, document.createTable(rows, cols));
    }
    throw isUnsupported(XWPFTable.class);
  }

  /**
   * Creates a new {@link XWPFTableRow}.
   *
   * @return the {@link XWPFBuilder} for {@link XWPFTableRow}
   */
  public XWPFBuilder<XWPFTableRow> row() {
    if (bodyElement instanceof XWPFTable table) {
      var row = table.createRow();
      table.addRow(row);
      return new XWPFBuilder<>(this, row);
    }
    throw isUnsupported(XWPFTableRow.class);
  }

  /**
   * Gets the {@link XWPFTableRow} at an index.
   *
   * @param pos row index
   * @return the {@link XWPFBuilder} for {@link XWPFTableRow}
   */
  public XWPFBuilder<XWPFTableRow> row(int pos) {
    if (bodyElement instanceof XWPFTable table) {
      return new XWPFBuilder<>(this, table.getRow(pos));
    }
    throw isUnsupported(XWPFTableRow.class);
  }

  /**
   * Gets the {@link XWPFTableCell} at an index.
   *
   * @param pos row index
   * @return the {@link XWPFBuilder} for {@link XWPFTableCell}
   */
  public XWPFBuilder<XWPFTableCell> cell(int pos) {
    if (bodyElement instanceof XWPFTableRow row) {
      return new XWPFBuilder<>(this, row.getCell(pos));
    }
    throw isUnsupported(XWPFTableCell.class);
  }

  /**
   * Inserts a text into the given {@link IBodyElement}.
   *
   * @param text the text
   * @return {@code this}
   */
  public XWPFBuilder<T> text(String text) {
    if (bodyElement instanceof XWPFRun run) {
      run.setText(text);
    } else if (bodyElement instanceof XWPFParagraph) {
      run(text);
    } else if (bodyElement instanceof XWPFTableCell cell) {
      cell.getParagraphs()
          .stream()
          .findFirst()
          .ifPresentOrElse(paragraph -> paragraph.createRun().setText(text),
              () -> cell.addParagraph().createRun().setText(text));
    } else {
      throw isUnsupported(String.class);
    }
    return this;
  }

  /**
   * Returns the {@link XWPFBuilder} for the XWPF element a hierarchy level above.
   *
   * @return {@link XWPFBuilder} or {@code null} when it's a {@link XWPFDocument}
   */
  public XWPFBuilder<?> end() {
    return parent;
  }

  private RuntimeException isUnsupported(Class<?> unsupportedType) {
    return new UnsupportedOperationException("Cannot add <%s> to <%s>".formatted(unsupportedType.getName(), bodyElement.getClass().getName()));
  }

  /**
   * Gets the final {@link XWPFBuilder}.
   *
   * @return {@link XWPFDocument}
   */
  public XWPFDocument build() {
    if (bodyElement instanceof XWPFDocument document) {
      return document;
    } else if (bodyElement instanceof IBodyElement element) {
      return element.getBody().getXWPFDocument();
    }
    throw new UnsupportedOperationException("Cannot get XWPFDocument from <%s>, call .end() to hit an upper hierarchy.".formatted(bodyElement));
  }

  /**
   * Initializes a new {@link XWPFBuilder} for an empty {@link XWPFDocument}.
   *
   * @return {@link XWPFBuilder}
   */
  public static XWPFBuilder<XWPFDocument> buildDocument() {
    return new XWPFBuilder<>(null, new XWPFDocument());
  }

}

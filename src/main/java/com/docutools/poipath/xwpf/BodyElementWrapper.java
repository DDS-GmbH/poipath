package com.docutools.poipath.xwpf;

import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFTable;

public record BodyElementWrapper(IBodyElement bodyElement) {

  /**
   * Tries to convert the {@link IBodyElement} to a {@link XWPFParagraph}.
   *
   * @return the {@link XWPFParagraph} or {@code null}
   */
  public ParagraphWrapper asParagraph() {
    if (bodyElement instanceof XWPFParagraph paragraph) {
      return new ParagraphWrapper(paragraph);
    }
    return null;
  }

  /**
   * Tries to convert the {@link IBodyElement} to a {@link XWPFTable}.
   *
   * @return the {@link XWPFTable} or {@code null}
   */
  public TableWrapper asTable() {
    if (bodyElement instanceof XWPFTable table) {
      return new TableWrapper(table);
    }
    return null;
  }

  /**
   * Tests whether this {@link IBodyElement} is a {@link XWPFParagraph}.
   *
   * @return {@code true} when it's a {@link XWPFParagraph}
   */
  public boolean isParagraph() {
    return bodyElement instanceof XWPFParagraph;
  }

  /**
   * Tests whether this {@link IBodyElement} is a {@link XWPFTable}.
   *
   * @return {@code true} when it's a {@link XWPFTable}
   */
  public boolean isTable() {
    return bodyElement instanceof XWPFTable;
  }
}

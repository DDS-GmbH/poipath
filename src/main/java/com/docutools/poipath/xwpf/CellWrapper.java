package com.docutools.poipath.xwpf;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;

public record CellWrapper(XWPFTableCell cell) {

  /**
   * Returns all {@link org.apache.poi.xwpf.usermodel.IBodyElement}s wrapped of this {@link XWPFTableCell}.
   *
   * @return the list of {@link BodyElementWrapper}s
   */
  public List<BodyElementWrapper> bodyElements() {
    return cell.getBodyElements().stream()
        .map(BodyElementWrapper::new)
        .collect(Collectors.toList());
  }

  /**
   * Returns the {@link BodyElementWrapper} of the {@link org.apache.poi.xwpf.usermodel.IBodyElement} on the given position.
   *
   * @param index index of the element
   * @return {@link BodyElementWrapper}
   */
  public BodyElementWrapper bodyElement(int index) {
    return new BodyElementWrapper(cell.getBodyElements().get(index));
  }

  /**
   * Joins the text of each {@link org.apache.poi.xwpf.usermodel.XWPFParagraph} in the body of
   * this {@link XWPFTableCell} to one string, concatenated by a {@code "\n"}.
   *
   * @return the cells text
   */
  public String text() {
    return bodyElements().stream()
        .map(BodyElementWrapper::asParagraph)
        .filter(Objects::nonNull)
        .map(ParagraphWrapper::text)
        .collect(Collectors.joining("\n"));
  }

}

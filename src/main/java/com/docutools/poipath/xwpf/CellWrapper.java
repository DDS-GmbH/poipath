package com.docutools.poipath.xwpf;

import org.apache.poi.xwpf.usermodel.XWPFTableCell;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public record CellWrapper(XWPFTableCell cell) {

  public List<BodyElementWrapper> bodyElements() {
    return cell.getBodyElements().stream()
            .map(BodyElementWrapper::new)
            .collect(Collectors.toList());
  }

  public BodyElementWrapper bodyElement(int index) {
    return new BodyElementWrapper(cell.getBodyElements().get(index));
  }

  public String text() {
    return bodyElements().stream()
            .map(BodyElementWrapper::asParagraph)
            .filter(Objects::nonNull)
            .map(ParagraphWrapper::text)
            .collect(Collectors.joining("\n"));
  }

}

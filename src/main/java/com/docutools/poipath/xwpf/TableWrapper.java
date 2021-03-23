package com.docutools.poipath.xwpf;

import java.util.List;
import java.util.stream.Collectors;
import org.apache.poi.xwpf.usermodel.XWPFTable;

public record TableWrapper(XWPFTable table) {

  public int numberOfRows() {
    return table.getRows().size();
  }

  /**
   * Get the {@link RowWrapper}s of this {@link XWPFTable}.
   *
   * @return {@link RowWrapper}s
   */
  public List<RowWrapper> rows() {
    return table.getRows().stream()
        .map(RowWrapper::new)
        .collect(Collectors.toList());
  }

  public RowWrapper row(int i) {
    return new RowWrapper(table.getRow(i));
  }
}

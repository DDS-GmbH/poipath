package com.docutools.poipath.xwpf;

import java.util.List;
import java.util.stream.Collectors;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

public record RowWrapper(XWPFTableRow row) {

  public int numberOfCells() {
    return row.getTableCells().size();
  }

  /**
   * Returns the {@link org.apache.poi.xwpf.usermodel.XWPFTableCell}s in the row.
   *
   * @return the cells
   */
  public List<CellWrapper> cells() {
    return row.getTableCells().stream()
        .map(CellWrapper::new)
        .collect(Collectors.toList());
  }

  public CellWrapper cell(int index) {
    return new CellWrapper(row.getCell(index));
  }
}

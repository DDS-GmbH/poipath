package com.docutools.poipath.xwpf;

import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.List;
import java.util.stream.Collectors;

public record RowWrapper(XWPFTableRow row) {

  public int numberOfCells() {
    return row.getTableCells().size();
  }

  public List<CellWrapper> cells() {
    return row.getTableCells().stream()
            .map(CellWrapper::new)
            .collect(Collectors.toList());
  }

  public CellWrapper cell(int index) {
    return new CellWrapper(row.getCell(index));
  }
}

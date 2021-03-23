package com.docutools.poipath.xwpf;

import org.apache.poi.xwpf.usermodel.XWPFTable;

import java.util.List;
import java.util.stream.Collectors;

public record TableWrapper(XWPFTable table) {

  public int numberOfRows() {
    return table.getRows().size();
  }

  public List<RowWrapper> rows() {
    return table.getRows().stream()
            .map(RowWrapper::new)
            .collect(Collectors.toList());
  }

  public RowWrapper row(int i) {
    return new RowWrapper(table.getRow(i));
  }
}

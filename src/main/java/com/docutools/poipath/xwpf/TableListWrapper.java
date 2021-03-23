package com.docutools.poipath.xwpf;

import java.util.List;
import org.apache.poi.xwpf.usermodel.XWPFTable;

public record TableListWrapper(List<XWPFTable> tables) {

  public TableWrapper table(int i) {
    return new TableWrapper(tables.get(i));
  }

  public int size() {
    return tables.size();
  }
}

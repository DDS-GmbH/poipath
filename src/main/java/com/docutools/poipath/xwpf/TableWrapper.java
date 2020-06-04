package com.docutools.poipath.xwpf;

import org.apache.poi.xwpf.usermodel.XWPFTable;

public class TableWrapper {
    private final XWPFTable table;

    public TableWrapper(XWPFTable table) {
        this.table = table;
    }

    public int numberRows() {
        return table.getRows().size();
    }

    public RowListWrapper rows() {
        return new RowListWrapper(table.getRows());
    }

    public RowWrapper row(int i) {
        return new RowWrapper(table.getRow(i));
    }
}

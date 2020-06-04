package com.docutools.poipath.xwpf;

import org.apache.poi.xwpf.usermodel.XWPFTable;

import java.util.List;

public class TableListWrapper {
    private final List<XWPFTable> tables;

    public TableListWrapper(List<XWPFTable> tables) {
        this.tables = tables;
    }

    public TableWrapper table(int i) {
        return new TableWrapper(tables.get(i));
    }

    public int size() {
        return tables.size();
    }
}

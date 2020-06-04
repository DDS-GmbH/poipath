package com.docutools.poipath.xwpf;

import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.List;

public class RowListWrapper {
    private final List<XWPFTableRow> rows;

    public RowListWrapper(List<XWPFTableRow> rows) {
        this.rows = rows;
    }
}

package com.docutools.poipath.xssf;

import org.apache.poi.xssf.usermodel.XSSFSheet;

public class SheetWrapper {
    private final XSSFSheet sheet;

    public SheetWrapper(XSSFSheet sheet) {
        this.sheet = sheet;
    }

    public RowWrapper row(int i) {
        return new RowWrapper(sheet.getRow(i));
    }
}

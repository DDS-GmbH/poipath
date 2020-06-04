package com.docutools.poipath.xssf;

import org.apache.poi.xssf.usermodel.XSSFCell;

public class CellWrapper {
    private final XSSFCell cell;

    public CellWrapper(XSSFCell cell) {
        this.cell = cell;
    }

    public String text() {
        return cell.getStringCellValue();
    }
}

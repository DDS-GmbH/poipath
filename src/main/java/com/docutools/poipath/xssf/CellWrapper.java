package com.docutools.poipath.xssf;

import org.apache.poi.xssf.usermodel.XSSFCell;

public class CellWrapper {
    private final XSSFCell cell;

    public CellWrapper(XSSFCell cell) {
        this.cell = cell;
    }

    public String content() {
        return switch (cell.getCellType()) {
            case _NONE -> "";
            case NUMERIC -> String.valueOf(cell.getNumericCellValue());
            case STRING -> cell.getStringCellValue();
            case FORMULA -> cell.getCellFormula();
            case BLANK -> "";
            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
            case ERROR -> cell.getErrorCellString();
        };
    }
}

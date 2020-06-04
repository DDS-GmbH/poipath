package com.docutools.poipath.xwpf;

import org.apache.poi.xwpf.usermodel.XWPFTableCell;

public class CellWrapper {
    private final XWPFTableCell cell;

    public CellWrapper(XWPFTableCell cell) {
        this.cell = cell;
    }

    public ParagraphListWrapper paragraphs() {
        return new ParagraphListWrapper(cell.getParagraphs());
    }

    public ParagraphWrapper paragraph(int i) {
        return new ParagraphWrapper(cell.getParagraphArray(i));
    }
}

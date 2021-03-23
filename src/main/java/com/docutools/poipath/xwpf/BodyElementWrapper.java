package com.docutools.poipath.xwpf;

import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFTable;

public record BodyElementWrapper(IBodyElement bodyElement) {

    public ParagraphWrapper asParagraph() {
        if(bodyElement instanceof XWPFParagraph paragraph) {
            return new ParagraphWrapper(paragraph);
        }
        return null;
    }

    public TableWrapper asTable() {
        if(bodyElement instanceof XWPFTable table) {
            return new TableWrapper(table);
        }
        return null;
    }

    public boolean isParagraph() {
        return bodyElement instanceof XWPFParagraph;
    }

    public boolean isTable() {
        return bodyElement instanceof XWPFTable;
    }
}

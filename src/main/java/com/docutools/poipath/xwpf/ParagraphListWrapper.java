package com.docutools.poipath.xwpf;

import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.util.List;

public class ParagraphListWrapper {
    private final List<XWPFParagraph> paragraphs;

    public ParagraphListWrapper(List<XWPFParagraph> paragraphs) {
        this.paragraphs = paragraphs;
    }

    public ParagraphWrapper get(int i) {
        return new ParagraphWrapper(paragraphs.get(i));
    }

    public int length() {
        return paragraphs.size();
    }
}

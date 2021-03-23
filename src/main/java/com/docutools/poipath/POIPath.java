package com.docutools.poipath;

import com.docutools.poipath.xssf.XSSFWorkbookWrapper;
import com.docutools.poipath.xwpf.XWPFDocumentWrapper;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

/**
 * Interface to load corresponding POIPath wrapper classes for XSSF or XWPF files.
 *
 * @since 1.0.0
 */
public class POIPath {

    /**
     * Creates a new {@link XSSFWorkbookWrapper} for the given {@link XSSFWorkbook}.
     *
     * @param workbook the {@link XSSFWorkbook}
     * @return the {@link XSSFWorkbookWrapper}
     */
    public static XSSFWorkbookWrapper xssf(XSSFWorkbook workbook) {
        return new XSSFWorkbookWrapper(workbook);
    }

    /**
     * Creates a new {@link XWPFDocumentWrapper} for a {@link XWPFDocument}.
     *
     * @param document the {@link XWPFDocument}
     * @return the {@link XWPFDocumentWrapper}
     */
    public static XWPFDocumentWrapper xwpf(XWPFDocument document) {
        return new XWPFDocumentWrapper(document);
    }

    private POIPath() {
    }

}

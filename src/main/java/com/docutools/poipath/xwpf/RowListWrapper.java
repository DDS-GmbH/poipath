package com.docutools.poipath.xwpf;

import java.util.List;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

public record RowListWrapper(List<XWPFTableRow> rows) {
}

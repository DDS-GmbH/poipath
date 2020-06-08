package com.docutools.poipath.xssf;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@DisplayName("Excel Tests")
class XSSFTests {

    @Test
    void simpleNavigationTest() throws IOException {
        var workbook = new XSSFWorkbook(XSSFTests.class.getResourceAsStream("/XSSF/simpleTest.xlsx"));
        var text = XSSFWorkbookWrapper.parse(workbook).sheet("OG").row(0).cell(0).content();

        assertThat(text, equalTo("No Peace"));
    }

}

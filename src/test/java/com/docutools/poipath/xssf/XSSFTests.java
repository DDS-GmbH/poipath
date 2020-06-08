package com.docutools.poipath.xssf;

import com.docutools.poipath.PoipathApplication;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest
class XSSFTests {

    @Test
    void simpleNavigationTest() throws IOException {
        var workbook = new XSSFWorkbook(PoipathApplication.class.getResourceAsStream("/XSSF/simpleTest.xlsx"));
        var text = XSSFWorkbookWrapper.parse(workbook).sheet("OG").row(0).cell(0).text();

        assertThat(text, equalTo("No Peace"));
    }

}

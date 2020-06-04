package com.docutools.poipath;

import com.docutools.poipath.xwpf.XWPFDocumentWrapper;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PoipathApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void simpleNavigationTest() throws IOException {
        var document = new XWPFDocument(PoipathApplication.class.getResourceAsStream("/word/simpleTest.docx"));
        var text = XWPFDocumentWrapper.parse(document).paragraph(0).text();

        assertEquals("No justice", text);
    }

}

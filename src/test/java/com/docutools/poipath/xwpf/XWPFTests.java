package com.docutools.poipath.xwpf;

import com.docutools.poipath.PoipathApplication;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest
class XWPFTests {

    @Test
    void simpleNavigationTest() throws IOException {
        var document = new XWPFDocument(PoipathApplication.class.getResourceAsStream("/XWPF/simpleTest.docx"));
        var text = XWPFDocumentWrapper.parse(document).paragraph(0).text();

        assertThat(text, equalTo("No Justice"));
    }

}

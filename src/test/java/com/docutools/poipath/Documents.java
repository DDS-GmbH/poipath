package com.docutools.poipath;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.IOException;
import java.util.Objects;

/**
 * Utility class for loading {@link XWPFDocument}s.
 *
 * @since 1.0.0
 */
public class Documents {

    /**
     * Loads an {@link XWPFDocument} from the test resource file with the given name.
     *
     * @param resourceName the name/path to the resource file
     * @return the {@link XWPFDocument}
     * @throws IOException when an IO Error occurs while reading the resource.
     * @throws NullPointerException when the resourceName is NULL or no resource with the given name exists.
     */
    public static XWPFDocument resource(String resourceName) throws IOException {
        return new XWPFDocument(Objects.requireNonNull(Documents.class.getResourceAsStream(resourceName)));
    }

    private Documents() {
    }

}

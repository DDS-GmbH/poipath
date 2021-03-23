package com.docutools.poipath;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.util.Objects;

/**
 * Utility class for loading test {@link XSSFWorkbook}s.
 *
 * @since 1.0.0
 */
public class Workbooks {

    /**
     * Loads an {@link XSSFWorkbook} from the test resource file with the given name.
     *
     * @param resourceName the name/path to the resource file
     * @return the {@link XSSFWorkbook}
     * @throws IOException when an IO Error occurs while reading the resource.
     * @throws NullPointerException when the resourceName is NULL or no resource with the given name exists.
     */
    public static XSSFWorkbook resource(String resourceName) throws IOException {
        return new XSSFWorkbook(Objects.requireNonNull(
                Workbooks.class.getResourceAsStream(resourceName)));
    }

    private Workbooks() {
    }

}

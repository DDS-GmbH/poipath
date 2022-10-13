package com.docutools.poipath.xssf;

import java.lang.reflect.InvocationTargetException;
import java.util.function.Consumer;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Provides a simple builder pattern for defining XSSF data structures.
 *
 * @param <T> the XSSF type {@link XSSFWorkbook}, {@link XSSFSheet}, {@link XSSFRow}, ...
 */
public record XSSFBuilder<T>(XSSFBuilder<?> parent, T bodyElement) {

  /**
   * Gets the final {@link XSSFWorkbook}.
   *
   * @return {@link XSSFWorkbook}
   */
  public XSSFWorkbook build() {
    if (bodyElement instanceof XSSFWorkbook workbook) {
      return workbook;
    } else if (bodyElement instanceof XSSFSheet sheet) {
      return sheet.getWorkbook();
    } else if (bodyElement instanceof XSSFRow row) {
      return row.getSheet().getWorkbook();
    } else if (bodyElement instanceof XSSFCell cell) {
      return cell.getSheet().getWorkbook();
    }
    throw new UnsupportedOperationException("Cannot get XSSFWorkbook from <%s>, call .end() to hit an upper hierarchy.".formatted(bodyElement));
  }

  /**
   * Initializes a new {@link XSSFBuilder} for an empty {@link XSSFWorkbook}.
   *
   * @return {@link XSSFBuilder}
   */
  public static XSSFBuilder<XSSFWorkbook> buildWorkbook() {
    var workbook = new XSSFWorkbook();
    return new XSSFBuilder<>(null, workbook);
  }

  /**
   * Returns the {@link XSSFBuilder} for the XSSF element a hierarchy level above.
   *
   * @return {@link XSSFBuilder} or {@code null} when it's a {@link XSSFWorkbook}
   */
  public XSSFBuilder<?> end() {
    return parent;
  }

  /**
   * Applies a given function on the XSSF element.
   *
   * @return {@code this}
   */
  public XSSFBuilder<T> modify(Consumer<T> consumer) {
    consumer.accept(bodyElement);
    return this;
  }

  /**
   * Creates a new {@link XSSFSheet} in the {@link XSSFWorkbook}.
   *
   * @return {@link XSSFBuilder} for {@link XSSFSheet}
   */
  public XSSFBuilder<XSSFSheet> sheet() {
    if (bodyElement instanceof XSSFWorkbook workbook) {
      return new XSSFBuilder<>(this, workbook.createSheet());
    }
    throw isUnsupported(XSSFSheet.class);
  }

  /**
   * Creates a new {@link XSSFRow}.
   *
   * @param number the row number, starting at 0
   * @return the {@link XSSFBuilder} for {@link XSSFRow}
   */
  public XSSFBuilder<XSSFRow> row(int number) {
    if (bodyElement instanceof XSSFSheet sheet) {
      return new XSSFBuilder<>(this, sheet.createRow(number));
    }
    throw isUnsupported(XSSFRow.class);
  }

  /**
   * Creates a new {@link XSSFCell}.
   *
   * @param index the cell index, starting at 0
   * @return the {@link XSSFBuilder} for {@link XSSFCell}
   */
  public XSSFBuilder<XSSFCell> cell(int index) {
    if (bodyElement instanceof XSSFRow row) {
      return new XSSFBuilder<>(this, row.createCell(index));
    }
    throw isUnsupported(XSSFCell.class);
  }

  /**
   * Tries to insert the given value into the {@link XSSFCell}.
   *
   * @param content the cell value
   * @param clazz cell value Java type
   * @param <V> cell value java type
   * @return {@code this}
   */
  public <V> XSSFBuilder<T> value(V content, Class<V> clazz) {
    if (bodyElement instanceof XSSFCell cell) {
      try {
        var method = XSSFCell.class.getMethod("setCellValue", clazz);
        method.invoke(cell, content);
        return this;
      } catch (NoSuchMethodException ignored) {
        throw isUnsupported(clazz);
      } catch (InvocationTargetException | IllegalAccessException e) {
        throw new UnsupportedOperationException("Ran into Exception when calling setCellValue(%s)".formatted(clazz), e);
      }
    }
    throw isUnsupported(String.class);
  }

  private RuntimeException isUnsupported(Class<?> unsupportedType) {
    return new UnsupportedOperationException("Cannot add <%s> to <%s>".formatted(unsupportedType.getName(), bodyElement.getClass().getName()));
  }
}

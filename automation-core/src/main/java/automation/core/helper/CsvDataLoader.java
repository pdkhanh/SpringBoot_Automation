package automation.core.helper;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class CsvDataLoader {
  private static final Logger logger = LoggerFactory.getLogger(CsvDataLoader.class);
  private static final CsvMapper csvMapper = new CsvMapper();

  private CsvDataLoader() {
  }

  public static <T> List<T> loadData(Class<T> dataType, String dataClasspath) {
    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    InputStream inputStream = classLoader.getResourceAsStream(dataClasspath);
    CsvSchema csvSchema = CsvSchema
        .emptySchema()
        .withColumnSeparator('\t')
        .withHeader();

    try {
      MappingIterator<T> it = csvMapper
          .readerFor(dataType)
          .with(csvSchema)
          .readValues(inputStream);

      return it.readAll();
    } catch (IOException ex) {
      logger.error(ex.getMessage(), ex.getCause());
      throw new KatException(ex);
    }
  }
}

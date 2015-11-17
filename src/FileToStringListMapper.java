import org.easybatch.core.record.GenericRecord;
import org.easybatch.core.record.Record;
import org.easybatch.core.mapper.RecordMapper;
import org.easybatch.core.record.StringRecord;

import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 * Created by rdarge on 8/11/2015.
 */
public class FileToStringListMapper implements RecordMapper<StringRecord, Record<List<String>>> {

   public Record<List<String>> processRecord(StringRecord record) {
      String payload = record.getPayload();
      payload = payload.replaceAll(" [ ]*", " ");
      return new GenericRecord<>(record.getHeader(), Arrays.asList(payload.split(" ")));
   }

}

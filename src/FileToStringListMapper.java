import org.easybatch.core.api.Record;
import org.easybatch.core.api.RecordMapper;

import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 * Created by rdarge on 8/11/2015.
 */
public class FileToStringListMapper implements RecordMapper<List<String>> {

   public List<String> mapRecord(Record record) {
      String payload = (String) record.getPayload();
      payload = payload.replaceAll(" [ ]*", " ");
      return Arrays.asList(payload.split(" "));
   }

}

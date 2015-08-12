import org.easybatch.core.api.Record;
import org.easybatch.core.api.RecordMapper;

import java.util.Arrays;
import java.util.List;

/**
 * Created by rdarge on 8/11/2015.
 */
public class PieTokenizer implements RecordMapper<List<String>> {

   public List<String> mapRecord(Record record) {
      String payload = (String) record.getPayload();
      return Arrays.asList(payload.split(" "));
   }

}

import org.easybatch.core.processor.ComputationalRecordProcessor;
import org.easybatch.core.record.Record;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by copypasta on 8/11/2015.
 */
public class ComputeDictionaryProcess implements
      ComputationalRecordProcessor<Record<List<String>>, Record<List<String>>, Map<WordPair, List<String>>> {

   private Map<WordPair, List<String>> words = new HashMap<>();
   private List<String> inputStory = new ArrayList<>();

   public Map<WordPair, List<String>> getComputationResult() {
      return words;
   }

   public Record<List<String>> processRecord(Record<List<String>> record) {
      List<String> tokens = record.getPayload();
      inputStory.addAll(tokens);

      WordPair pair = null;
      String nextWord = null;

      //Add the WordPairs for this part of the story
      while (inputStory.size() >= 3) {
         pair = new WordPair(inputStory.get(0), inputStory.get(1));
         nextWord = inputStory.get(2);
         associateWordPair(pair, nextWord);

         //Remove the first word from the list
         System.out.print(inputStory.get(0) + " ");
         inputStory.remove(0);
      }

      return record;
   }

   private void associateWordPair(WordPair pair, String nextWord) {
      if (words.containsKey(pair)){
         words.get(pair).add(nextWord);
      } else {
         List newList = new ArrayList<String>();
         newList.add(nextWord);
         words.put(pair, newList);
      }
   }
}

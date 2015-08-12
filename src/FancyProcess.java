import org.easybatch.core.api.ComputationalRecordProcessor;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by copypasta on 8/11/2015.
 */
public class FancyProcess implements
      ComputationalRecordProcessor<List<String>, List<String>, List<String>> {

   private Map<WordPair, List<String>> words = new HashMap<>();
   private List<String> output;

   public List<String> getComputationResult() {
      return output;
   }

   public List<String> processRecord(List<String> tokens) {

      for (int num = 0; num < tokens.size()-3; num++) {
         WordPair pair = new WordPair(tokens.get(num), tokens.get(num + 1));
         String nextWord = tokens.get(num + 2);
         if (words.containsKey(pair)){
            words.get(pair).add(nextWord);
         } else {
            List newList = new ArrayList<String>();
            newList.add(nextWord);
            words.put(pair, newList);
         }

//         Integer count = words.get(token);
//         count = (count == null) ? 1 : count + 1;
//         words.put(token, count);
      }

      Random gen = new Random();

      ArrayList<WordPair> startingPoint = words.keySet().stream().collect(Collectors.toCollection(ArrayList::new));
      WordPair startingPair = startingPoint.get(gen.nextInt(startingPoint.size()));

      output = new ArrayList<String>();
      output.add(startingPair.toString());
      WordPair nextPair = startingPair;
      while(words.containsKey(nextPair)) {
         //Put this in a function
         List<String> nextWordOptions = words.get(nextPair);
         String nextWord = nextWordOptions.get(gen.nextInt(nextWordOptions.size()));
         nextPair = new WordPair(nextPair.getTwo(),nextWord);
         output.add(nextWord);
         if(output.size() > 1000) break;
      }
      return tokens;
   }
}
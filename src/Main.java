import org.easybatch.core.job.Job;
import org.easybatch.core.job.JobExecutor;
import org.easybatch.core.job.JobReport;
import org.easybatch.core.job.JobBuilder;
import org.easybatch.flatfile.FlatFileRecordReader;
//import java.io.File;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public class Main {

    static Random gen = new Random();

    public static void main(String[] args) {
        try {
        File inputFile = new File("res/lastQuestion.txt");
            Job job = JobBuilder.aNewJob()
                    .reader(new FlatFileRecordReader(inputFile))
                    .mapper(new FileToStringListMapper())
                    .processor(new ComputeDictionaryProcess())
                    .build();
            JobReport report = JobExecutor.execute(job);
            Map<WordPair, List<String>> dictionary = (Map<WordPair, List<String>>) report.getResult();
            String newStory = generateNewBook(dictionary);
            System.out.println(newStory);
            System.out.println("Thanks for playing!");
        } catch (Exception e) {};
    }

    public static String generateNewBook(Map<WordPair, List<String>> words){

        ArrayList<WordPair> startingList = words.keySet().stream().collect(Collectors.toCollection(ArrayList::new));
        WordPair startingPair = startingList.get(gen.nextInt(startingList.size()));

        String output = startingPair.toString();
        WordPair nextPair = startingPair;

        while(words.containsKey(nextPair)) {

            //Put this in a function
            List<String> nextWordOptions = words.get(nextPair);
            String nextWord = nextWordOptions.get(gen.nextInt(nextWordOptions.size()));
            nextPair = new WordPair(nextPair.getTwo(),nextWord);
            output = output + " " + nextWord;
            if(output.length() > 100000) break;

        }

        return output;
    }

    public static String flatten(ArrayList<String> story) {
        return story.stream()
                .map(Object::toString)
                .reduce((t,u) -> t + " " + u)
                .orElse("");
    }


}

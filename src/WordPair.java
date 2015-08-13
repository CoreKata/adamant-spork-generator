import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;


/**
 * Created by rdarge on 8/11/2015.
 */
public class WordPair  {
   private final String one;

   public String getTwo() {
      return two;
   }

   public String getOne() {
      return one;
   }

   private final String two;

   public WordPair(String a, String b){
      one = a;
      two = b;
   }

   public String toString(){
      return one + " " + two;
   }

   @Override
   public int hashCode(){
      return new HashCodeBuilder(524287,2147483647). // two randomly chosen prime numbers
            // if deriving: appendSuper(super.hashCode()).
            append(one).
            append(two).
            toHashCode();
   }

   @Override
   public boolean equals(Object obj){
      if (!(obj instanceof WordPair))
         return false;
      if (obj == this)
         return true;

      WordPair wp = (WordPair) obj;
      return new EqualsBuilder().
            // if deriving: appendSuper(super.equals(obj)).
                  append(one, wp.one).
            append(two, wp.two).
            isEquals();
   }


}

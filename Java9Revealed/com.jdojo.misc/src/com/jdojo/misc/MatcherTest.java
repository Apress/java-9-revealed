// MatcherTest.java
package com.jdojo.misc;

import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

public class MatcherTest {
    public static void main(String[] args) {
        // A regex to match 7-digit or 10-digit phone numbers
        String regex = "\\b(\\d{3})?(\\d{3})(\\d{4})\\b";
        
        // An input string
        String input = "1, 3342229999, 2330001, 6159996666, 123, 3340909090";

        // Create a matcher
        Matcher matcher = Pattern.compile(regex)
                                  .matcher(input);

        // Collect formatted phone numbers into a list
        List<String> phones = matcher.results()
                          .map(mr -> (mr.group(1) == null ? "" : "(" + mr.group(1) + ") ")
                                      + mr.group(2) + "-" + mr.group(3))
                          .collect(toList());
        System.out.println("Phones: " + phones);
        
        // Reset the matcher, so we can resuse it from start
        matcher.reset();
        
        // Get distinct area codes
        Set<String> areaCodes = matcher.results()
                                       .filter(mr -> mr.group(1) != null)
                                       .map(mr -> mr.group(1))
                                       .collect(toSet());
        System.out.println("Distinct Area Codes:: " + areaCodes);                
    }
}

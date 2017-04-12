// VersionTest.java
package com.jdojo.version.string;

import java.util.List;
import java.lang.Runtime.Version;

public class VersionTest {
    public static void main(String[] args) {
        String[] versionStrings = {
            "9", "9.1", "9.1.2", "9.1.2.3.4", "9.0.0",
            "9.1.2-ea+153", "9+132", "9-ea+132-2016-08-23", "9+-123",
            "9.0.1-ea+132-2016-08-22.10.56.45am"};

        for (String versonString : versionStrings) {
            try {
                Version version = Version.parse(versonString);

                // Get the additional version number elements
                // which starts at 4th element
                String vnumAdditionalInfo = getAdditionalVersionInfo(version);

                System.out.printf("Version String=%s%n", versonString);
                System.out.printf("Major=%d, Minor=%d, Security=%d, Additional Version=%s,"
                        + " Pre=%s, Build=%s, Optional=%s %n%n",
                        version.major(),
                        version.minor(),
                        version.security(),
                        vnumAdditionalInfo,
                        version.pre().orElse(""),
                        version.build().isPresent() ? version.build().get().toString() : "",
                        version.optional().orElse(""));
            } catch (Exception e) {
                System.out.printf("%s%n%n", e.getMessage());
            }
        }
    }

    // Returns the version number elements from the 4th elements to the end      
    public static String getAdditionalVersionInfo(Version v) {
        String str = "";

        List<Integer> vnum = v.version();
        int size = vnum.size();
        if (size >= 4) {
            str = str + String.valueOf(vnum.get(3));
        }

        for (int i = 4; i < size; i++) {
            str = str + "." + String.valueOf(vnum.get(i));
        }

        return str;
    }
}

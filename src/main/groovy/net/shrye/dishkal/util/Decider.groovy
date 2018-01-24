package net.shrye.dishkal.util

import java.util.regex.Matcher

import static java.time.LocalDateTime.now
import static java.time.format.DateTimeFormatter.ofPattern

class Decider {
    static String decide(String input, String identity) {
        Matcher r = (input =~ /(".+?"|(?<!").+?(?!"))(?:\s+|$)/)
        long c = asciicount(input) + asciicount(now().format(ofPattern('yyyy/MM/dd')))
        if (r.size() > 2) {
            return "für " + r[(c + safeMod(asciicount(identity), 100)) % r.size() as int][0]
        }

        String adv = (safeMod(c, safeMod(asciicount(identity), 100)) % 2 + 1) == 1 ? 'für ' : 'gegen '
        return adv + r[0][0]
    }

    static private int asciicount(String t) {
        return Arrays.stream(t.toCharArray()).mapToInt({ i -> (int) i }).sum()
    }

    static private long safeMod(long a, long b) {
        if (b == 0) return 0
        return a % b
    }
}

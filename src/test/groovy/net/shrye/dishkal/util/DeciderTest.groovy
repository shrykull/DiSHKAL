package net.shrye.dishkal.util

import spock.lang.Specification
import spock.lang.Unroll

class DeciderTest extends Specification {
    def "decide dafür/dagegen bei single match"() {
        def identity1 = '@npx#5201'
        def identity2 = '@someone#124'

        def question = 'etwas'

        expect:
        Decider.decide(question, identity1) == Decider.decide(question, identity1)
        Decider.decide(question, identity1) != Decider.decide(question, identity2)
        Decider.decide(question, identity2) == Decider.decide(question, identity2)

        "gegen $question" == (Decider.decide(question, identity1)) || "für $question" == (Decider.decide(question, identity1))
        "gegen $question" == (Decider.decide(question, identity2)) || "für $question" == (Decider.decide(question, identity2))
    }

    @Unroll
    def "!decide #question"(String question) {
        def identity1 = '@npx#5201'
        def identity2 = '@someone#123'

        expect:
        Decider.decide(question, identity1) == Decider.decide(question, identity1)
        Decider.decide(question, identity1) != Decider.decide(question, identity2)
        Decider.decide(question, identity2) == Decider.decide(question, identity2)

        //assert no lists were accidentially toString()ed
        !Decider.decide(question, identity1).contains('[')
        !Decider.decide(question, identity2).contains('[')

        where:
        question << [
                '"bla fasel" "blubb" "mehr bla"',
                '"bla fasel" blubb "mehr bla"',
                '"bla fasel" "blubb" mehr bla',
                'bla fasel "blubb" "mehr blubb"',
                '"bla fasel blubb mehr bla'
        ]
    }
}

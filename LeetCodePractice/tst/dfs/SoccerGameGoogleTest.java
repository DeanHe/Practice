package dfs;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

/*
plan football game match, given a country to teams mapping:
e.g. {'Italy': [c1, c2], 'Spain': [c3], 'France': [c4, c5, c6]

and a list of previously happened matches:
[c3, c6], [c4, c2], [c5, c1]
 */
public class SoccerGameGoogleTest {
    private SoccerGameGoogle sg;

    @Before
    public void setup() {
        sg = new SoccerGameGoogle();
    }

    @Test
    public void testCase1(){
        Map<String, List<String>> countries = new HashMap<>();
        countries.computeIfAbsent("Italy", x -> new ArrayList<>()).add("c1");
        countries.computeIfAbsent("Italy", x -> new ArrayList<>()).add("c2");
        countries.computeIfAbsent("Spain", x -> new ArrayList<>()).add("c3");
        countries.computeIfAbsent("France", x -> new ArrayList<>()).add("c4");
        countries.computeIfAbsent("France", x -> new ArrayList<>()).add("c5");
        countries.computeIfAbsent("France", x -> new ArrayList<>()).add("c6");

        String[][] happenedMatches = {
                {"c3", "c6"},
                {"c4", "c2"},
                {"c5", "c1"}
        };
        String[][] res = sg.planMatches(countries, happenedMatches);
        for(String[] pair : res){
            System.out.println(Arrays.toString(pair));
        }
    }
}

package britionary.common;

import java.util.HashSet;

/**
 * Luokkaa käytetään testien kirjoittamisen apuna.
 */
public class Sets {

    /**
     * Metodi tallentaa tunnetut biscuit-synonyymit HashSet:iin.
     *
     * @return  HashSet synonyymeistä
     */
    public static HashSet<RegionalWord> newBiscuitSet() {
        HashSet<RegionalWord> synonymSet = new HashSet<>();

        synonymSet.add(new RegionalWord("", "cookie"));
        synonymSet.add(new RegionalWord("", "bicky"));
        synonymSet.add(new RegionalWord("", "cracker"));
        synonymSet.add(new RegionalWord("", "wafer"));

        return synonymSet;
    }

    /**
     * Metodi tallentaa tunnetut leisurely-synonyymit HashSet:iin.
     *
     * @return  HashSet synonyymeistä
     */
    public static HashSet<RegionalWord> newLeisurelySet() {
        HashSet<RegionalWord> synonymSet = new HashSet<>();

        synonymSet.add(new RegionalWord("", "unhurried"));
        synonymSet.add(new RegionalWord("", "unrushed"));
        synonymSet.add(new RegionalWord("", "comfortable"));
        synonymSet.add(new RegionalWord("", "slow"));
        synonymSet.add(new RegionalWord("", "unhurried"));
        synonymSet.add(new RegionalWord("", "gentle"));
        synonymSet.add(new RegionalWord("", "easy-going"));
        synonymSet.add(new RegionalWord("", "sedate"));
        synonymSet.add(new RegionalWord("", "steady"));
        synonymSet.add(new RegionalWord("", "lackadaisical"));
        synonymSet.add(new RegionalWord("", "measured"));
        synonymSet.add(new RegionalWord("", "restful"));
        synonymSet.add(new RegionalWord("", "effortless"));
        synonymSet.add(new RegionalWord("", "languorous"));
        synonymSet.add(new RegionalWord("", "languid"));
        synonymSet.add(new RegionalWord("", "lazy"));
        synonymSet.add(new RegionalWord("", "easy"));
        synonymSet.add(new RegionalWord("", "laid-back"));
        synonymSet.add(new RegionalWord("", "relaxed"));
        synonymSet.add(new RegionalWord("", "undemanding"));
        synonymSet.add(new RegionalWord("", "lingering"));

        return synonymSet;
    }
    
}

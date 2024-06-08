/*
 * Copyright (c) Bia
 */

package models;

import java.util.Comparator;

public class HypeTierComparator implements Comparator<HypeTier>{
    public int compare(HypeTier h1, HypeTier h2){
        //megahype este cel mai inalt nivel care poate fi asociat unui eveniment
        if ((h1.getType().equals("megahype")) && (h2.getType().equals("hype") || h2.getType().equals("cool") || h2.getType().equals("nice")))
            return -1;
        else if ((h1.getType().equals("hype")) && (h2.getType().equals("cool") || h2.getType().equals("nice")))
            return -1;
        else if ((h1.getType().equals("cool")) && (h2.getType().equals("nice")))
            return -1;
        else if ((h2.getType().equals("megahype")) && (h1.getType().equals("hype") || h1.getType().equals("cool")|| h1.getType().equals("nice")))
            return 1;
        else if((h2.getType().equals("hype")) && (h1.getType().equals("cool") || h1.getType().equals("nice")))
            return 1;
        else if((h2.getType().equals("cool")) && (h1.getType().equals("nice")))
            return 1;
        else return 0;
    }
}

package gmu.cs.cs477.tanibon.classes;

import gmu.cs.cs477.tanibon.R;

public class WillowTanibon extends Tanibon {
    public WillowTanibon(String name){
        species = "Willow";
        this.name = name;
        description = "Has long leaves with serrated edges. Likes to cuddle!";
        age = 0;
        hatchTime = 5;
        growthTime = hatchTime*2;
    }

}

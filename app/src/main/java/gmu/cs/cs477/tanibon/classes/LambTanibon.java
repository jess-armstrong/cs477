package gmu.cs.cs477.tanibon.classes;


public class LambTanibon extends Tanibon {
    public LambTanibon(String name){
        species = "Lamb";
        this.name = name;
        description = "Soft, wooly, and will care for your minor injuries! Likes arid regions.";
        age = 0;
        hatchTime = 7;
        growthTime = hatchTime*2;
    }
}

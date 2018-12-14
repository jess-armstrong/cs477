package gmu.cs.cs477.tanibon.classes;

public abstract class Tanibon {
    String species, name, description;
    int age, hatchTime, growthTime;

    public Tanibon(){
        this.species = "none";
        this.name  = species;
        this.description = "none";
        this.age = -1;
        this.hatchTime = -1;
        this.growthTime = -1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public long getHatchTime() {
        return hatchTime;
    }

    public void setHatchTime(int hatchTime) {
        this.hatchTime = hatchTime;
    }

    public long getGrowthTime() {
        return growthTime;
    }

    public void setGrowthTime(int growthTime) {
        this.growthTime = growthTime;
    }
}


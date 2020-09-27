package pojo;

public class Dish {
    private Integer weight;
    private String name;

    public Dish() {
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Dish(Integer weight, String name) {
        this.weight = weight;
        this.name = name;
    }
}

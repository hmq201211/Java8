package pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Dish {
    public enum Type {MEAT, FISH, OTHER}

    private final String name;
    private final boolean vegetarian;
    private final int calories;
    private final Type type;
}

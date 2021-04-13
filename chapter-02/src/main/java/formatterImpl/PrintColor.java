package formatterImpl;

import formatter.AppleFormat;
import pojo.Apple;

public class PrintColor implements AppleFormat {
    @Override
    public String accept(Apple apple) {
        return apple.getColor();
    }
}

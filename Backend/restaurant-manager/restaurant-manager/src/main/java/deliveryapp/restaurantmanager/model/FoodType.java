package deliveryapp.restaurantmanager.model;

import java.util.HashSet;
import java.util.Set;

public enum FoodType {

    PIZZA,
    BURGER,
    TACOS,
    DRINKS,
    SNACK,
    DISH,
    DESERT,
    SALAD,
    FISH;


    private final static Set<String> values = new HashSet<>(FoodType.values().length);

    static{
        for(FoodType r: FoodType.values())
            values.add(r.name());
    }

    public static boolean contains( String value ){
        return values.contains(value);
    }



}

package deliveryapp.restaurantmanager.model;

import java.util.HashSet;
import java.util.Set;

public enum DishType {
    PIZZA,
    BURGER,
    TACOS,
    DRINKS,
    SNACK,
    DISH,
    DESERT,
    SALAD,
    FISH;

    private final static Set<String> values = new HashSet<>(DishType.values().length);

    static{
        for(DishType r: DishType.values())
            values.add(r.name());
    }

    public static boolean contains( String value ){
        return values.contains(value);
    }



    }

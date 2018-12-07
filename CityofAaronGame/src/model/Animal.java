package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Amber Mitchell
 */
public class Animal extends InventoryItem implements Serializable {

    private ArrayList<Integer> ages = new ArrayList<>();

    public Animal() {
        // Empty constructor for bean
    }

    public Animal(String aName, ItemType aType, int aQuantity, Condition aCondition, ArrayList<Integer> aAges) {
        setName(aName);
        setType(aType);
        setQuantity(aQuantity);
        setCondition(aCondition);
        setAge(aAges);
    }

    public ArrayList<Integer> getAge() {
        return ages;
    }

    public void setAge(ArrayList<Integer> ages) {
        this.ages = ages;
    }

    // toString method
    @Override
    public String toString() {
        return "\n{ Animal: "
                + name + ", "
                + ages + ", "
                + type + ", "
                + quantity + ", "
                + condition + " }";
    }
}

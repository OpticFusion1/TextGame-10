package model;
import java.io.Serializable;

/**
 *
 * @author Amber Mitchell
 */

public class InventoryItem implements Serializable {
    protected String name;
    protected ItemType type;
    protected int quantity;
    protected Condition condition;

    public InventoryItem() {
        // Empty constructor for bean
    }

    public InventoryItem(String aName, ItemType aType, int aQuantity, Condition aCondition) {
        setName(aName);
        setType(aType);
        setQuantity(aQuantity);
        setCondition(aCondition);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }
    
    // toString method
    @Override
    public String toString() {
        return "\n{ Inventory Item: "
               + name + ", "
               + type + ", "
               + quantity + ", "
               + condition + " }";
    }

}

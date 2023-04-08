package Model;

import java.util.ArrayList;

public abstract class AbstractConnectionModel<T>{
    private ArrayList<Object> items = new ArrayList<>();
    private ArrayList<Object> itemsNeedUpdate = new ArrayList<>();

    @SuppressWarnings("unchecked")
    public <T> ArrayList<T> getItems() {
        return (ArrayList<T>) items;
    }

    public void updateItem(T original, T modified) {
        itemsNeedUpdate.add(modified);
    }

    @SuppressWarnings("unchecked")
    public <T>void setItems(ArrayList<T> items) {
        this.items = (ArrayList<Object>) items;
    }

    @SuppressWarnings("unchecked")
    public <T> T get(int index) {
        return (T) items.get(index);
    }

    @SuppressWarnings("unchecked")
    private <T> T getItemNeedUpdate(int index) {
        return (T) itemsNeedUpdate.get(index);
    }

    public <T>void add(T item) {
        items.add(item);
        itemsNeedUpdate.add(item);
    }

    public abstract void updateHandler(T item);

    public void update() {
        for (int i = 0; i < itemsNeedUpdate.size(); i++) {
            updateHandler(getItemNeedUpdate(i));
        }
        itemsNeedUpdate.clear();
    }


}

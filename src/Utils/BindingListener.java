package Utils;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.function.Consumer;

public class BindingListener<T> implements DocumentListener {
    private final T model;
    private final Consumer<String> setFunction;

    public BindingListener(T model, Consumer<String> setFunction) {
        this.model = model;
        this.setFunction = setFunction;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        dataUpdated(e);
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        dataUpdated(e);
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        dataUpdated(e);
    }

    private void dataUpdated(DocumentEvent e) {
        try {
            String text = e.getDocument().getText(
                    e.getDocument().getStartPosition().getOffset(),
                    e.getDocument().getEndPosition().getOffset() - 1);

            setFunction.accept(text);

        } catch (BadLocationException ex) {
            throw new RuntimeException(ex);
        }
    }


}
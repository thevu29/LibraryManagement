package Utils;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.function.Consumer;
import java.util.regex.Pattern;

public class BindingListener<T> implements DocumentListener {
    private final JTextComponent parent;
    private final T model;
    private final Consumer<String> setFunction;
    private final Pattern pattern;

    public BindingListener(JTextComponent parent, T model, Consumer<String> setFunction) {
        this(parent, model, setFunction, ".*");
    }

    public BindingListener(JTextComponent parent, T model, Consumer<String> setFunction, String validationPattern) {
        this.parent = parent;
        this.model = model;
        this.setFunction = setFunction;
        System.out.println(validationPattern);
        this.pattern = Pattern.compile(validationPattern);
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
            if (text.matches(pattern.pattern())) {
                setFunction.accept(text);
                parent.setBackground(new Color(195, 232, 141));
            }
            else {
                parent.setBackground(new Color(244, 171, 171));
            }


        } catch (BadLocationException ex) {
            throw new RuntimeException(ex);
        }
    }


}
package com.starxg.browserfx;

import com.intellij.openapi.diagnostic.Logger;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.atomic.AtomicInteger;

public class MainPane extends JPanel {

    private ClosableTabbedPane tabbedPane;
    private JButton btnAdd;
    private AtomicInteger tabIndex = new AtomicInteger(1);

    MainPane() {
        try {
            initView();
        } catch (Exception e) {
            Logger.getInstance(MainPane.class).error(e);
        }
    }

    public void initView() {
        setLayout(new BorderLayout());

        tabbedPane = new ClosableTabbedPane().setCloseListener((index) -> {
        });
        btnAdd = new JButton("+");
        btnAdd.setMargin(new Insets(2, 6, 2, 6));
        btnAdd.setFocusable(false);
        btnAdd.setContentAreaFilled(false);
        btnAdd.addActionListener(e -> {
            addTab("  " + tabIndex.getAndIncrement() + "  ");
        });

        JPanel placeholder = new JPanel();
        tabbedPane.insertTab("", null, placeholder, "添加标签", 0);
        tabbedPane.setTabComponentAt(0, btnAdd);

        add(tabbedPane, BorderLayout.CENTER);
    }

    public void initAddButton() {

    }

    public void addTab(String title) {
        try {
            tabbedPane.addTabWithClose(title, new Browser((BrowserView) Class.forName("com.starxg.browserfx.JcefBrowser").newInstance()));
        } catch (Exception e) {
            Logger.getInstance(MainPane.class).error(e);
        }
    }

}

package com.starxg.browserfx;

import com.intellij.openapi.diagnostic.Logger;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.atomic.AtomicInteger;

public class BrowserPane extends JPanel {

    private ClosableTabbedPane pane;
    private JButton btn_add;
    private AtomicInteger tabIndex = new AtomicInteger(1);

    BrowserPane() {
        try {
            initView();
        } catch (Exception e) {
            Logger.getInstance(BrowserWindowFactory.class).error(e);
        }
    }

    public void initView() {
        setLayout(new BorderLayout());

        btn_add = new JButton("+");
        btn_add.addActionListener(e -> {
            addTab(tabIndex.getAndIncrement() + "");
        });
        add(btn_add, BorderLayout.NORTH);

        pane = new ClosableTabbedPane().setCloseListener((index) -> {
        });
        add(pane, BorderLayout.CENTER);
    }

    public void addTab(String title) {
        try {
            pane.addTabWithClose(title, new Browser((BrowserView) Class.forName("com.starxg.browserfx.JcefBrowser").newInstance()));
        } catch (Exception e) {
            Logger.getInstance(BrowserWindowFactory.class).error(e);
        }
    }

}

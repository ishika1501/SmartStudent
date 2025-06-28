package com.ishika.smartstudent;

import com.formdev.flatlaf.FlatLightLaf;               // ✅ Add this
import com.ishika.smartstudent.gui.LoginFrame;

import javax.swing.UIManager;

public class Main {
    public static void main(String[] args) {
        FlatLightLaf.setup();                          // ✅ Setup L&F

        System.out.println(UIManager.getLookAndFeel()); // 🔍 Check it prints FlatLaf
        new LoginFrame();
    }
}

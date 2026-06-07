/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DashboardUIDefault;

import java.awt.Color;

/**
 *
 * @author rafra
 */
import java.awt.Color;

public class Colors {

    // Accent Colors
    public Color PRIMARY_BLUE;
    public Color SUCCESS_GREEN;

    // Main Background
    public Color BACKGROUND;

    // Panels Background
    public Color PANELS_BACKGROUND;

    // Sidebar
    public Color SidePanel;

    // Buttons
    public Color ButtonHighlight;
    public Color CancelButton;

    // Header/Footer
    public Color HeaderFooterColor;

    // Borders
    public Color BORDER_GRAY;

    // Text
    public Color TEXT_GRAY;
    public Color TEXT_WHITE;
    public Color TEXT_BLACK;

    public static Colors LIGHT() {
        Colors c = new Colors();

        c.PRIMARY_BLUE = new Color(82, 124, 174);
        c.SUCCESS_GREEN = new Color(82, 174, 124);

        c.BACKGROUND = new Color(243, 243, 243);
        c.PANELS_BACKGROUND = new Color(243, 243, 243);
        c.SidePanel = new Color(228, 228, 236);
        c.ButtonHighlight = new Color(201, 211, 221);
        c.HeaderFooterColor = new Color(240, 240, 240);
        c.CancelButton = Color.GRAY;

        c.BORDER_GRAY = Color.LIGHT_GRAY;

        c.TEXT_GRAY = Color.GRAY;
        c.TEXT_WHITE = Color.WHITE;
        c.TEXT_BLACK = Color.BLACK;

        return c;
    }

    public static Colors DARK() {
        Colors c = new Colors();

        c.PRIMARY_BLUE = new Color(100, 149, 237);
        c.SUCCESS_GREEN = new Color(76, 175, 80);

        c.BACKGROUND = new Color(30, 30, 30);
        c.PANELS_BACKGROUND = new Color(45, 45, 45);
        c.SidePanel = new Color(40, 40, 40);
        c.ButtonHighlight = new Color(70, 70, 70);
        c.HeaderFooterColor = new Color(45, 45, 45);
        c.CancelButton = Color.GRAY;

        c.BORDER_GRAY = new Color(70, 70, 70);

        c.TEXT_GRAY = new Color(180, 180, 180);
        c.TEXT_WHITE = Color.WHITE;
        c.TEXT_BLACK = Color.WHITE;

        return c;
    }

}

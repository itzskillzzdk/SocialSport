package views.utils;

import modules.donnees.Donnee;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Utils {
    public static ImageIcon getResizeImage(String path, int width, int height) {
        return new ImageIcon(
          new ImageIcon(path).getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH)
        );
    }


}

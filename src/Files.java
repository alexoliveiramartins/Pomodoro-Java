import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Files {
    String soundsFolderPath = "src/Sounds";
    String bgImagesFolderPath = "src/BgImages";
    File soundsFolder;
    File bgImagesFolder;
    File[] sounds;
    File[] images;

    public Files() {
        soundsFolder = new File(soundsFolderPath);
        bgImagesFolder = new File(bgImagesFolderPath);

        sounds = soundsFolder.listFiles();
        images = bgImagesFolder.listFiles();
    }

    public void changeBackground(JPanel panel, String path) {
        if ("Default".equals(path)) {
            panel.setBackground(null); // Remove background
            panel.setOpaque(true); // Make panel opaque again
        } else {
            Image backgroundImage = new ImageIcon("src/BgImages/" + path).getImage();

            // Create a new JPanel to replace the original one
            JPanel newPanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            };
            newPanel.setOpaque(false);
            newPanel.setLayout(panel.getLayout());

            // Transfer all components from the original panel to the new panel
            Component[] components = panel.getComponents();
            for (Component component : components) {
                panel.remove(component);
                newPanel.add(component);
            }

            // Replace the original panel with the new panel in its parent container
            Container parent = panel.getParent();
            if (parent != null) {
                int index = -1;
                for (int i = 0; i < parent.getComponentCount(); i++) {
                    if (parent.getComponent(i) == panel) {
                        index = i;
                        break;
                    }
                }
                parent.remove(panel);
                parent.add(newPanel, index);
                parent.revalidate();
                parent.repaint();
            }
        }
    }

    public void addSoundsComboBox(JComboBox<String> box) {
        for (File a : sounds) {
            box.addItem(a.getName());
        }
    }

    public void addImagesComboBox(JComboBox<String> box) {
        box.addItem("Default");
        for (File a : images) {
            box.addItem(a.getName());
        }
    }
}
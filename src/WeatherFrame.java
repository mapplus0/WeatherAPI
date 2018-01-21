import sun.awt.SunHints;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.util.Random;

public class WeatherFrame extends JFrame {

    private Point mouse;

    private JLabel temperatureLabel;
    private JLabel statusLabel;
    private JLabel windSpeedValLabel;
    private JLabel humidityValLabel;

    public WeatherFrame() throws Exception {
        this.setTitle("Weather");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setUndecorated(true);

        Font font = Font.createFont(Font.TRUETYPE_FONT, WeatherFrame.class.getResourceAsStream("segoeuil.ttf"));

        this.setContentPane(new JLabel(new ImageIcon(ImageIO.read(WeatherFrame.class.getResourceAsStream("bg.png")))));
        this.getContentPane().setMinimumSize(new Dimension(720, 720));
        this.getContentPane().setMaximumSize(new Dimension(720, 720));
        this.getContentPane().setPreferredSize(new Dimension(720, 720));
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);

        BufferedImage top = ImageIO.read(WeatherFrame.class.getResourceAsStream("top.png"));
        Graphics2D topg2d = (Graphics2D) top.getGraphics();
        topg2d.setRenderingHint(SunHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        topg2d.setColor(Color.WHITE);
        topg2d.setFont(font.deriveFont(22f));
        topg2d.drawString(this.getTitle(), 50, 28);

        JLabel topLabel = new JLabel(new ImageIcon(top));
        topLabel.setSize(720,40);
        topLabel.setLocation(0,0);
        this.mouse = null;
        topLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mouse = new Point(e.getX(), e.getY());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                mouse = null;
            }
        });
        topLabel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (mouse == null) return;
                setLocation(e.getXOnScreen()-mouse.x, e.getYOnScreen()-mouse.y);
            }
        });
        this.getContentPane().add(topLabel);

        JButton exitButton = new JButton("X");
        exitButton.setSize(40,40);
        exitButton.setLocation(680,0);
        exitButton.setOpaque(false);
        exitButton.setForeground(Color.WHITE);
        exitButton.setFont(font.deriveFont(22f));
        exitButton.setFocusPainted(false);
        exitButton.setBorder(null);
        exitButton.setBorderPainted(false);
        exitButton.setContentAreaFilled(false);
        exitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                exitButton.setForeground(Color.CYAN);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                exitButton.setForeground(Color.WHITE);
            }
        });
        topLabel.add(exitButton);

        JLabel cityLabel = new JLabel("City:");
        cityLabel.setSize(540,25);
        cityLabel.setLocation(10,50);
        cityLabel.setOpaque(false);
        cityLabel.setForeground(Color.WHITE);
        cityLabel.setFont(font.deriveFont(20f));
        this.getContentPane().add(cityLabel);

        JTextField cityField = new JTextField();
        cityField.setSize(540,40);
        cityField.setLocation(10,80);
        cityField.setOpaque(false);
        cityField.setForeground(Color.WHITE);
        cityField.setBorder(new MatteBorder(0,0,1,0,Color.WHITE));
        cityField.setFont(font.deriveFont(20f));
        cityField.setCaretColor(Color.WHITE);
        cityField.setSelectionColor(new Color(0,100,195));
        cityField.setSelectedTextColor(Color.WHITE);
        this.getContentPane().add(cityField);

        JLabel countryLabel = new JLabel("Country code:");
        countryLabel.setSize(150,40);
        countryLabel.setLocation(560,50);
        countryLabel.setOpaque(false);
        countryLabel.setForeground(Color.WHITE);
        countryLabel.setFont(font.deriveFont(20f));
        this.getContentPane().add(countryLabel);

        JTextField countryField = new JTextField("SK");
        countryField.setSize(150,40);
        countryField.setLocation(560,80);
        countryField.setOpaque(false);
        countryField.setForeground(Color.WHITE);
        countryField.setBorder(new MatteBorder(0,0,1,0,Color.WHITE));
        countryField.setFont(font.deriveFont(20f));
        countryField.setCaretColor(Color.WHITE);
        countryField.setSelectionColor(new Color(0,100,195));
        countryField.setSelectedTextColor(Color.WHITE);
        this.getContentPane().add(countryField);

        JButton sendButton = new JButton("Search");
        sendButton.setSize(150,40);
        sendButton.setLocation(285,140);
        sendButton.setOpaque(false);
        sendButton.setForeground(Color.WHITE);
        sendButton.setBorder(new MatteBorder(0,0,1,0,Color.WHITE));
        sendButton.setFont(font.deriveFont(20f));
        sendButton.setFocusPainted(false);
        sendButton.setContentAreaFilled(false);
        this.getContentPane().add(sendButton);

        JLabel cityNameLabel = new JLabel("---");
        cityNameLabel.setSize(700,30);
        cityNameLabel.setLocation(10,240);
        cityNameLabel.setOpaque(false);
        cityNameLabel.setForeground(Color.WHITE);
        cityNameLabel.setFont(font.deriveFont(20f));
        cityNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        cityNameLabel.setVerticalAlignment(SwingConstants.CENTER);
        this.getContentPane().add(cityNameLabel);

        this.temperatureLabel = new JLabel("---°C");
        temperatureLabel.setSize(150,60);
        temperatureLabel.setLocation(285,280);
        temperatureLabel.setOpaque(false);
        temperatureLabel.setForeground(Color.WHITE);
        temperatureLabel.setFont(font.deriveFont(48f));
        temperatureLabel.setHorizontalAlignment(SwingConstants.CENTER);
        temperatureLabel.setVerticalAlignment(SwingConstants.CENTER);
        this.getContentPane().add(temperatureLabel);

        sendButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                sendButton.setForeground(Color.CYAN);
                sendButton.setBorder(new MatteBorder(0,0,1,0,Color.CYAN));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                sendButton.setForeground(Color.WHITE);
                sendButton.setBorder(new MatteBorder(0,0,1,0,Color.WHITE));
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                temperatureLabel.setText(new Random().nextInt(30)+"°C");
                cityNameLabel.setText(cityField.getText()+", "+countryField.getText());
            }
        });

        this.statusLabel = new JLabel("---");
        statusLabel.setSize(150,30);
        statusLabel.setLocation(285,350);
        statusLabel.setOpaque(false);
        statusLabel.setForeground(Color.WHITE);
        statusLabel.setFont(font.deriveFont(20f));
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        statusLabel.setVerticalAlignment(SwingConstants.CENTER);
        this.getContentPane().add(statusLabel);

        //DETAILS

        JLabel windSpeedTopLabel = new JLabel("Wind speed");
        windSpeedTopLabel.setSize(340,30);
        windSpeedTopLabel.setLocation(10,420);
        windSpeedTopLabel.setOpaque(false);
        windSpeedTopLabel.setForeground(Color.WHITE);
        windSpeedTopLabel.setFont(font.deriveFont(25f));
        windSpeedTopLabel.setHorizontalAlignment(SwingConstants.CENTER);
        windSpeedTopLabel.setVerticalAlignment(SwingConstants.CENTER);
        this.getContentPane().add(windSpeedTopLabel);

        this.windSpeedValLabel = new JLabel("3.4 m/s");
        windSpeedValLabel.setSize(340,30);
        windSpeedValLabel.setLocation(10,450);
        windSpeedValLabel.setOpaque(false);
        windSpeedValLabel.setForeground(Color.WHITE);
        windSpeedValLabel.setFont(font.deriveFont(20f));
        windSpeedValLabel.setHorizontalAlignment(SwingConstants.CENTER);
        windSpeedValLabel.setVerticalAlignment(SwingConstants.CENTER);
        this.getContentPane().add(windSpeedValLabel);

        JLabel humidityTopLabel = new JLabel("Humidity");
        humidityTopLabel.setSize(340,30);
        humidityTopLabel.setLocation(370,420);
        humidityTopLabel.setOpaque(false);
        humidityTopLabel.setForeground(Color.WHITE);
        humidityTopLabel.setFont(font.deriveFont(25f));
        humidityTopLabel.setHorizontalAlignment(SwingConstants.CENTER);
        humidityTopLabel.setVerticalAlignment(SwingConstants.CENTER);
        this.getContentPane().add(humidityTopLabel);

        this.humidityValLabel = new JLabel("94%");
        humidityValLabel.setSize(340,30);
        humidityValLabel.setLocation(370,450);
        humidityValLabel.setOpaque(false);
        humidityValLabel.setForeground(Color.WHITE);
        humidityValLabel.setFont(font.deriveFont(20f));
        humidityValLabel.setHorizontalAlignment(SwingConstants.CENTER);
        humidityValLabel.setVerticalAlignment(SwingConstants.CENTER);
        this.getContentPane().add(humidityValLabel);
    }

    public void setTemperature(double kelvin) {
        double celsius = kelvin-273.15;
        this.temperatureLabel.setText(celsius+"°C");
    }

    public void setStatus(String status) {
        this.statusLabel.setText(status);
    }

    public void setWindSpeed(double speedms) {
        this.windSpeedValLabel.setText(speedms+" m/s");
    }

    public void setHumidity(int humidity) {
        this.humidityValLabel.setText(humidity+"%");
    }

}

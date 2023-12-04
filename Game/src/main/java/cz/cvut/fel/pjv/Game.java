package cz.cvut.fel.pjv;

import cz.cvut.fel.pjv.Objects.*;
import cz.cvut.fel.pjv.Objects.Weapon.Axe_Weapon;
import cz.cvut.fel.pjv.Objects.Weapon.Spear_Weapon;
import cz.cvut.fel.pjv.Objects.Weapon.Weapons;
import cz.cvut.fel.pjv.framework.*;
import cz.cvut.fel.pjv.framework.Image.IMG;
import cz.cvut.fel.pjv.window.Menu;
import cz.cvut.fel.pjv.window.Window;
import cz.cvut.fel.pjv.window.*;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.util.Objects;

/**
 * This class is used for running the game itself.
 */
public class Game extends Canvas implements Runnable {

    public static int WIDTH = 800, HEIGHT = 600;
    private boolean running = false;
    static BufferedImage level;
    public static Handler handler;
    static Camera camera;
    public static State state = State.Menu;
    public static int numberOfEnemies;
    public static int uroven;
    public static boolean load;
    public IMG loader = new IMG();
    static FileReader file;

    static {
        try {
            file = new FileReader();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public int logger = file.getLogger();


    /**
     * This function is used for checking if the game is running, so it can't be run again.
     */
    public synchronized void start() {
        if (running)
            return;
        running = true;
        Thread thread = new Thread(this);
        thread.start();

    }

    /**
     * This function is used for creating game loop.
     */
    @Override
    public void run() {
        System.out.println("Beginning");
        uroven = 1;
        load = false;

        if (Objects.equals(file.getWeapon(), "Axe")) {
            Player.state = Weapons.Axe;
        }
        if (Objects.equals(file.getWeapon(), "Spear")) {
            Player.state = Weapons.Spear;
        }

        this.addKeyListener(new InputsMenu());
        LoadLevel(uroven);
        this.requestFocus();


        // Game Loop
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                updates++;
                delta--;
            }
            render();
            frames++;
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                if(logger > 0) {
                    System.out.println("FPS: " + frames + " TICKS: " + updates);
                }
                frames = 0;
                updates = 0;
            }
        }
    }

    /**
     * This function is used for ticking every object, so it can be updated.
     */
    private void tick() {

        handler.tick();


        if (load) {
            LoadLevel(uroven);
        }
        if (Game.state == State.Reset) {
            Reset();
            LoadLevel(1);
        }

        for (int i = 0; i < handler.object.size(); i++) {
            GameObject obj = handler.object.get(i);
            if (obj != null) {
                if (obj.getId() == ObjectID.Player) {
                    camera.tick(obj);
                }
                if (numberOfEnemies == 0) {
                    if (uroven == 1) {
                        uroven = 2;
                        load = false;
                        if (Player.Hearts < file.getHearts()/2) {
                            Player.Hearts = (Player.Hearts / 2) + Player.Hearts;
                        }
                        LoadLevel(uroven);
                    } else if (uroven == 2) {
                        Game.state = State.Win;
                    }
                }
            }
        }

    }

    /**
     * This function is used for rendering the Game and triggering all the render functions in another class's.
     */
    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics graphics = bs.getDrawGraphics();
        Graphics2D graphics2D = (Graphics2D) graphics;
        Menu menu = new Menu();
        // Draw from here

        graphics.setColor(Color.gray);
        graphics.fillRect(0, 0, getWidth(), getWidth());

        //Game
        if (state == State.Game) {
            graphics2D.translate(camera.getX(), -camera.getY());
            handler.render(graphics);
            graphics2D.translate(-camera.getX(), -camera.getY());
        } else if (state == State.Menu || state == State.Settings || state == State.GameOver || state == State.Win || state == State.Shop) {
            menu.render(graphics, graphics2D);
        } else if (state == State.Quit) {
            System.exit(1);
        }

        //To here
        graphics.dispose();
        bs.show();
    }


    /**
     * This function is used for loading the saved game
     */
    public static void Load() {
        file.readInventar();
        load = true;
        uroven = file.getUroven();
        if (Objects.equals(file.getWeapon(), "Axe")) {
            Player.state = Weapons.Axe;
        }
        if (Objects.equals(file.getWeapon(), "Spear")) {
            Player.state = Weapons.Spear;
        }
        Player.Hearts = file.getHearts();

    }

    /**
     * This function is used for resting the game in any scenario
     */
    public static void Reset() {
        file.readFile();
        uroven = file.getUroven();
        Player.Speed = file.getSpeed();
        if (Objects.equals(file.getWeapon(), "Axe")) {
            Player.state = Weapons.Axe;
        }
        if (Objects.equals(file.getWeapon(), "Spear")) {
            Player.state = Weapons.Spear;
        }
        Player.Hearts = file.getHearts();
        Game.state = State.Game;

    }


    /**
     * This function is used for loading every GameObject and adding inputs.
     */
    public void LoadLevel(int uroven) {
        if (load) {
            level = loader.loadImage("config/safe.png");
        } else {
            if (uroven == 1) {
                level = loader.loadImage("image/map.png");
            } else if (uroven == 2) {
                level = loader.loadImage("image/map2.png");
            }
        }

        camera = new Camera(0, 0);
        handler = new Handler();
        this.addKeyListener(new Inputs(handler));
        this.addMouseListener(new MouseInput());

        load = false;
        LoadImageLevel(level);

    }

    /**
     * This function is used for loading img a creating maps from it.
     *
     * @param image is the map image.
     */
    private static void LoadImageLevel(BufferedImage image) {
        numberOfEnemies = 0;
        int width = image.getWidth();
        int height = image.getHeight();


        for (int x = 0; x < height; x++) {
            for (int y = 0; y < width; y++) {
                int pixel = image.getRGB(x, y);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;

                if (red == 0 && green == 255 && blue == 255)
                    handler.addObject(new Money(x * 32, y * 32, ObjectID.Money));
                if (red == 255 && green == 255 && blue == 255)
                    handler.addObject(new Block(x * 32, y * 32, ObjectID.Block));
                if (red == 255 && green == 0 && blue == 255)
                    handler.addObject(new Trader(x * 32, y * 32, ObjectID.Trader));
                if (red == 255 && green == 0 && blue == 0) {
                    handler.addObject(new Enemy(x * 32, y * 32, ObjectID.Enemy));
                    numberOfEnemies++;
                }
                if (red == 0 && green == 255 && blue == 0) {
                    handler.addObject(new Spear_Weapon(x * 32, y * 32, 0, handler, ObjectID.Spear));
                }
                if (red == 255 && green == 255 && blue == 0) {
                    handler.addObject(new Axe_Weapon(x * 32, y * 32, 0, handler, ObjectID.Axe));
                }
                if (red == 0 && green == 0 && blue == 255)
                    handler.addObject(new Player(x * 32, y * 32, ObjectID.Player));
            }

        }


    }

    /**
     * This function is used for Starting everything in the Idea.
     *
     * @param args no idea.
     */
    public static void main(String[] args) {
        System.out.println("This is Game Engine");
        new Window(HEIGHT, WIDTH, "Habbis Advanture", new Game());
    }


}

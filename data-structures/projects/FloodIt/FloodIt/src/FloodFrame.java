
import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mvc on 10/23/2015.
 */
public class FloodFrame  extends JFrame{
    public final Driver driver;
    private JPanel tiles_panel;
    private JMenuBar menu_bar = new JMenuBar();
    private JMenu steps = new JMenu();
    public Map<Coord, Tile> coord_of_tiles = new HashMap<>();

    public FloodFrame(final Driver _driver) {
        this.driver = _driver;
        this.tiles_panel = new JPanel(new GridLayout(_driver.size, _driver.size));

        this.init_menu();

        this.setContentPane(this.tiles_panel);
        //this.pack();
        this.setSize(640, 640);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void init_menu() {
        final FloodFrame frame = this;

        final JMenu game_menu = new JMenu("Game");
        game_menu.setMnemonic(KeyEvent.VK_G);
        this.menu_bar.add(game_menu);

        final JMenuItem restart = new JMenuItem("Restart");
        restart.addActionListener(e -> frame.driver.restart());
        game_menu.add(restart);

        final JMenuItem resize = new JMenuItem("Resize");
        resize.addActionListener(e -> {
            final String msg = JOptionPane.showInputDialog("Please type in the size");
            frame.driver.resize(Integer.parseInt(msg));
        });
        game_menu.add(resize);

        final JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(e -> frame.dispose());
        game_menu.add(exit);

        final JMenu help = new JMenu("Help");
        help.setMnemonic(KeyEvent.VK_H);
        this.menu_bar.add(help);

        final JMenuItem how_to_play = new JMenuItem("HowToPlay");
        how_to_play.addActionListener(e -> JOptionPane.showMessageDialog(frame, "At the beginning, the tile at northwestern corner is flooded.\nClicking a tile refines the flooded tiles with the color of the clicked one,\nthen floods all adjacent tiles of flooded tiles that is    in the same color.\nThe user wins when all tiles are flooded,\nand fails when the step limit is reached.\n"));
        help.add(how_to_play);

        this.menu_bar.add(steps);

        this.setJMenuBar(this.menu_bar);
    }

    public void add_tile(final Coord coord, final int color_idx) {
        final Tile tile = new Tile(coord, color_idx, this);
        this.tiles_panel.add(tile);
        this.coord_of_tiles.put(coord, tile);
    }

    public void set_steps(final int current, final int max) {
        this.steps.setText(Integer.toString(current) + "/" + Integer.toString(max));
    }
}

class Coord {
    public int x, y;

    public Coord(final int _x, final int _y) {
        this.x = _x;
        this.y = _y;
    }

    public boolean equals(final Object obj) {
        if(obj instanceof Coord) {
            final Coord that = (Coord) obj;
            return that.x == this.x && that.y == this.y;
        }
        return false;
    }

    public int hashCode() {
        return this.x * 101 + this.y;
    }
}

class Tile extends JButton {
    public int color_idx;
    public Coord coord;
    public FloodFrame frame;

    public Tile(final Coord _coord, final int _color_idx, final FloodFrame _frame) {
        this.coord = _coord;
        this.color_idx = _color_idx;
        this.frame = _frame;
        //this.setPreferredSize(new Dimension(50, 50));
        this.setBackground(gen_color(_color_idx));
        final TileActionListener listener = new TileActionListener(this);
        this.addMouseListener(listener);
        this.addMouseMotionListener(listener);
        this.setOpaque(true);
    }

    public void flood() {
        this.frame.driver.flood(this.color_idx);
    }

    public void set_color(final int _color_idx) {
        this.color_idx = _color_idx;
        this.setBackground(gen_color(_color_idx));
    }

    private Color gen_color(final int color_idx) {
        switch (color_idx) {
            case Constants.BLUE:
                return Color.BLUE;
            case Constants.RED:
                return Color.RED;
            case Constants.CYAN:
                return Color.CYAN;
            case Constants.PINK:
                return Color.PINK;
            case Constants.GREEN:
                return Color.GREEN;
            case Constants.YELLOW:
            	return Color.YELLOW;
            default:
                throw new Error("bad color index");
        }
    }
}

class TileActionListener extends MouseInputAdapter {
    private final Tile tile;

    public TileActionListener(final Tile _tile) {
        this.tile = _tile;
    }

    public void mouseClicked(final MouseEvent e) {
        this.tile.flood();
        this.tile.frame.driver.one_step_more();
    }
}




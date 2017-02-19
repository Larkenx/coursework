
import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;

import static java.lang.Math.toIntExact;

import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by mvc on 11/1/2015.
 */
public class Driver {
    private static final String HINT = "\n\nUsage:\n\"play\" to play the game.\n\"test\" to run test cases and generate time/size graph\n\n";

    public int size = 14;
    public int current_step;
    public int step_limit;
    public int num_of_tiles;

    private boolean enable_gui;

    private HashMap<Coord, Integer> color_of_tiles;

    private FloodFunction floodFunction;

    private FloodFrame floodFrame;

    private List<Integer> num_of_steps = new LinkedList<>();
    private List<Integer> times = new LinkedList<>();
    private List<List<Integer>> result = new LinkedList<>();

    public static void main(final String... args) {
        if(args.length > 0) {
            switch(args[0]) {
                case "play":
                    javax.swing.SwingUtilities.invokeLater(() -> new Driver(true));
                    break;
                case "test":
                    if(args.length > 1) {
                        new Driver(false).testx(Integer.parseInt(args[1]));
                        break;
                    } else{
                        new Driver(false).test();
                        break;
                    }
                default:
                    System.out.println(Driver.HINT);
            }
        }else {
            javax.swing.SwingUtilities.invokeLater(() -> new Driver(true));
        }
    }

    public Driver(final boolean _enable_gui) {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        }catch (Exception e) {
            e.printStackTrace(System.out);
        }
        this.enable_gui = _enable_gui;
        this.init_data();
    }

    public void one_step_more() {
        if(this.floodFunction.flooded_list.size() == this.num_of_tiles) {
            this.oh_yeah();
        }else {
            if(this.current_step == this.step_limit) {
                this.game_over();
            }else {
                if(this.enable_gui) {
                    this.floodFrame.set_steps(this.current_step, this.step_limit);
                }
            }
        }
    }

    public void flood(final int new_color_idx) {
        this.current_step += 1;
        this.floodFunction.flood(this.color_of_tiles, new_color_idx);
        /*The following is an O(n) operation. We could integrate it into the FloodFunction to improve the efficiency.
        * Doing so, however, exposes too much implementation details to the students,
        * which might be distracting.
        * */
        for (final Coord coord:floodFunction.flooded_list) {
            this.color_of_tiles.put(coord, new_color_idx);
            if (this.enable_gui) {
                this.floodFrame.coord_of_tiles.get(coord).set_color(new_color_idx);
            }
        }
        if (this.enable_gui) {
	    this.floodFrame.repaint();
	}
    }
    public void floodx(final int index, final int new_color_idx) {
        try {
            Class<?> c = floodFunction.getClass();
            Class[] argTypes = new Class[]{Map.class, Integer.class};
            String name = index==0?"flood":"flood" + Integer.toString(index);
            Method flood2 = c.getDeclaredMethod(name, argTypes);
            this.current_step += 1;
            flood2.invoke(floodFunction, this.color_of_tiles, new_color_idx);
            for (final Coord coord : floodFunction.flooded_list) {
                this.color_of_tiles.put(coord, new_color_idx);
                if (this.enable_gui) {
                    this.floodFrame.coord_of_tiles.get(coord).set_color(new_color_idx);
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void game_over() {
        JOptionPane.showMessageDialog(floodFrame, "You lose");
    }

    private void oh_yeah(){
        JOptionPane.showMessageDialog(floodFrame, "You Win!");
    }


    public void resize(final int _size) {
        this.size = _size;
        this.restart();
    }

    public void restart() {
        if(enable_gui) {
            this.floodFrame.dispose();
        }
        this.init_data();
    }

    private void init_data() {
        this.floodFunction = new FloodFunction(this);
        this.current_step = 0;
        this.step_limit = Driver.gen_step_limit(this.size);
        this.num_of_tiles = this.size * this.size;
        if(this.enable_gui) {
            this.floodFrame = new FloodFrame(this);
            this.floodFrame.set_steps(this.current_step, this.step_limit);
        }
        this.gen_tiles();
        this.floodFunction.flood(this.color_of_tiles, this.color_of_tiles.get(new Coord(0,0)));
    }

    private void gen_tiles() {
        this.color_of_tiles = new HashMap<>();
        for(int x_index=0; x_index<this.size; x_index+=1) {
            for(int y_index=0; y_index<this.size; y_index+=1) {
                final Coord coord = new Coord(x_index, y_index);
                final int color_idx = this.rand_int();
                this.color_of_tiles.put(coord, color_idx);
                if(this.enable_gui) {
                    this.floodFrame.add_tile(coord, color_idx);
                }
            }
        }
    }

    private void play() {
        long total = 0;
        for(int index=0; index<Constants.REPEAT_EACH_STEP; index+=1) {
            long startTime = System.currentTimeMillis();
            while(this.floodFunction.flooded_list.size() < this.num_of_tiles) {
                this.flood(Driver.rand_int());
            }
            long endTime = System.currentTimeMillis();
            total += (endTime - startTime);
            this.restart();
        }
        total /= Constants.REPEAT_EACH_STEP;
        System.out.println(Long.toString(total));
        this.times.add(toIntExact(total));
        this.num_of_steps.add(this.current_step);
    }

    private void playx(int i) {
            long total = 0;
            for (int index = 0; index < Constants.REPEAT_EACH_STEP; index += 1) {
                long startTime = System.currentTimeMillis();
                while (this.floodFunction.flooded_list.size() < this.num_of_tiles) {
                    this.floodx(i, Driver.rand_int());
                }
                long endTime = System.currentTimeMillis();
                total += (endTime - startTime);
                this.restart();
            }
            total /= Constants.REPEAT_EACH_STEP;
            System.out.println(Long.toString(total));
            this.times.add(toIntExact(total));
            this.num_of_steps.add(this.current_step);
    }

    private void test() {
        this.result.clear();
            this.times.clear();
            for (int s = 1; s < Constants.MAX_TEST_SIZE; s += 1) {
                System.out.println("testing size " + s);
                this.resize(s);
                this.play();
            }
            result.add(times);
        new GraphingData(result).visualAndWrite();
    }

    private void testx(int floods) {
        this.result.clear();
        for(int index=0; index<floods; index+=1) {
            System.out.println("running flood" + Integer.toString(index));
            this.times.clear();
            for (int s = 1; s < Constants.MAX_TEST_SIZE; s += 1) {
                System.out.println("testing size " + s);
                this.resize(s);
                this.playx(index);
            }
            final List<Integer> l = new LinkedList<>();
            for(int i=0; i<this.times.size(); i+=1) {
                l.add(this.times.get(i));
            }
            this.result.add(l);
        }
        new GraphingData(this.result).visualAndWrite();
    }


    private static int gen_step_limit(final int size) {
            return size * 25 / 14 + 1;
    }

    private static int rand_int() {
        final Random rand = new Random();
        final int num_of_colors = 6;
        return rand.nextInt(num_of_colors);
    }
}

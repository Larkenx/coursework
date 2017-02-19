
import java.util.*;
import java.util.function.*;
import java.lang.*;

public class Stencil {

    public static Triple[][] convolute3(Convolution<Pixel,Triple> conv, Pixel[][] input, int width, int height) {
        Triple[][] output = new Triple[width][height];
        for (int i = 0; i < width; ++i) {
            for (int j = 0; j < height; ++j) {
                output[i][j] = conv.convolute(input, width, height, i, j);
            }
        }
        return output;
    }

    public static int[][] convolute1(Convolution<Pixel,Integer> conv, Pixel[][] input, int width, int height) {
        int[][] output = new int[width][height];
        for (int i = 0; i < width; ++i) {
            for (int j = 0; j < height; ++j) {
                output[i][j] = conv.convolute(input, width, height, i, j);
            }
        }
        return output;
    }

    // TODO padding for printing uniform columns
    private static void print_array(Object[][] arr, int width, int height) {
        boolean first_col;
        for (int y = 0; y < height; ++y) {
            first_col = true;
            for (int x = 0; x < width; ++x) {
                if (first_col) {
                    first_col = false;
                } else {
                    System.out.print(" ");
                }
                System.out.print(arr[x][y].toString());
            }
            System.out.println("");
        }
    }

    /*
    public static Pixel[][] laplace(Pixel[][] input, int width, int height) {
        double[][] kernel =
            { {0.25 ,  0.5, 0.25}
            , {0.5  , -3.0, 0.5 }
            , {0.25 ,  0.5, 0.25} };
        BiFunction<Integer,Pixel,Function<Triple,Triple>> more =
            (coeff, c) -> {
                return res -> {
                    res.a += coeff * ((double) c.red);
                    res.b += coeff * ((double) c.green);
                    res.c += coeff * ((double) c.blue);
                    return res;
                };
            };
        Supplier<Triple> done = () -> { return new Triple(0.0, 0.0, 0.0); };
        Convolution<Pixel,Triple> conv = new Convolution<>(kernel , 1 , more , done);
        Pixel[][] D = new Pixel[width][height];
        Triple[][] map = Stencil.convolute3(conv, input, width, height);
        for (int i = 0; i < width; ++i) {
            for (int j = 0; j < height; ++j) {
                D[i][j] = new Pixel(map[i][j].a.intValue(), map[i][j].b.intValue(), map[i][j].c.intValue());
            }
        }
        return D;
    }
    */

    public static int[][] disruption(Pixel[][] input, int width, int height) {
        int[][] gx_kernel =
            { {-1 , 0, 1}
            , {-2 , 0, 2}
            , {-1 , 0, 1} };
        int[][] gy_kernel =
            { {-1 ,-2, -1}
            , { 0 , 0,  0}
            , { 1 , 2,  1} };
        BiFunction<Integer,Pixel,Function<Integer,Integer>> more =
            (coeff, c1) -> {
                return res -> {
                    return (coeff * ((c1.red + c1.green + c1.blue)) / 3) + res;
                };
            };
        Supplier<Integer> done = () -> { return 0; };
        Convolution<Pixel,Integer> conv_gx = new Convolution<Pixel,Integer>
            ( gx_kernel
            , 1
            , more
            , done
            );
        Convolution<Pixel,Integer> conv_gy = new Convolution<Pixel,Integer>
            ( gy_kernel
            , 1
            , more
            , done
            );
        int[][] D = new int[width][height];
        int[][] gx = Stencil.convolute1(conv_gx, input, width, height);
        int[][] gy = Stencil.convolute1(conv_gy, input, width, height);
        for (int i = 0; i < width; ++i) {
            for (int j = 0; j < height; ++j) {
                D[i][j] = ((Double) Math.sqrt(Math.pow((double) gx[i][j], 2.0) + Math.pow((double) gy[i][j], 2.0))).intValue();
            }
        }
        return D;
    }

    public static void main(String[] args) {
        Pixel[][] img = new Pixel[16][16];
        int grey;
        for (int y = 0; y < 16; ++y) {
            for (int x = 0; x < 16; ++x) {
                grey = x >= 8 ? 255 : 0;
                img[x][y] = new Pixel(grey, grey, grey);
            }
        }
        print_array(img, 16, 16);
        System.out.println("");

    }
}

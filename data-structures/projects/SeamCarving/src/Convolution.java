
import java.util.*;
import java.util.function.*;

public class Convolution<T,U extends Object> {
    // The matrix of weights; the convolution stencil.
    final int[][] kernel;
    // The radius of the kernel. e.g. radius=0 means the kernel is the single pixel. radius=1 is the Moore neighborhood.
    final int radius;
    // size is the edge length of the kernel.
    final int size;
    // more is a function from a Integer coefficient from the kernel, the T from the input, and a partial U result, to a U.
    BiFunction<Integer,T,Function<U,U>> more;
    // done is a U value, the initial result.
    Supplier<U> done;
    // together, more and done make a right fold over a collection of coefficient/T pairs, resulting in a U.

    // convolute single coordinate.
    public U convolute(T[][] input, int width, int height, int x, int y) {
        U res = this.done.get();
        int x_in;
        int y_in;
        int coeff;
        T t;
        for (int i = 0; i < this.size; ++i) {
            for (int j = 0; j < this.size; ++j) {
                x_in = x + i - this.radius;
                y_in = y + j - this.radius;
                x_in = (x_in < 0 ? 0 : (x_in >= width ? width - 1 : x_in));
                y_in = (y_in < 0 ? 0 : (y_in >= height ? height - 1 : y_in));
                coeff = this.kernel[i][j];
                t = input[x_in][y_in];
                res = this.more.apply(coeff, t).apply(res);
            }
        }
        return res;
    }

    public Convolution(int[][] kernel, int radius, BiFunction<Integer,T,Function<U,U>> more, Supplier<U> done) {
        this.kernel = kernel;
        this.radius = radius;
        this.size   = (2 * radius) + 1;
        this.more   = more;
        this.done   = done;
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
}

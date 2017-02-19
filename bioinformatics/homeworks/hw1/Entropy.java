class Entropy {
    static double[] profiles = {
                        0, 0.5, 0.2, 0.3,
                        0.5, 0, 0.5, 0,
                        0.5, 0.4, 0.1, 0,
                        0.3, 0.4, 0.1, 0.2,
                        0.1, 0, 0.2, 0.7,
                        0.3, 0, 0.6, 0.1,
                        1, 0, 0, 0,
                        0.8, 0, 0.1, 0.1,
                        0, 0, 0, 1,
                        0.2, 0.2, 0, 0.6};

    public static void main(String[] args) {
        double sum = 0;
        for (int i = 0; i < 40; i++) {
            double rowResult = 0;
            double val = profiles[i];
            System.out.println(val * (Math.log(val) / Math.log(2)));
            if (val != 0)
                sum += val * (Math.log(val) / Math.log(2));
        }
        System.out.println(-1 * sum);
    }
}

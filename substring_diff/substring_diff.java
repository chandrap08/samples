import java.io.*;
import java.math.BigInteger;
import java.util.Random;
import java.util.StringTokenizer;

public class Solution implements Runnable {

    // leave empty to read from stdin/stdout
    private static final String TASK_NAME_FOR_IO = "";

    // file names
    private static final String FILE_IN = TASK_NAME_FOR_IO + ".in";
    private static final String FILE_OUT = TASK_NAME_FOR_IO + ".out";

    BufferedReader in;
    PrintWriter out;
    StringTokenizer tokenizer = new StringTokenizer("");

    public static void main(String[] args) {
        new Solution().run();
    }

    int n, maxMismatch;
    char[] a, b;

    private void solve() throws IOException {
        int tc = nextInt();
        for (int tcIdx = 0; tcIdx < tc; tcIdx++) {
            maxMismatch = nextInt();
            a = nextToken().toCharArray();
            b = nextToken().toCharArray();
            if (a.length != b.length) {
                throw new IllegalStateException("Input strings have different length");
            }
            n = a.length;

            out.println(solveFast());
        }

        // stress();
    }

    private void stress() {
        Random r = new Random(123128731L);
        for (;;) {
            n = 50;
            maxMismatch = r.nextInt(n + 1);
            a = randomString(r, n);
            b = randomString(r, n);

            int ans1 = solveNaive();
            int ans2 = solveFast();
            if (ans1 != ans2) {
                throw new IllegalStateException();
            }
            System.out.println("OK");
        }

    }

    private char[] randomString(Random r, int n) {
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < n; i++) {
            buf.append((char) ('a' + r.nextInt(26)));
        }
        return buf.toString().toCharArray();
    }

    private int solveFast() {
        int maxLen = 0;
        for (int i = 0; i < n; i++) {
            int m = solveFastSegment(i, 0);
            maxLen = Math.max(maxLen,  m);
        }
        for (int j = 0; j < n; j++) {
            int m = solveFastSegment(0, j);
            maxLen = Math.max(maxLen,  m);
        }
        return maxLen;
    }

    private int solveFastSegment(int i, int j) {
        int len = Math.min(n - i, n - j);
        short[] d = new short[len];
        for (int k = 0; k < len; k++) {
            d[k] = (short) ((a[i + k] != b[j + k]) ? 1 : 0);
        }

        int l = 0;
        int sumMismatch = 0;
        int answer = 0;
        for (int r = 0; r < len; r++) {
            // let's add it to the sum anyway
            sumMismatch += d[r];

            // do we need to advance l now?
            while (sumMismatch > maxMismatch) {
                sumMismatch -= d[l++];
            }

            // update answer
            answer = Math.max(answer, r - l + 1);
        }

        return answer;
    }

    private int solveNaive() {

        int maxLen = 0;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                int mismatch = 0;
                for (int k = 0; i + k < n && j + k < n; k++) {
                    if (a[i + k] != b[j + k]) {
                        mismatch++;
                    }

                    if (mismatch <= maxMismatch) {
                        maxLen = Math.max(maxLen, k + 1);
                    }
                }
            }

        return maxLen;
    }

    public void run() {
        long timeStart = System.currentTimeMillis();

        boolean fileIO = TASK_NAME_FOR_IO.length() > 0;
        try {

            if (fileIO) {
                in = new BufferedReader(new FileReader(FILE_IN));
                out = new PrintWriter(new FileWriter(FILE_OUT));
            } else {
                in = new BufferedReader(new InputStreamReader(System.in));
                out = new PrintWriter(new OutputStreamWriter(System.out));
            }

            solve();

            in.close();
            out.close();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        long timeEnd = System.currentTimeMillis();

        if (fileIO) {
            System.out.println("Time spent: " + (timeEnd - timeStart) + " ms");
        }
    }

    private String nextToken() throws IOException {
        while (!tokenizer.hasMoreTokens()) {
            String line = in.readLine();
            if (line == null) {
                return null;
            }
            tokenizer = new StringTokenizer(line);
        }
        return tokenizer.nextToken();
    }

    private int nextInt() throws IOException {
        return Integer.parseInt(nextToken());
    }

    private BigInteger nextBigInt() throws IOException {
        return new BigInteger(nextToken());
    }

    private long nextLong() throws IOException {
        return Long.parseLong(nextToken());
    }

    private double nextDouble() throws IOException {
        return Double.parseDouble(nextToken());
    }

}

package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeAList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeAListConstruction();
    }

    public static void timeAListConstruction() {
        // TODO: YOUR CODE HERE
        AList<Integer> Ns = new AList<>();
        AList<Double> times = new AList<>();
        AList<Integer> opCounts = new AList<>();

       int[] sizes = {1000, 2000, 4000, 8000, 16000, 32000, 64000, 128000};

        // 测量不同N下所需要的时间
        for (int N : sizes){
            // 新建List存储N个整数（测试）
            AList <Integer> list = new AList<>();
            Stopwatch stopwatch = new Stopwatch();
            for (int i = 0; i < N; i++){
                list.addLast(i);
            }
            // 计算每次addList所需要的时间
            double timeInSeconds = stopwatch.elapsedTime();
            Ns.addLast(N);
            // 记录添加N个元素所需要花费的时间
            times.addLast(timeInSeconds);
            // 统计不同操作下添加的元素数量
            opCounts.addLast(list.size());
        }
        printTimingTable(Ns, times, opCounts);
    }
}
            
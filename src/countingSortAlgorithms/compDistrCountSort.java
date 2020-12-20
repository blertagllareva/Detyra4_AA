package countingSortAlgorithms;
import com.google.common.base.Stopwatch;
//import java.util.concurrent.TimeUnit;
import java.util.*;
public class compDistrCountSort
{
    private static int[] ComparisonCountingSort(int[] A)
    {
        int[] S = new int[A.length];
        int[] counts = new int[A.length];

        for (int i = 0; i < counts.length; i++)
        {
            counts[i] = 0;
        }
        for (int i = 0; i < A.length - 1;i++)
        {
            for (int j = i + 1; j < A.length;j++)
            {
                if (A[i] < A[j])
                {
                    counts[j]++;
                }
                else
                {
                    counts[i]++;
                }
            }
        }
        for (int i = 0; i < A.length;i++)
        {
            S[counts[i]] = A[i];
        }
        return S;

    }
    private static int[] DistributionCountingSort(int[] A)
    {
        int l = A[0], u = A[0];
        for (int i = 0; i < A.length;i++)
        {
            if (A[i] < l)
            {
                l = A[i];
            }
            else if (A[i] > u)
            {
                u = A[i];
            }
        }
        int[] D = new int[u];
        for (int i = 0; i < u;i++)
        {
            D[i] = 0;
        }
        for (int i = 0; i < A.length; i++)
        {
            D[A[i] - l] = D[A[i] - l] + 1;
        }
        for (int i = 1;i < u;i++)
        {
            D[i] = D[i] + D[i - 1];
        }
        int[] S = new int[A.length];
        for (int i = A.length - 1;i >= 0;i--)
        {
            int j = A[i] - l;
            S[D[j] - 1] = A[i];
            D[j] = D[j] - 1;
        }
        return S;
    }
    public static void main(String[] args)
    {
        Random rand = new Random();
        int[] A = new int[100];

        int j = 0;
        while (j < 100)
        {
            A[j] = rand.nextInt(1 << 10000);
            j++;
        }

        Stopwatch startTime = Stopwatch.createStarted();
        //startTime.start();


        int[] comparisonCountArray = ComparisonCountingSort(A);

        startTime.stop();
        System.out.println("Comparison Counting Sort Time: " + startTime.elapsed());



        Stopwatch endTime = Stopwatch.createUnstarted();
        endTime.start();

        int[] distributionCountArray = DistributionCountingSort(A);


        System.out.println("Distribution Counting Sort Time: " + endTime.elapsed());

        endTime.stop();


        new Scanner(System.in).nextLine();
    }
}

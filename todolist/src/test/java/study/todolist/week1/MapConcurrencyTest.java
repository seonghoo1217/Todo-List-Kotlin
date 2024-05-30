package study.todolist.week1;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

public class MapConcurrencyTest {
    static final int ITERATION_COUNT = 10000000;
    static CountDownLatch latch;
    static Map<Integer, Integer> map;
    static List<Long> times;

    private void init(Map<Integer, Integer> m) {
        latch = new CountDownLatch(20);
        map = m;
        for (int i = 1; i <= 1000; i++) {
            map.put(i, i);
        }
    }

    @Test
    public void test() throws InterruptedException {
        for (Map m : new Map[]{
                new HashMap<Integer, Integer>(),
                new ConcurrentHashMap<Integer, Integer>(),
                new Hashtable<Integer, Integer>()
        }) {
            times = new ArrayList<>();

            for (int t = 0; t < 10; t++) {
                init(m);

                long start = System.currentTimeMillis();

                // 쓰기 작업 쓰레드 10개
                for (int counter = 0; counter < 10; counter++) {
                    new Thread(() -> {
                        for (int i = 1; i <= ITERATION_COUNT; i++) {
                            int key = i % 1000 + 1;
                            map.put(key, key);
                        }
                        latch.countDown();
                    }).start();
                }

                // 읽기 작업 쓰레드 10개
                for (int counter = 0; counter < 10; counter++) {
                    new Thread(() -> {
                        for (int i = 1; i <= ITERATION_COUNT; i++) {
                            int key = i % 1000 + 1;
                            map.get(key);
                        }
                        latch.countDown();
                    }).start();
                }

                // 모든 쓰레드 작업 대기
                latch.await();

                long time = System.currentTimeMillis() - start;
                times.add(time);
            }

            System.out.println("Map: " + m.getClass().getSimpleName());
            System.out.println("Execution Times: " + Arrays.toString(times.toArray()));
            System.out.println("Average execution Time: " + times.stream().mapToLong(Long::longValue).average().getAsDouble() + "ms");
            System.out.println();
        }
    }
}

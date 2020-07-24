package com.other.problems;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class MinSwapToSort {
    class Item implements Comparable<Item> {
        public int value;
        public int position;

        public Item(int value, int position) {
            this.value = value;
            this.position = position;
        }

        @Override
        public int compareTo(Item item) {
            if (this.value > item.value) {
                return 1;
            } else if (this.value < item.value) {
                return -1;
            }
            return 0;
        }

    }

    public int minSwaps(int[] array, int length) {
        Item[] arrayItem = new Item[length];
        for (int index = 0; index < length; ++index) {
            arrayItem[index] = new Item(array[index], index);
        }

        Arrays.sort(arrayItem);

        int totalSwapCount = 0;
        int cycle = 0;
        int nextIndex = 0;
        boolean[] isVisited = new boolean[length];

        for (int index = 0; index < length; ++index) {
            if (isVisited[index] || index == arrayItem[index].position) {
                continue;
            }

            cycle = 0;
            nextIndex = index;
            while (!isVisited[nextIndex]) {
                isVisited[nextIndex] = true;
                nextIndex = arrayItem[nextIndex].position;
                cycle += 1;
            }

            if (cycle != 0) {
                totalSwapCount += (cycle - 1);
            }
        }

        return totalSwapCount;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine().trim());
        while (t-- > 0) {
            int length = Integer.parseInt(br.readLine().trim());
            String str[] = br.readLine().trim().split(" ");
            int[] array = new int[length];
            for (int i = 0; i < length; i++) {
                array[i] = Integer.parseInt(str[i]);
            }
            long start = System.currentTimeMillis();
            System.out.println(new MinSwapToSort().minSwaps(array, length));
            long end = System.currentTimeMillis();
            System.out.println("Time taken: " + (end - start + " ms"));
        }
    }
}

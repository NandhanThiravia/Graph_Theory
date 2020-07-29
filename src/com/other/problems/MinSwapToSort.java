package com.other.problems;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;

public class MinSwapToSort {
    class Item {
        public int value;
        public int position;

        public Item(int value, int position) {
            this.value = value;
            this.position = position;
        }
    }

    public int minSwaps(int[] array, int length) {
        ArrayList<Item> arrayItem = new ArrayList<Item>();
        for (int index = 0; index < length; ++index) {
            arrayItem.add(new Item(array[index], index));
        }

        arrayItem.sort(new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                if (o1.value > o2.value)
                    return 1;
                return -1;
            }
        });

        int totalSwapCount = 0;
        int cycle = 0;
        int nextIndex = 0;
        boolean[] isVisited = new boolean[length];

        for (int index = 0; index < length; ++index) {
            if (isVisited[index] || index == arrayItem.get(index).position) {
                continue;
            }

            cycle = 0;
            nextIndex = index;
            while (!isVisited[nextIndex]) {
                isVisited[nextIndex] = true;
                nextIndex = arrayItem.get(nextIndex).position;
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

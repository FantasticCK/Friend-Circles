package com.CK;

public class Main {

    public static void main(String[] args) {
        // write your code here
    }
}

class Solution {
    public int findCircleNum(int[][] M) {
        int n = M.length;
        if (n == 0) return 0;
        DSU uf = new DSU(n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i <= j) continue;
                if (M[i][j] == 1) {
                    uf.union(i, j);
                }
            }
        }
        return uf.groups;
    }

    class DSU {
        int[] parents;
        int[] sizes;
        int groups;

        DSU(int n) {
            parents = new int[n];
            sizes = new int[n];
            groups = 0;
            for (int i = 0; i < n; i++) {
                parents[i] = i;
                sizes[i] = 1;
                groups++;
            }
        }

        private int find(int i) {
            while (i != parents[i]) {
                parents[i] = find(parents[i]);
                i = parents[i];
            }
            return i;
        }

        private void union(int p, int q) {
            int rp = find(p), rq = find(q);
            if (rp == rq) return;
            groups--;
            if (sizes[rp] >= sizes[rq]) {
                parents[rq] = rp;
                sizes[rp] += sizes[rq];
            } else {
                parents[rp] = rq;
                sizes[rq] += sizes[rp];
            }
        }
    }
}
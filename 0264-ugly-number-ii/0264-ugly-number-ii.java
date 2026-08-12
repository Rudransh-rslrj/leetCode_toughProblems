class Solution {
    public int nthUglyNumber(int n) {
        if (n == 1) return 1;

        PriorityQueue<Long> q = new PriorityQueue<>();
        HashSet<Long> set = new HashSet<>();

        int[] arr = {2, 3, 5};

        q.add(1L);
        set.add(1L);

        int count = 0;

        while (!q.isEmpty()) {
            long t = q.poll();
            count++;

            if (count == n) {
                return (int)t;
            }

            for (int el : arr) {
                long k = t * el;

                if (!set.contains(k)) {
                    q.add(k);
                    set.add(k);
                }
            }
        }

        return -1;
    }
}
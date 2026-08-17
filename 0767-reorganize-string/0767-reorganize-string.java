class Solution {
    public String reorganizeString(String s) {
        HashMap<Character, Integer> map = new HashMap<>();

        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        PriorityQueue<Character> pq = new PriorityQueue<>(
            (a, b) -> map.get(b) - map.get(a)
        );

        pq.addAll(map.keySet());

        StringBuilder ret = new StringBuilder();

        while (pq.size() > 1) {
            char c1 = pq.poll();
            char c2 = pq.poll();

            ret.append(c1).append(c2);

            map.put(c1, map.get(c1) - 1);
            map.put(c2, map.get(c2) - 1);

            if (map.get(c1) > 0) pq.add(c1);
            if (map.get(c2) > 0) pq.add(c2);
        }

        if (!pq.isEmpty()) {
            char c = pq.poll();

            if (map.get(c) > 1) return "";

            ret.append(c);
        }

        return ret.toString();
    }
}
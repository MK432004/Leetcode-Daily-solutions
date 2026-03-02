import java.util.*;

class Solution {
    static final int MOD = 1_000_000_007;

    public int rectangleArea(int[][] rectangles) {
        List<Event> events = new ArrayList<>();
        Set<Integer> ySet = new HashSet<>();

        // Create events
        for (int[] r : rectangles) {
            events.add(new Event(r[0], r[1], r[3], 1));
            events.add(new Event(r[2], r[1], r[3], -1));
            ySet.add(r[1]);
            ySet.add(r[3]);
        }

        Collections.sort(events);

        // Coordinate compression on Y
        List<Integer> ys = new ArrayList<>(ySet);
        Collections.sort(ys);

        Map<Integer, Integer> yIndex = new HashMap<>();
        for (int i = 0; i < ys.size(); i++) {
            yIndex.put(ys.get(i), i);
        }

        SegmentTree segTree = new SegmentTree(ys);

        long area = 0;
        int prevX = events.get(0).x;

        for (Event e : events) {
            int currX = e.x;
            long width = currX - prevX;
            long height = segTree.totalLength();
            area = (area + width * height) % MOD;

            segTree.update(
                yIndex.get(e.y1),
                yIndex.get(e.y2),
                e.type
            );

            prevX = currX;
        }

        return (int) area;
    }

    static class Event implements Comparable<Event> {
        int x, y1, y2, type;

        Event(int x, int y1, int y2, int type) {
            this.x = x;
            this.y1 = y1;
            this.y2 = y2;
            this.type = type;
        }

        public int compareTo(Event other) {
            return this.x - other.x;
        }
    }

    static class SegmentTree {
        int[] count;
        long[] total;
        List<Integer> ys;

        SegmentTree(List<Integer> ys) {
            this.ys = ys;
            int n = ys.size();
            count = new int[n * 4];
            total = new long[n * 4];
        }

        void update(int y1, int y2, int val) {
            update(1, 0, ys.size() - 1, y1, y2, val);
        }

        void update(int node, int start, int end, int y1, int y2, int val) {
            if (y1 >= end || y2 <= start) return;

            if (y1 <= start && end <= y2) {
                count[node] += val;
            } else {
                int mid = (start + end) / 2;
                update(node * 2, start, mid, y1, y2, val);
                update(node * 2 + 1, mid, end, y1, y2, val);
            }

            if (count[node] > 0) {
                total[node] = ys.get(end) - ys.get(start);
            } else {
                if (start + 1 >= end) {
                    total[node] = 0;
                } else {
                    total[node] = total[node * 2] + total[node * 2 + 1];
                }
            }
        }

        long totalLength() {
            return total[1];
        }
    }
}
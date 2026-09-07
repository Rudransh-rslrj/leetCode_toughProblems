// import java.util.*;

// class Solution {

//     static class DisjointSet {

//         int[] parent;
//         int[] size;

//         DisjointSet(int n) {
//             parent = new int[n];
//             size = new int[n];

//             for (int i = 0; i < n; i++) {
//                 parent[i] = i;
//                 size[i] = 1;
//             }
//         }

//         int findUPar(int node) {
//             if (node == parent[node]) {
//                 return node;
//             }

//             return parent[node] = findUPar(parent[node]);
//         }

//         void unionBySize(int u, int v) {

//             int ulp_u = findUPar(u);
//             int ulp_v = findUPar(v);

//             if (ulp_u == ulp_v) {
//                 return;
//             }

//             if (size[ulp_u] < size[ulp_v]) {
//                 parent[ulp_u] = ulp_v;
//                 size[ulp_v] += size[ulp_u];
//             } else {
//                 parent[ulp_v] = ulp_u;
//                 size[ulp_u] += size[ulp_v];
//             }
//         }
//     }

//     public static List<List<String>> accountsMerge(
//             List<List<String>> details) {

//         int n = details.size();

//         DisjointSet ds = new DisjointSet(n);

//         HashMap<String, Integer> mapMailNode =
//                 new HashMap<>();

//         // Connect accounts having common emails
//         for (int i = 0; i < n; i++) {

//             for (int j = 1; j < details.get(i).size(); j++) {

//                 String mail = details.get(i).get(j);

//                 if (!mapMailNode.containsKey(mail)) {
//                     mapMailNode.put(mail, i);
//                 } else {
//                     ds.unionBySize(
//                             i,
//                             mapMailNode.get(mail)
//                     );
//                 }
//             }
//         }

//         // Store emails according to their component
//         ArrayList<String>[] mergedMail =
//                 new ArrayList[n];

//         for (int i = 0; i < n; i++) {
//             mergedMail[i] = new ArrayList<>();
//         }

//         for (Map.Entry<String, Integer> entry
//                 : mapMailNode.entrySet()) {

//             String mail = entry.getKey();

//             int node =
//                     ds.findUPar(entry.getValue());

//             mergedMail[node].add(mail);
//         }

//         // Construct answer
//         List<List<String>> ans =
//                 new ArrayList<>();

//         for (int i = 0; i < n; i++) {

//             if (mergedMail[i].isEmpty()) {
//                 continue;
//             }

//             Collections.sort(mergedMail[i]);

//             List<String> temp =
//                     new ArrayList<>();

//             // Name
//             temp.add(details.get(i).get(0));

//             // Emails
//             for (String mail : mergedMail[i]) {
//                 temp.add(mail);
//             }

//             ans.add(temp);
//         }

//         return ans;
//     }
// }

import java.util.*;

class Solution {

    class ptr {
        ptr parent;
        String name;

        ptr(String name) {
            this.name = name;
            parent = this;
        }
    }

    ptr find(ptr x) {
        if (x.parent != x)
            x.parent = find(x.parent);
        return x.parent;
    }

    public List<List<String>> accountsMerge(List<List<String>> accounts) {

        HashMap<String, ptr> map = new HashMap<>();

        for (List<String> a : accounts) {

            ptr p = new ptr(a.get(0));

            for (int i = 1; i < a.size(); i++) {

                String mail = a.get(i);

                if (map.containsKey(mail)) {
                    ptr x = find(p);
                    ptr y = find(map.get(mail));

                    if (x != y)
                        x.parent = y;
                }

                map.putIfAbsent(mail, p);
            }
        }

        HashMap<ptr, TreeSet<String>> groups = new HashMap<>();

        for (String mail : map.keySet()) {

            ptr p = find(map.get(mail));

            groups.putIfAbsent(p, new TreeSet<>());
            groups.get(p).add(mail);
        }

        List<List<String>> ans = new ArrayList<>();

        for (ptr p : groups.keySet()) {

            List<String> temp = new ArrayList<>();
            temp.add(p.name);
            temp.addAll(groups.get(p));

            ans.add(temp);
        }

        return ans;
    }
}
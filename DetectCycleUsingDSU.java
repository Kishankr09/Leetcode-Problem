/*

GFG PODT 27-11-23
Given an undirected graph with no self loops with V (from 0 to V-1) nodes and E edges, the task is to check if there is any cycle in the undirected graph.

Note: Solve the problem using disjoint set union (DSU).

Example 1:

Input: 

Output: 1
Explanation: There is a cycle between 0->2->4->0
Example 2:

Input: 

Output: 0
Explanation: The graph doesn't contain any cycle
Your Task:
You don't need to read or print anyhting. Your task is to complete the function detectCycle() which takes number of vertices in the graph denoting as V and adjacency list adj and returns 1 if graph contains any cycle otherwise returns 0.

Expected Time Complexity: O(V + E)
Expected Space Complexity: O(V)

Constraints:
2 ≤ V ≤ 104
1 ≤ E ≤ 104


*/


class Solution
{
    //Function to detect cycle using DSU in an undirected graph.
    public int detectCycle(int V, ArrayList<ArrayList<Integer>> adj)
    {
        // Code here
        unionFind uf=new unionFind(V);
        Set<String>set=new HashSet<>();
        for(int i=0;i<V;i++){
            for(int it:adj.get(i)){
                if(set.contains(helper(i,it))){
                    continue;
                }
                if(uf.findParent(i)!=uf.findParent(it)){
                    uf.union(i,it);
                    set.add(helper(i,it));
                    set.add(helper(it,i));
                }
                else{
                    return 1;
                }
            }
        }
        return 0;
    }
    class unionFind{
        int parent[];
        int rank[];
        public unionFind(int n){
            parent=new int[n];
            rank=new int[n];
            for(int i=0;i<n;i++){
            parent[i]=i;
            }
            Arrays.fill(rank,0);
        }
        public void union(int a,int b){
            int ulp_a=findParent(a);
            int ulp_b=findParent(b);
            if(ulp_a==ulp_b){
                return ;
            }
            else if(ulp_a>ulp_b){
                parent[ulp_b]=ulp_a;
            }
            else{
                parent[ulp_a]=ulp_b;
            }
        }
        int findParent(int a){
            if(parent[a]==a){
                return parent[a];
            }
            return parent[a]=findParent(parent[a]);
        }
    }
    public String helper(int a,int b){
        String ans=Integer.toString(a);
        ans+=Integer.toString(b);
        return ans;
    }
}

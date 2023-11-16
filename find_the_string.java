/*

Problem of The Day GFG 16/11/2023
Given two integers N and K, the task is to find the string S of minimum length such that it contains all possible strings of size N as a substring. The characters of the string should be from integers ranging from 0 to K-1.  

Example 1:

Input:
N = 2, K = 2
Output: 
00110
Explanation: 
Allowed characters are from 0 to k-1 (i.e., 0 and 1).
There are 4 string possible of size N=2 
(i.e "00", "01","10","11")
"00110" contains all possible string as a 
substring. It also has the minimum length.
Example 2:

Input:
N = 2, K = 3
Output: 
0010211220
Explanation: 
Allowed characters are from 0 to k-1 (i.e., 0, 1 and 2).
There are total 9 strings possible
of size N, given output string has the minimum
length that contains all those strings as substring.
Your Task: 
You don't need to read input or print anything. Complete the function findString( ) which takes the integer N and the integer K as input parameters and returns the string.
Note: In case of multiple answers, return any string of minimum length which satisfies above condition. The driver will print the length of the string. In case of wrong answer it will print -1.

Expected Time Complexity: O(KNlogK)
Expected Space Complexity: O(KNN)

Constraints:
1 ≤ N ≤ 4
1 < K < 10
1 < KNN < 106
*/
class Solution{
    public String findString(int n, int k){
        K = k;
        N = n;
        st.clear();
        if(n == 1) {
            char[] r = new char[k];
            for(int i = 0; i < k; i++)
                r[i] = (char)(i + '0');
            return new String(r);
        }
        tot = 1;
        for(int i = 1; i <= n; i++)
            tot *= k;
        char[] ansa = new char[n];
        Arrays.fill(ansa, '0');
        ans = new String(ansa);
        st.add(ans);
        dfs();
        return ans;
    }
    int K, N;
    long tot;
    HashSet<String> st = new HashSet<>();
    String ans;
    boolean dfs() {
        if(st.size() == tot) {
            return true;
        }
        String tmp = "";
        if(N > 1) {
            tmp = ans.substring(ans.length()-N+1);
        }
        for(int i = 0; i < K; i++) {
            tmp += i;
            if(!st.contains(tmp)) {
                ans += i;
                st.add(tmp);
                if(dfs())
                    return true;
                st.remove(tmp);
                ans = ans.substring(0, ans.length()-1);
            }
            tmp = tmp.substring(0, tmp.length()-1);
        }
        return false;
    }
}

import javafx.util.Pair;

import java.io.*;
import java.math.*;
import java.util.*;
@SuppressWarnings("Duplicates")
// author @mdazmat9
public class Main{
    static Scanner sc = new Scanner(System.in);
    static PrintWriter out = new PrintWriter(System.out);
    public static void main(String[] args) throws IOException {
        int test = 1;
       //   test=sc.nextInt();
        for (int ind = 0; ind < test; ind++) {
            solve();
        }
        out.flush();
    }

    static void solve() {
            int n=sc.nextInt();
            int m=sc.nextInt();
            int[][]a=new int[n][m];
            for(int i=0;i<n;i++)Arrays.fill(a[i],-1);
            int[]row=intarray(n);
            int[]col=intarray(m);


            for(int i=0;i<m;i++){
                if(col[i]==0){
                    a[0][i]=0;
                    continue;
                }
                for(int j=0;j<col[i];j++){
                    a[j][i]=1;
                }
                if(col[i]<n){
                    a[col[i]][i]=0;
                }
            }
/*
            for(int i=0;i<n;i++){
                for(int j=0;j<m;j++){
                    out.print(a[i][j]+" ");
                }
                out.println();
            }
*/



            for(int i=0;i<n;i++){
                if(row[i]==0){
                    if(a[i][0]==1){
                        out.println(0);
                        return;
                    }
                    a[i][0]=0;
                    continue;
                }
                for(int j=0;j<row[i];j++){
                    if(a[i][j]==0){
                        out.println(0);
                        return;
                    }
                    a[i][j]=1;
                }
                if(row[i]<m){
                    if(a[i][row[i]]==1){
                        out.println(0);
                        return;
                    }
                    a[i][row[i]]=0;
                }

            }
/*
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                out.print(a[i][j]+" ");
            }
            out.println();
        }
*/
        int count=0;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(a[i][j]==-1)count++;
            }
        }
        out.println(fast_pow(2,count,abs));

    }

    static class DSU {

    int[] rank, parent;
    int n;

    public DSU(int n) {
        rank = new int[n];
        parent = new int[n];
        this.n = n;
        makeSet();
    }

    void makeSet() {
        for (int i = 0; i < n; i++)
            parent[i] = i;
    }
    int find(int x) {
        if (parent[x] != x) {
            return find(parent[x]);
        }
        return x;
    }
    void union(int x, int y) {
        int xRoot = find(x);
        int yRoot = find(y);
        if (xRoot == yRoot)
            return;

        if (rank[xRoot] < rank[yRoot])
            parent[xRoot] = yRoot;

        else if (rank[yRoot] < rank[xRoot])
            parent[yRoot] = xRoot;

        else
        {
            parent[yRoot] = xRoot;
            rank[xRoot] = rank[xRoot] + 1;
        }
    }
}

    static int[] intarray(int n){ int [] a=new int[n];for(int i=0;i<n;i++)a[i]=sc.nextInt();return a; }
    static void sort(int[]a){ shuffle(a);Arrays.sort(a);}
    static void sort(long[]a){ shuffle(a);Arrays.sort(a);}
    static long[] longarray(int n){ long [] a=new long[n];for(int i=0;i<n;i++)a[i]=sc.nextLong();return a; }
    static ArrayList<Integer> intlist(int n){ArrayList<Integer> list=new ArrayList<>();for(int i=0;i<n;i++)list.add(sc.nextInt());return list; }
    static ArrayList<Long> longlist(int n){ArrayList<Long> list=new ArrayList<>();for(int i=0;i<n;i++)list.add(sc.nextLong());return list; }
    static int[][] int2darray(int n,int m){ int [][] a=new int[n][m];for(int i=0;i<n;i++){ for(int j=0;j<m;j++){ a[i][j]=sc.nextInt(); } }return a; }
    static long[][] long2darray(int n,int m){ long [][] a=new long[n][m];for(int i=0;i<n;i++){ for(int j=0;j<m;j++){ a[i][j]=sc.nextLong(); } }return a; }
    static char[][] char2darray(int n,int m){ char [][] a=new char[n][m];for(int i=0;i<n;i++){ String s=sc.next(); a[i]=s.toCharArray(); }return a; }

    static double pi=3.14159265358979323846264;
    public static double logb( double a, double b ) {return Math.log(a) / Math.log(b); }
    static long fast_pow(long a, long b,long abs) {
        if(b == 0) return 1L;
        long val = fast_pow(a, b / 2,abs);
        if(b % 2 == 0) return val * val % abs;
        else return val * val % abs * a % abs;
    }
    static long abs = (long)1e9 + 7;
    static   void shuffle(int[] a) { int n = a.length;for(int i = 0; i < n; i++) { int r = i + (int) (Math.random() * (n - i));int tmp = a[i];a[i] = a[r];a[r] = tmp; } }
    static   void shuffle(long[] a) { int n = a.length;for(int i = 0; i < n; i++) { int r = i + (int) (Math.random() * (n - i));long tmp = a[i];a[i] = a[r];a[r] = tmp; } }
    static long gcd(long a , long b) {
        if(b == 0) return a;
        return gcd(b , a % b);
    }
}

class Scanner {
    public BufferedReader reader;
    public StringTokenizer st;
    public Scanner(InputStream stream) {
        reader = new BufferedReader(new InputStreamReader(stream));
        st = null;
    }
    public String next() {
        while (st == null || !st.hasMoreTokens()) {
            try {
                String line = reader.readLine();
                if (line == null) return null;
                st = new StringTokenizer(line);
            } catch (Exception e) {
                throw (new RuntimeException());
            }
        }
        return st.nextToken();
    }
    public int nextInt() {
        return Integer.parseInt(next());
    }
    public long nextLong() {
        return Long.parseLong(next());
    }
    public double nextDouble() {
        return Double.parseDouble(next());
    }
}

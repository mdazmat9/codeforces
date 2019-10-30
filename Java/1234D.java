import java.io.*;
import java.math.*;
import java.util.*;
@SuppressWarnings("Duplicates")
// author @mdazmat9
public class Main {
    static Scanner sc = new Scanner(System.in);
    static PrintWriter out = new PrintWriter(System.out);

    public static void main(String[] args) throws IOException {
        int test = 1;
        // test=sc.nextInt();
        for (int ind = 0; ind < test; ind++) {
            solve();
        }
        out.flush();
    }

    static void solve() {
        String s = sc.next();
        int n=s.length();
        char [] array=s.toCharArray();
        SegmentTree[] tree=new SegmentTree[26];
        for(int i=0;i<26;i++){
            int[] a=new int[n];
            for(int j=0;j<n;j++){
                if(s.charAt(j)-'a'==i){
                    a[j]=1;
                }
            }
            tree[i]=new SegmentTree(a,n);
        }
        int q=sc.nextInt();
        for(int i=0;i<q;i++){
            int num=sc.nextInt();
            if(num==1){
                int pos=sc.nextInt()-1;
                char seg=sc.next().charAt(0);
                char old=array[pos];
                tree[old-'a'].updateValue(n,pos,-1);
                array[pos]=seg;
                tree[seg-'a'].updateValue(n,pos,1);
            }else {
                int x=sc.nextInt()-1;
                int y=sc.nextInt()-1;
                int sum=0;
                for(int j=0;j<26;j++){
                    num=tree[j].getSum(n,x,y);
                    if(num>0)sum++;
                }
                out.println(sum);
            }
        }

    }



   static class SegmentTree {
       int st[];

       SegmentTree(int arr[], int n) {
           int x = (int) (Math.ceil(Math.log(n) / Math.log(2)));
           int max_size = 2 * (int) Math.pow(2, x) - 1;
           st = new int[max_size]; // Memory allocation
           constructSTUtil(arr, 0, n - 1, 0);
       }

       int getMid(int s, int e) {
           return s + (e - s) / 2;
       }

       int getSumUtil(int ss, int se, int qs, int qe, int si) {
           if (qs <= ss && qe >= se)
               return st[si];
           if (se < qs || ss > qe)
               return 0;
           int mid = getMid(ss, se);
           return getSumUtil(ss, mid, qs, qe, 2 * si + 1) +
                   getSumUtil(mid + 1, se, qs, qe, 2 * si + 2);
       }

       void updateValueUtil(int ss, int se, int i, int diff, int si) {
           if (i < ss || i > se)
               return;
           st[si] = st[si] + diff;
           if (se != ss) {
               int mid = getMid(ss, se);
               updateValueUtil(ss, mid, i, diff, 2 * si + 1);
               updateValueUtil(mid + 1, se, i, diff, 2 * si + 2);
           }
       }

       void updateValue( int n, int i, int new_val) {
           // Check for erroneous input index
           if (i < 0 || i > n - 1) {
               System.out.println("Invalid Input");
               return;
           }
           updateValueUtil(0, n - 1, i, new_val, 0);
       }
       int getSum(int n, int qs, int qe) {
           if (qs < 0 || qe > n - 1 || qs > qe) {
               System.out.println("Invalid Input");
               return -1;
           }
           return getSumUtil(0, n - 1, qs, qe, 0);
       }
       int constructSTUtil(int arr[], int ss, int se, int si) {
           if (ss == se) {
               st[si] = arr[ss];
               return arr[ss];
           }

           int mid = getMid(ss, se);
           st[si] = constructSTUtil(arr, ss, mid, si * 2 + 1) +
                   constructSTUtil(arr, mid + 1, se, si * 2 + 2);
           return st[si];
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
class DSU {

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

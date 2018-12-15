import java.util.Arrays;

public class SpiralArray2D{

     public static void main(String []args){
        Spiral2D s = new Spiral2D();
        System.out.println(Arrays.deepToString(s.spiral(-1)));
        System.out.println(Arrays.deepToString(s.spiral(0)));
        System.out.println(Arrays.deepToString(s.spiral(1)));
        System.out.println(Arrays.deepToString(s.spiral(2)));
        System.out.println(Arrays.deepToString(s.spiral(3)));
        System.out.println(Arrays.deepToString(s.spiral(10)));
     }
     
     private static class Spiral2D{
         
         int[][] spiral(int n){
             if(n<1){
                 n=0;
             }
             int[][] ret = new int[n][n];
             int cur = 1;
             int r = 0;
             int c = 0;
             int[] rd = new int[]{0, 1, 0, -1};
             int[] cd = new int[]{1, 0, -1, 0};
             int di = 0;
             while(cur<=n*n){
                 ret[r][c] = cur;
                 r += rd[di];
                 c += cd[di];
                 if(atBound(n, ret, r, c)){
                     r -= rd[di];
                     c -= cd[di];
                     di = nextDirection(di);
                     r += rd[di];
                     c += cd[di];
                 }
                 cur++;
             }
             return ret;
         }
         
         boolean atBound(int n, int[][] ret, int r, int c){
             return r<0 || c<0 || r>=n || c>=n || ret[r][c]!=0;
         }
         
         int nextDirection(int di){
             return (di+1) % 4;
         }
     }
}

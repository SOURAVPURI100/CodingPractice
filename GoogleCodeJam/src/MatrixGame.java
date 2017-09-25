import java.util.*;

public class MatrixGame {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		int test  = Integer.parseInt(in.nextLine());
		
		for(int i = 1; i<=test; i++){
			int N = in.nextInt();
			int K = in.nextInt();
			int [] A = new int[N+1];
			int [] B = new int[N+1];
			A[1] = in.nextInt();
			B[1] = in.nextInt();
			int C = in.nextInt();
			int D = in.nextInt();
			int E1 = in.nextInt();
			int E2 = in.nextInt();
			int F = in.nextInt();
			int[] r = new int[N+1];
			int[] s = new int[N+1];
			int[] X = new int[N+1];
			int[] Y = new int[N+1];
			X[1] = A[1];
			Y[1] = B[1];
			
			int[][] matrix = new int[N][N];
//			xi = ( C*xi-1 + D*yi-1 + E1 ) modulo F.
//					yi = ( D*xi-1 + C*yi-1 + E2 ) modulo F.
//			ri = ( C*ri-1 + D*si-1 + E1 ) modulo 2.
//					si = ( D*ri-1 + C*si-1 + E2 ) modulo 2.
			for(int j =2; j<=N; j++){
				X[j] = (C * X[j-1] + D * Y[j-1] + E1)%F;
				Y[j]= (D * X[j-1] + C * Y[j-1] + E2)%F;
				
				r[j] = (C * r[j-1] + D * s[j-1] + E1)%2;
				s[j] = (D * r[j-1] + C* s[j-1] + E2)%2;
//				We define Ai = (-1)ri * xi and Bi = (-1)si * yi, for all i = 2 to N.
				A[j] = (int) Math.pow(-1, r[j]) * X[j];
				B[j] = (int) Math.pow(-1, s[j]) * Y[j];
			}
			
			
			for(int j =1; j<=N; j++){
				
				for(int k =1; k<=N; k++){
					matrix[j-1][k-1] = A[j] * B[k];
					
				}
			}
			List<Integer> sum = new ArrayList<>();
			for(int j =0; j<N; j++){
				for(int k =0; k<N; k++){
					for(int l = j; l<N; l++){
						for(int m = k; m <N; m++){
							sum.add(findsum(j, k, l, m, matrix));
							
							
							
						}
					}
					
				}
			}
			
			Collections.sort(sum);
			
			System.out.println("Case #"+i+": "+sum.get(sum.size() - K));
			
		}

	}
	
	public static int findsum(int j, int k, int l, int m, int[][] matrix){
		int sum = 0;
		for(int p = j; p<=l; p++){
			for(int g = k; g<=m; g++){
				sum += matrix[p][g];
			}
		}
		return sum;
		
	}

}

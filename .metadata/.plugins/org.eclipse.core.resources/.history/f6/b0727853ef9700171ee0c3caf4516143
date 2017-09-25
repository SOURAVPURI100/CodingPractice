
import java.util.*;
public class Test {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		int test = Integer.parseInt(in.nextLine());
		
		for(int i =1; i<=test; i++){
			
			boolean isPossible = true;
			int rows = Integer.parseInt(in.nextLine());
			int cols = rows;
			char[][] matrix = new char[rows][cols];
			int numX =0;
			
			int X_index = 0, Y_index = 0, X_index1 = 0, Y_index1 = 0, temp =0;
			boolean num1 = false;
			for(int j=0; j<rows; j++){
				numX= 0;
				String line = in.nextLine();
				
				for(int k=0;k<line.length(); k++){
					matrix[j][k] = line.charAt(k);
					if(line.charAt(k) == 'X'){
						temp = k;
						numX++;
					}
						
				}
				
				if (numX > 2 || numX < 1){
					isPossible = false;
				}
				else if(numX == 1){
					if(num1 == false){
						num1 =  true;
						X_index = j;
						Y_index = temp;
					}
					else{
						isPossible = false;
					}
				}
				
			}
			
			if(isPossible == false || num1 == false){
				System.out.println("Case #"+i+": IMPOSSIBLE");
				continue;
			}
			
			numX =0;
			num1 = false;
			for(int j=0; j<cols; j++){
				numX= 0;
				
				for(int k=0;k<rows; k++){
					if(matrix[k][j] == 'X'){
						numX++;
						temp = k;
					}
							
				}
				
				if (numX > 2 || numX < 1){
					isPossible = false;
					break;
				}
				else if(numX == 1){
					if(num1 == false){
						num1 =  true;
						X_index1 = temp;
						Y_index1 = j;
					}
					else{
						isPossible = false;
						break;
					}
				}
				
			}
			
			if(isPossible == false || num1 == false || X_index != X_index1 || Y_index != Y_index1){
				System.out.println("Case #"+i+": IMPOSSIBLE");
				
			}
			else{
				System.out.println("Case #"+i+": POSSIBLE");
				
			}
			
			
		}
		
		in.close();
	}

}

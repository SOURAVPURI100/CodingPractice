
import java.util.*;
public class RedBlue {

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		
		String input = in.nextLine();
		String pattern = in.nextLine();
		int ans = wordpattern(pattern,  input);
		
		System.out.println(ans);
	}
	// abba   -> redbluebluered
    static int wordpattern(String pattern, String input) {
        Map<Character, String> temp_map = new HashMap<>();
        return( wordpatternTemp(pattern, 0, pattern.length() -1, 
                            input, 0, input.length()-1, temp_map));
        
        
    }
// abba   -> redbluebluered = 1
    // abba -> redredredred = 0
    static int wordpatternTemp (String pattern, int beg_pat, int end_pat, 
                            String input, int beg_inp, int end_inp, Map<Character, String> map){
        
        if(end_pat < beg_pat && end_inp < beg_inp){
        	// Check unique values for pattern characters
        	if(checkUnique(map))
        		return 1;
        	return 0;
        }
        else if(end_pat < beg_pat || end_inp < beg_inp)
            return 0;
        
        String temp= "";
        for(int i =beg_inp; i<= end_inp; i++){
            temp += input.charAt(i);
            
            Map<Character, String> temp_map = new HashMap<>(map);
            
            if(temp_map.containsKey(pattern.charAt(beg_pat))){
                if(temp_map.get(pattern.charAt(beg_pat)).equals(temp)){
                    // Do nothing
                }
                else if(temp_map.get(pattern.charAt(beg_pat)).length() > temp.length()){
                    continue;
                }
                else{
                    return 0;
                }
                
            }
            else{
                temp_map.put(pattern.charAt(beg_pat), temp);
            }
           
            int ret = wordpatternTemp (pattern, beg_pat+1, end_pat, 
                            input, i+1, end_inp, temp_map);
            if(ret == 1)
                return 1;
               
        }
        return 0;
        
        
    }
    
    static boolean checkUnique(Map<Character, String> map){
    	
    	Set<String> set = new HashSet<>();
    	for(char ch: map.keySet()){
    		if(set.contains(map.get(ch)))
    			return false;
    		set.add(map.get(ch));
    		
    	}
    	
    	return true;
    }

}


import java.util.*;

class City{
	int startTime;
	int freq;
	int dur;
}
public class SightSeeing {
	static int globalNum = -1;
	static int[] res;
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		int test  = Integer.parseInt(in.nextLine());
		Map<Integer, City> map = new HashMap<>();
		for(int i = 1; i<=test; i++){
			
			int totalCities = in.nextInt();
			int timeperCity = in.nextInt();
			int finalTime = in.nextInt();
			globalNum = -1;
			res = new int[totalCities];
			for(int j =1; j<totalCities; j++){
				
				City city = new City();
				city.startTime = in.nextInt();
				city.freq = in.nextInt();
				city.dur = in.nextInt();
				
				map.put(j, city);
				
			}
			
			boolean result = getMaximumCities(1, 0, map, timeperCity, finalTime, totalCities , 0);
			
			if(globalNum == -1)
				System.out.println("Case #"+i+": IMPOSSIBLE");
			else
				System.out.println("Case #"+i+": "+globalNum);
			
			
		}
		

	}
	
	public static boolean getMaximumCities(int city, int time, Map<Integer, City> map, int timePerCity, 
													int finalTime, int finalCity, int num){	
		if(time > finalTime)
			return false;;
		if(city == finalCity){
			if(num > globalNum)
				globalNum = num;
			return true;
		}
		
		if(res[city] == time && res[city] != 0)
			return true;
		
		if((num + finalCity - city) <= globalNum)
			return true;
		
		int nextTime = getNextCityTime(time + timePerCity, map.get(city));
		boolean val1 = getMaximumCities(city+1, nextTime, map, timePerCity, finalTime, finalCity, num+1);
		
		nextTime = getNextCityTime(time , map.get(city));
		boolean val2 = getMaximumCities(city+1, nextTime, map, timePerCity, finalTime, finalCity, num);
		
		res[city] = time;
		return (val1 || val2);
	}
	
	public static int getNextCityTime(int time, City city){
		
		int startTime = city.startTime;
		while(time > startTime){
			startTime += city.freq;
		}
		
		return startTime + city.dur;
		
	}

}













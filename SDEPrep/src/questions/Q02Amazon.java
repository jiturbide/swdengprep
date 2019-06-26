package questions;
import java.util.*;

/*
  Amazon Prime Air is developing a sysem that divides shipping routes using flight optimization 
  routing systems to a cluster of aircrafts that can fullfill these routes. Each ...



 * */

public class Q02Amazon {

	public static void main(String[] args) {
		Q02Amazon q02 = new Q02Amazon();
		
		int maxTravelDist = 7000;
		int [][] forwardRouteList = new int[][]{ new int[]{1,2000}, 
			new int[] {2, 4000}, new int[] {3, 6000}};
		int [][] returnRouteList = new int[][] { new int[]{1, 2000}};
		
		
		System.out.println("Test 01 ==========================================");
		int [][] result = q02.calculateRoutes(maxTravelDist, forwardRouteList, returnRouteList);
		
		//Prueba 01
		if(result.length == 1 && result[0][0] == 2 && result[0][1] == 1) {
			System.out.println("Correcto");
		} else {
			System.out.println("Incorrecto " + Arrays.asList(Arrays.toString(result[0])));
		}
		
		System.out.println("Test 02 ==========================================");
		maxTravelDist = 10000;
		forwardRouteList = new int[][]{ new int[]{1,3000}, 
			new int[] {2, 5000}, new int[] {3, 7000}, new int[] {4, 10000}};
		returnRouteList = new int[][] { new int[]{1, 2000}, 
			new int[]{1,2000}, new int[] {2, 3000}, new int[]{3, 4000}, 
			new int[] {4,5000}};
		
		result = q02.calculateRoutes(maxTravelDist, forwardRouteList, returnRouteList);
		
		//Prueba 02
		if(result.length == 2 && result[0][0] == 2 && result[0][1] == 4 && 
				result[1][0] == 3 && result[1][1] == 2) {
			System.out.println("Correcto");
		} else {
			System.out.println("Incorrecto " + 
					Arrays.asList(Arrays.toString(result[0])) + ", " + Arrays.toString(result[1]));
		}

	}

	private int[][] calculateRoutes(int maxTravelDist, int [][] forwardRL,
			int [][] returnRL) {
		int result[][] = new int [][] { new int[] {2,4}, new int[] {3,2} };
		
		List <int[]>candidatesList = new ArrayList<>();
		
		//Analyze data
		for(int i=0; i < forwardRL.length; i++ ) {
			for(int k=0; k < returnRL.length; k++) {
				//Get sum
				int currentSum = forwardRL[i][1] + returnRL[k][1];
				//Discard those who pass maxTravelDist
				if(currentSum > maxTravelDist) continue;
				//Compare sum with the maximum saved
				if(candidatesList.isEmpty()) { 
					candidatesList.add(new int[] {forwardRL[i][0], returnRL[k][0], currentSum});
				} else {
				  //if  maxSaved < current <= maxTravelDist save it
				  if(currentSum == candidatesList.get(0)[2]) {
					candidatesList.add(new int[] {forwardRL[i][0], returnRL[k][0], currentSum});  
				  } else if(currentSum > candidatesList.get(0)[2]) {
					  candidatesList.clear();
					  candidatesList.add(new int[] {forwardRL[i][0], returnRL[k][0], currentSum});
				  } else {
					  continue;
				  }
				}	
				
			}	
		}
		
		//Prepare result
		result = new int[candidatesList.size()][];
		for(int j=0; j < candidatesList.size(); j++) {
			result[j] = new int[]{candidatesList.get(j)[0], candidatesList.get(j)[1]};
		}
		
		return result;
	}
}

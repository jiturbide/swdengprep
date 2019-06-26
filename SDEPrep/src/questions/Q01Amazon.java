package questions;

import java.util.*;
import java.util.regex.Pattern;

public class Q01Amazon {

	public static void main(String[] args) {
		Q01Amazon q = new Q01Amazon();
		
		System.out.println("-----------------------");
		List<String> ol = new ArrayList<>();
		
		System.out.println("Test 01 .....................");
		/* * /
		ol.add("a006 66666");
		ol.add("a001 tshrt");
		ol.add("a008 dress8");
		ol.add("a003 33333");
		ol.add("a007 dress");
		ol.add("a004 dress");
		ol.add("a002 pantsx ");
		ol.add("a005 55555");
		ol.add("b009 shoes");		
		/* */
		/* */
		ol.add("a001 tshrt www");
		ol.add("a008 dress8 666 444");
		ol.add("a003 33333 444");
		ol.add("a007 dress aaa ccc");
		ol.add("a004 dress aaa ccc");
		ol.add("a004 dress aaa ccc");
		ol.add("a002 pantsx zzz bbb");
		ol.add("a005 55555");
		ol.add("a006 66666 444");
		ol.add("b009 shoes qqq ccc");
		/* */

		q.prioritizeOrders(10, ol);
		//boolean incorrect = false;
		/* * /
		if(!rl.get(0).equals("a004 dress aaa ccc")) incorrect = true;
		if(!rl.get(1).equals("a007 dress aaa ccc")) incorrect = true;
		if(!rl.get(2).equals("a001 dress7 666 444")) incorrect = true;
		if(!rl.get(3).equals("a002 pantsx zzz bbb")) incorrect = true;
		//if(!rl.get(4).equals("b009 shoes qqq ccc")) incorrect = true;		
		if(!rl.get(4).equals("a001 tshrt www")) incorrect = true;
		if(!rl.get(5).equals("a003 55555 444")) incorrect = true;
		if(!rl.get(6).equals("a005 44444")) incorrect = true;
		if(!rl.get(7).equals("a006 33333 444")) incorrect = true;
		
		
		System.out.println("Result 01 Correcto: " + !incorrect);
		System.out.println("");

		System.out.println("Test 02 .....................");
		ol.add("a001 tshrt www");
		ol.add("a008 dress7 666 444");
		ol.add("a003 55555 444");
		ol.add("a004 dress aaa ccc dddd");
		ol.add("a004 dress aaa ccc");
		ol.add("a002 pantsx zzz bbb");
		ol.add("a005 44444");
		ol.add("a006 33333 444");
		ol.add("b009 shoes qqq ccc");		
		
		rl = q.prioritizeOrders(9, ol);
		incorrect = false;
		
		if(!rl.get(0).equals("a004 dress aaa ccc")) incorrect = true;
		if(!rl.get(1).equals("a004 dress aaa ccc dddd")) incorrect = true;
		if(!rl.get(2).equals("a008 dress7 666 444")) incorrect = true;
		if(!rl.get(3).equals("a002 pantsx zzz bbb")) incorrect = true;
		if(!rl.get(4).equals("b009 shoes qqq ccc")) incorrect = true;		
		if(!rl.get(5).equals("a001 tshrt www")) incorrect = true;
		if(!rl.get(6).equals("a003 55555 444")) incorrect = true;
		if(!rl.get(7).equals("a005 44444")) incorrect = true;
		if(!rl.get(8).equals("a006 33333 444")) incorrect = true;
		
		
		System.out.println("Result 02 Correcto: " + !incorrect);
		/* */
		
		//System.out.println("Non Prime: " + q.isNonPrime("a003 55555 444"));
		//System.out.println("Non Prime: " + q.isNonPrime("a008 dress7 666 444"));
		/* * /
		boolean c1 = q.lessThan("a004 dress aaa ccc", "a004 dress aaa ccc dddd");
		System.out.println("Comparing " + c1);
		boolean c2 = q.lessThan("a004 dress aaa ccc", "a004 dress aaa ccc");
		System.out.println("Comparing " + c2);
		boolean c3 = q.lessThan("a004 dress aaa ccc dddd", "a004 dress aaa ccc");
		System.out.println("Comparing " + c3);
		/* */
		
	}

	protected List<String> prioritizeOrders(int numOrders, List<String> orderList) {
		
		//Bubble sort
		for(int reg=0; reg < numOrders; reg++) {
			for(int n=numOrders-1; n > 0 ; n--) {							
				this.swapIfApplies( orderList, n-1, n);
			}
		}
		
		return orderList;
	}
	
	private boolean isPrime(String item) {
		int pos1StSep = item.indexOf(" ");
		String metadata = item.substring(pos1StSep);
		
		boolean isPrime = ! Pattern.matches("[0-9 ]+", metadata);
		//System.out.println("Prime: " + item + ", " + isPrime);
		
		return isPrime;
	}
	
	private void swapIfApplies(List <String>orderList, int ixa, int ixb) {
		String a = orderList.get(ixa);
		String b = orderList.get(ixb);
		int pos1StSep = a.indexOf(" ");
		String metaA = a.substring(pos1StSep+1);
		String keyA = a.substring(0, pos1StSep);
		
		pos1StSep = b.indexOf(" ");
		String metaB = b.substring(pos1StSep+1);
		String keyB = b.substring(0, pos1StSep);

		boolean swap = false;
		//1. Two non prime, keep it as is, do nothing
		//2. NonPrime and Prime => Swaps
		if(isPrime(a) == false && isPrime(b)) { swap =true;}
		//3. Prime and NonPrime => Don't Swap
		//4. If Prime && Prime, evalutes the key
		if(isPrime(a) && isPrime(b)) {
			if(metaA.compareTo(metaB) > 0) { swap = true; }
			if(metaA.compareTo(metaB) == 0 ) {
			  if(keyA.compareTo(keyB) > 0) { // KeyA > keyB
				swap = true;
			  } //keyA < keyB or keyA == keyB => Do nothing
			}
		}
		
        if(swap) {
            System.out.println(orderList);
        	System.out.println("swap " + a + " <-> " + b);
        	orderList.set(ixa, b);
        	orderList.set(ixb, a);
            System.out.println(orderList);
            System.out.println();

        }


	}
}


/*
1. Prime orders should be returned first sorted by lexicographic sort of the aphabetic metadata.
2. Only in case of ties the identifier should be used as backup sort
3. The remaining non Prime orders must come all after in the original order they were given in the input.


*/
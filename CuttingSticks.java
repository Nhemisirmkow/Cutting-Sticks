/**
 * @author Marcin
 *			 Michorzewski
 * @task
 * 		   Cutting Sticks
 */

import java.util.Scanner;

public class CuttingSticks {

	public static final int MAX_INT = 99999999;
	
	private static int cutDirectly(int l, int r, int[] piecesLength){
		int min = MAX_INT;
		int temp = -1;
		int stickLength = piecesLength[l];
		for (int i = l; i < r; i++){
			temp = cutDirectly(l, i, piecesLength) + cutDirectly(i + 1, r, piecesLength);
			if (min > temp) 
				min = temp;
			stickLength += piecesLength[i + 1]; /* that goes from (l + 1) to r */
		}
		if (min > temp) min = temp; /* if (l >= r) */
		if (min == -1) return 0; /* no cuts needed */
		else 
			return (min + stickLength);
	}                                                           
	                                                            
	private static int cut(int stickLength, int[] cutPlaces){
		int[] piecesLength;
		piecesLength = new int[cutPlaces.length + 1];
		int lastStop = 0; /* temp int used to remember when last piece ended */
		for (int i = 0; i < cutPlaces.length; i++){
			piecesLength[i] = cutPlaces[i] - lastStop;
			lastStop = cutPlaces[i];
		}
		piecesLength[cutPlaces.length] = stickLength - lastStop;
		return cutDirectly(0, cutPlaces.length, piecesLength);
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int length = scanner.nextInt();
		int piecesNumber;
		while (length != 0){
			piecesNumber = scanner.nextInt();
			int[] cutPlaces;
			cutPlaces = new int[piecesNumber];
			for (int i = 0; i < piecesNumber; i++){
				cutPlaces[i] = scanner.nextInt();
			}
			System.out.println("The minimum cutting is " + cut(length, cutPlaces) + ".");
			length = scanner.nextInt();
		}
		scanner.close();
	}

}

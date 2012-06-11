package me.botsko.mythos.utilities;

import java.util.List;
import java.util.Random;

import me.botsko.mythos.MythosWeighted;

public class WeightedRandom {

	
	/**
	 * Gets a random number
	 * @return
	 */
	public static int getRandomNumber( Integer max ){
		Random randomGenerator = new Random();
		return randomGenerator.nextInt(max);
	}
	
	
	/**
	 * 
	 * @param data
	 * @param weight
	 * @return
	 */
	public static int getWeightedRandomNumber( int[] data, int[] weight ){
		Random randomGenerator = new Random();
		int totalWeight = sum(weight);
		int n=randomGenerator.nextInt(totalWeight);
		int runningTotal=0;
		for (int i=0;i<weight.length;i++){
		  runningTotal+=weight[i];
		  if (n<runningTotal) return data[i];
		}
		return -1;
	}
	static int sum(int[] a){
		int s=0;
		for (int i=0;i<a.length;i++) s+=a[i];
		return s;
	}

	
	/**
	 * 
	 * @param items
	 * @return
	 */
	public static MythosWeighted chooseOnWeight(List<MythosWeighted> items) {
		
		// Set total weight for items
        double completeWeight = 0.0d;
        for (MythosWeighted item : items){
            completeWeight += item.getWeight();
        }

        // Choose a random item
        double rand = Math.random();
        double r = rand * completeWeight;

        double countWeight = 0.0;
        for (MythosWeighted item : items) {
            countWeight += item.getWeight();
            if (countWeight >= r){
                return item;
            }
        }
        return null;
    }
}
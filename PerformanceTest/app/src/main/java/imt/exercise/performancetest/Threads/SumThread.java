package imt.exercise.performancetest.Threads;

import imt.exercise.performancetest.Activities.SumActivity;
import imt.exercise.performancetest.OtherUtils.ArrayDimension;

public class SumThread extends Thread {
	
	private int[] array = null;
	private ArrayDimension dim = null;
	private int partialSum = 0;
	private int multiplier = 0;
	
	public SumThread(int[] array, ArrayDimension dim, int multiplier){
		super();
		this.array = array;
		this.dim = dim;
		this.multiplier = multiplier;
	}
	
	public void run() {
		for (int j = 0; j < multiplier; j++)
			for (int i = dim.startDimension; i < dim.endDimension; i++)
				partialSum += array[i];
		SumActivity.setSum(partialSum + SumActivity.getSum());
	}
	
}

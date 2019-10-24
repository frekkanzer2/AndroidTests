package imt.exercise.performancetest.Threads;

import imt.exercise.performancetest.OtherUtils.ArrayDimension;

public class InizializationThread extends Thread {

	int[] array = null;
	ArrayDimension dim = null;
	int valueToSet = 0;
	int multiplier = 1;
	
	public InizializationThread(int[] array, ArrayDimension dim, int valueToSet, int mult) {
		super();
		this.array = array;
		this.dim = dim;
		this.valueToSet = valueToSet;
		this.multiplier = mult;
	}
	public InizializationThread(int[] array, ArrayDimension dim, int mult) {
		super();
		this.array = array;
		this.dim = dim;
		this.valueToSet = 1;
		this.multiplier = mult;
	}
	
	public void run() {
		for (int multIndex = 0; multIndex < this.multiplier; multIndex++)
			for (int index = this.dim.startDimension; index < this.dim.endDimension; index++)
				this.array[index] = this.valueToSet;
	}
	
}

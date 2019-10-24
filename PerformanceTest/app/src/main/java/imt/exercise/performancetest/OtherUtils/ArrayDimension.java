package imt.exercise.performancetest.OtherUtils;

public class ArrayDimension {
	
	public ArrayDimension(int startDimension, int endDimension) {
		this.startDimension = startDimension;
		this.endDimension = endDimension;
	}
	
	public int startDimension = 0;
	public int endDimension = 0; //Should be N, not N-1
	
}

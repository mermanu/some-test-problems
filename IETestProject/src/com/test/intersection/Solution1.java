package com.test.intersection;

import java.util.*;

/**
 * The Class Solution.
 */
public class Solution1 {
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {		
		Solution1 solution=new Solution1();	
		Integer[][] entry = {{2,4},{5,10},{7,11},{3,6},{9,12},{9,15}};
		//Integer[][] entry = {{2,4},{4,5},{5,10},{11,13}};
		//Integer[][] entry = {{2,4},{5,10},{11,13}};
		solution.solution(entry);		
	}
	
	/**
	 * Solution.
	 *
	 * @param intervals the intervals
	 * @return the integer[][]
	 */
	public Integer[][] solution(Integer[][] intervals){
		TreeMap<Integer,ArrayList<Interval>> lengthValues = new TreeMap<Integer, ArrayList<Interval>>(); 
		Integer[][] result=null;
		
		Solution1 solution=new Solution1();
		//Primitive to ArrayList
		ArrayList<Interval> entry = new ArrayList<Solution1.Interval>();
		for(Integer[] interval : intervals){			
			entry.add(solution.new Interval(interval[0], interval[1]));			
		}
		intervals=null;
		
		//execute to find intervals, length and relative nodes
		solution.execute(entry, lengthValues);
		
		//ArrayList to primitive		
		if(lengthValues.size()>0){			
			System.out.println("Intersections length "+lengthValues.lastKey() );
			ArrayList<Interval> maxIntervals=lengthValues.get(lengthValues.lastKey());
			result=new Integer[maxIntervals.size()][2];	
			//generate primitive type
			for(int i = 0; i < maxIntervals.size(); i++){
				result[i][0]=maxIntervals.get(i).start;
				result[i][1]=maxIntervals.get(i).end;
				System.out.println(result[i][0]+","+result[i][1]);
			}	
		}else{
			System.out.println("null no intersection");
		}
		return result;
	}
	
	

	/**
	 * Merge.
	 *
	 * @param intervals the intervals
	 * @return the array list
	 */
	public void execute(ArrayList<Interval> intervals, Map<Integer,ArrayList<Interval>> lengthValues) {	   
		//first sort all Interval values
		sortIntervals(intervals);	    
		
	    //get length and assign relative nodes
	    for (int i = 0; i < intervals.size(); i++) {
	        Interval cur = intervals.get(i);
	        
	        if (i==0 && intervals.size()>1) {
	        	cur.right=intervals.get(i+1);	        
	        } else {
	            Interval last = intervals.get(i-1);
	            if (last.end >= cur.start) {
	            	cur.length=last.end-cur.start;
	            	cur.left=last;
	            	last.right=cur;
	            	setCurrentNodeToTreeMap(lengthValues, cur);
	            }
	        }
	    }	    
	}
	
	/**
	 * Sort intervals.
	 *
	 * @param intervals the intervals
	 */
	private void sortIntervals(ArrayList<Interval> intervals){		 
	    Comparator<Interval> comparator = new Comparator<Interval>() {
	        @Override
	        public int compare(Interval i1, Interval i2) {
	            if (i1.start < i2.start){
	                return -1;
	            }else if (i1.start > i2.start){
	                return 1;
	            }else {
	                if (i1.end < i2.end)
	                    return -1;
	                else if (i1.end > i2.end)
	                    return 1;
	                else
	                    return 0;
	            }
	        }
	    };
	    Collections.sort(intervals, comparator);
	}
	
	/**
	 * Sets the current node to tree map.
	 *
	 * @param lengthValues the length values
	 * @param cur the cur
	 */
	private void setCurrentNodeToTreeMap(Map<Integer,ArrayList<Interval>> lengthValues, Interval cur){
		if(lengthValues.get(cur.length)!=null){
    		lengthValues.get(cur.length).add(cur.left);
    		lengthValues.get(cur.length).add(cur);	            		
    	}else{
    		ArrayList<Solution1.Interval> values = new ArrayList<Interval>();
    		values.add(cur.left);	
    		values.add(cur);
    		lengthValues.put(cur.length, values);	            		            		
    	}
	}
	
	/**
	 * The Class Interval.
	 */
	public class Interval{
		
		/** The start. */
		private int start;
		
		/** The end. */
		private int end;
		
		/** The length. */
		private int length=-1;
		
		/** The left. */
		private Interval left;
		
		/** The right. */
		private Interval right;
		
		/**
		 * Instantiates a new interval.
		 *
		 * @param start the start
		 * @param end the end
		 */
		public Interval(int start, int end) {
			super();
			this.start = start;
			this.end = end;
		}		
	}
}

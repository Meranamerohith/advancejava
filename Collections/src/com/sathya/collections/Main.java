package com.sathya.collections;
import java.util.*;

public class Main {

	public static void main(String[] args) {
		
          List<Population> populationData = new ArrayList<Population>();
          
          populationData.add(new Population("India",1380004385,"INR","Delhi",28));
          populationData.add((new Population("United States",331002651,"USD","Washington",50)));
          
          System.out.print(populationData.get(1));
          
	}

}

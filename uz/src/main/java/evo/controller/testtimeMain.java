package evo.controller;

import java.text.ParseException;
import java.util.ArrayList;

import evo.model.UserMonthTime;

public class testtimeMain {



	/**
	 * @param args
	 * @throws ParseException
	 */
	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub

		ArrayList<Item> temp = testlist.test3in();
		//ArrayList<Item> temp = testexample.testnormal();
		//ArrayList<Item> temp = testexample.test3inotherout();
		
		//ArrayList<Item> temp = testexample.test6inotherout();
		//ArrayList<Item> temp = testexample.test8inotherout();
		//ArrayList<Item> temp = testexample.test12inotherout();
		//ArrayList<Item> temp = testexample.test14inotherout();
		//ArrayList<Item> temp = testexample.test18inotherout();
		
	
		UserMonthTime  userMonthTime= computeTime.getTime(temp,null);
		if(userMonthTime==null)
		{
			System.out.println("error----occured");
			return;
		}
		
	}






	
}

package com.demo.test;

public class test4 {

	public static void main(String[] args) {

		String num = "05/05/2020 09:02 AM EDT - 05/05/2020 09:10 AM EDT (9 minutes),05/05/2020 09:10 AM EDT - 05/05/2020 10:05 AM EDT (55 minutes),05/05/2020 10:09 AM EDT - 05/05/2020 12:05 PM EDT (1 hour 57 minutes)";
		char[] nu = num.toCharArray();
		String nownum = "";
		boolean flag = false;
		int res = 0;
		
		for (int i = 0; i < nu.length; i++) {
			if (Integer.valueOf(nu[i]) == '(')
				flag = true;
			if (Integer.valueOf(nu[i]) == ')')
				flag = false;
			if(flag == true) {
				if (Integer.valueOf(nu[i]) >= 48 && Integer.valueOf(nu[i]) <= 57)
					nownum = nownum + nu[i];
				else if (Integer.valueOf(nu[i]) =='h') {
					res += Integer.parseInt(nownum) * 60;
					nownum = "";
				}
				else if (Integer.valueOf(nu[i]) =='m') {
					res += Integer.parseInt(nownum) * 1;
					nownum = "";
				}	
			}
		}
		System.out.print(res + "minutes");
	}
}

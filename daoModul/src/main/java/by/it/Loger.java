package by.it;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Loger {        // class log file
	PrintWriter fLog;
	public void logWrite(String str){		
			try {
				fLog = new PrintWriter(new BufferedWriter(new FileWriter("D:\\kurs\\workspase\\LentaNewsDao\\loger.txt", true)));								
			} catch (IOException e) {
				System.out.println("Error loger!");
				return;
			}
			fLog.println(str);
			fLog.flush();
			fLog.close();
	}

}

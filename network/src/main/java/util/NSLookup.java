package util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class NSLookup {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		while (true) {
			System.out.print("> ");
			String domainName = sc.nextLine();
			
			if ("exit".equals(domainName)) {
				break;
			}
			
			printAllInetAdresses(domainName);
		}
		
		sc.close();
	}
	
	private static void printAllInetAdresses(String name) {
		try {
			InetAddress[] InetAddresses = InetAddress.getAllByName(name);
			
			for(InetAddress inetAddress : InetAddresses) {
				System.out.println(name + " : " + inetAddress.getHostAddress());
			}
		} catch (UnknownHostException e) {
			System.out.println("unknown host (" + e.getMessage() + ")");
		}
	}
}
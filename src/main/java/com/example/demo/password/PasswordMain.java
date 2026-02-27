package com.example.demo.password;

public class PasswordMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String saltKey = "do/e2KAP4v5xAewSlQ3D3Q==";
		String pwd = "1111";
		
		System.out.println("===============시작===============");
		System.out.println(SecurityUtil.getSecureString(pwd, saltKey));
		System.out.println("===============끝===============");
	}

}

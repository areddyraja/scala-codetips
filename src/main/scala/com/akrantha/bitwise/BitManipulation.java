package com.akrantha.bitwise;

public class BitManipulation {

	public static void main(String[] args) {
		System.out.println(getBit(5, 2));
		System.out.println(setBit(5, 3));
		System.out.println(clearBit(5, 3));
		print(clearnBitsMSBThroughI(15, 3));
		print(clearBitsThrough0(31, 3));
		print(updateBit(13, 3, 1));
		print(convertBinary(100));
		print(calculateBinary(100));
		print(printBinary(0.72));
		
	}

	// 00000101 & 00001000 = 1
	static boolean getBit(int num, int i) {
		return ((num & (1 << i)) != 0);
	}

	// 00000101 | 00001000 = 13
	static int setBit(int num, int i) {
		return (num | (1 << i));
	}

	static int clearBit(int num, int i) {
		return (num & ~(1 << i));
	}

	// 00001101 => 0000 0101
	static int clearnBitsMSBThroughI(int num, int i) {
		print(num & (1 << i) - 1);
		return 0;
	}

	static int clearBitsThrough0(int num, int i) {
		print("clear: " + ((1 << (i + 1)) - 1));
		return num & ~((1 << (i + 1)) - 1);
	}

	static int updateBit(int num, int i, int v) {
		int mask = ~(1 << i);
		return (num & mask) | (v << i);
	}

	static void print(int x) {
		System.out.println(x);
	}

	// chapter 5
	// Given a real number between 0 and 7 (e.g., 0.72) that is passed in as a
	// double, print
	// the binary representation. If the number cannot be represented accurately
	// in binary
	// with at most 32 characters, print "ERROR."

	public static String printBinary(double num) {
		if (num < 0 || num > 1)
			return "ERROR";
		StringBuilder build = new StringBuilder();
		build.append(".");
		double frac = 0.5;
		while (num > 0) {
			
			if (build.length() >= 32) {
				return "Error";
			}

			if (num > frac) {
				build.append(1);
				num -= frac;
			} else {
				build.append(0);
			}
			frac /= 2;
		}
		return build.toString();
	}

	// convert a number to binary
	static int[] convertBinary(int no) {
		int i = 0, temp[] = new int[7];
		int binary[];
		while (no > 0) {
			temp[i++] = no % 2;
			no /= 2;
		}
		binary = new int[i];
		int k = 0;
		for (int j = i - 1; j >= 0; j--) {
			binary[k++] = temp[j];
		}

		return binary;
	}

	static StringBuffer calculateBinary(int number) {
		StringBuffer sBuf = new StringBuffer();
		int temp = 0;
		while (number > 0) {
			temp = number % 2;
			sBuf.append(temp);
			number = number / 2;
		}
		return sBuf.reverse();
	}

	static void print(Object x) {
		System.out.println(x);
	}
}

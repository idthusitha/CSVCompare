import java.util.ArrayList;

public class Demo {

	public static void main(String[] args) {

		////1,NEW,,100000,RAIL 1000,1000,"4353435,7654345, 6353437",TRANSPORT,"CHICAGO, IL","HOUSTON, TX",NS-CHGO-CN,B,CAD,6543,6543,5633,CAR,1,278666,1,7/23/2017,12/31/2017,10000,10000,10000,,,,,,,N,B,AAD 332,GROUP TO GROUP,APPLY HERE IN DIFFERENT SERVICES,ONE_CAR
		
		String line = "1,,\"4353435,7654345, 6353437\",sfsfsf,sf,s,fs,fs,fs,fs,fs,f,\"sfs,fsfsfs\"";
		ArrayList<String> rowString = new ArrayList<String>();

		String letterTemp = "";
		for (String letter : line.split("")) {

			if (",".equals(letter) && !letterTemp.startsWith("\"")) {
				rowString.add(letterTemp);
				letterTemp = "";

			} else if (letterTemp.length() != 1 && letterTemp.startsWith("\"") && letterTemp.endsWith("\"")) {
				rowString.add(letterTemp);
				letterTemp = "";
			}			
			letterTemp += !",".equals(letter) ? letter : letterTemp.startsWith("\"") ? letter : "";
		}
		rowString.add(letterTemp);

		for (int i = 0; i < rowString.size(); i++) {
			System.out.println("row==>" + i + ":" + rowString.get(i));
		}

	}

}

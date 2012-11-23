package fileHandler;
import java.io.*;
import ResImpl.*;
import java.util.*;


public class FileHandler{

	/*public static void main(String args[]){
		FileHandler file=new FileHandler();
		file.write("test.txt","key data");
		file.write("test.txt","last line");
		System.out.println(file.read("test.txt"));
		System.out.println(file.read("test.txt","key"));
		RMHashtable hash= file.recover("test.txt");
		for(Enumeration e=hash.elements();e.hasMoreElements();){
			System.out.println(e.nextElement());
		}
	}
	*/
	private BufferedWriter openW(String fileName ){
		FileWriter fstream=null;
		BufferedWriter br=null;
		try{
			fstream = new FileWriter(fileName,true);
            		br = new BufferedWriter(fstream);	
			
		 }catch(Exception e){
		 	e.printStackTrace();
		 }
		 return br;

	}



	private BufferedReader openR(String fileName){
		DataInputStream d=null;	
		BufferedReader br=null;
		try{
			FileInputStream in=new FileInputStream(fileName);
			d=new DataInputStream(in);
			 br=new BufferedReader(new InputStreamReader(d));
			
		}catch(Exception  e){
			e.printStackTrace();
		}
		return br;
		
	}


	void write(String fileName,String data){
		try{
		
			BufferedWriter out =openW(fileName);
			out.write(data);
			out.newLine();	//TODO:might cause problems in read last line method. test.
			out.close();
		}catch(Exception e ){
			e.printStackTrace();
		}
	}

	String read(String fileName,String key){	// read on basis of key
		BufferedReader in=openR(fileName);
		String tempArr[]=new String[2];
		String temp=null;
		try{
			while (((temp=in.readLine())!=null)){ //TODO: check for errors
				tempArr=temp.split(" ");
				if(tempArr[0].equals(key)){
					return temp;
					
				}
			}
			in.close();
		}catch(Exception e ){
			e.printStackTrace();
		}
		
		return null;
	}
	String read(String fileName){	//read last line
		BufferedReader in=openR(fileName);
		String temp,lastLine=null;
		try{
			
			while(((temp=in.readLine())!=null)){
				lastLine=temp;
			}
			in.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return lastLine;
		
		
	}

	RMHashtable recover(String fileName){
		
		BufferedReader br= openR(fileName);
		RMHashtable hash=new RMHashtable();
		String temp;
		String [] tempArr;
		try{
			temp=br.readLine();
			while(temp!=null){
				tempArr=temp.split(" ");
				hash.put(tempArr[0],tempArr[1]);
				temp=br.readLine();
			}
			br.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return hash;
	}

}


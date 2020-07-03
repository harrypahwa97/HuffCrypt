import java.util.*;
import java.io.*;

public class Hauffman_file_compression {
	
	public static void encode(Data_Class root, String str,
			HashMap<String, String> huffmanCode){                                 //this function generates the 
     																			  //code corresponding to each			
		if (root == null)														  //character using recursion
			return;																  //and stores in HashMap.

		if (root.left_child == null && root.right_child == null) {
			huffmanCode.put(root.string, str);
		}
		
		encode(root.left_child, str + "0", huffmanCode);
		encode(root.right_child, str + "1", huffmanCode);
	}
	
	public static void main(String args[]) throws IOException {					
		
		Scanner s = new Scanner(System.in);
		int ch; 
		FileReader fr=null; 
		// check if File exists or not 
		try
		{ 
			fr = new FileReader("input.txt");                                     //opening the input file
			HashMap<String,Integer> frequency_list=new HashMap<String,Integer>(); //frequency HashMap
			
			while ((ch=fr.read())!=-1){											  //reading the file
				String str=Character.toString((char)ch);						  //conversion of character input to string
				
				if(frequency_list.containsKey(str))								  //creating a HashMap storing character and
					frequency_list.put(str,frequency_list.get(str)+1);			  //its frequency.
				else
					frequency_list.put(str,1);
			}
			fr.close();
			
			Set<String> keys_list = frequency_list.keySet();
			Min_Priority_Queue pq=new Min_Priority_Queue();						  //formation of minimum priority queue
			System.out.println("frequency list for each character");
			
			for(String a : keys_list) {											  //addition of each element in min heap
				Data_Class fc=new Data_Class(a,frequency_list.get(a));
				pq.insert(fc);
				System.out.println(a+" : "+frequency_list.get(a));
			}
			
			while(pq.getSize()!=1){												  //formation of huffman tree
				Data_Class temp=pq.removeMin();
				Data_Class temp2=pq.removeMin();
				Data_Class temp3=new Data_Class(temp.string+temp2.string,temp.frequency+temp2.frequency);
				temp3.left_child=temp;
				temp3.right_child=temp2;
				pq.insert(temp3);
			}
			
			Data_Class temp2=pq.getMin();
			HashMap<String, String> huffmanCode = new HashMap<String,String>();	  //HashMap that will have codes
			encode(temp2, "", huffmanCode);										  //calling of encode function 
			Set<String> code_list = huffmanCode.keySet();
			System.out.println("hauffman code generated for each character");
			
			for(String a : code_list) {											  
				System.out.println(a + " : " + huffmanCode.get(a));
			}
            
			//ENCODING
			
			// check if File exists or not 
			FileReader f=null; 
			try
			{ 
				int hc;															  //in these lines encoded file is 
				String st="";													  //generated.by traversing each 
				String code="";													  //character of input file and 
				f = new FileReader("input.txt");								  //writing the code corresponding
				FileWriter fw=new FileWriter("encoded.txt");					  //to the encoded file
				
				while ((hc=f.read())!=-1) {
					st = Character.toString((char)hc);
					code=huffmanCode.get(st);
					for (int i = 0; i < code.length(); i++) 
						fw.write(code.charAt(i));
				}
				
				System.out.println("File encoded"); 
				fw.close(); 
				f.close(); 
			} 
			
			catch (FileNotFoundException fe) 
			{ 
				System.out.println("File not found"); 
			} 
			
			//DECODING
			
			System.out.println("enter 1. to decode the file or else to exit");
			int choice=s.nextInt();
			
			if(choice==1){
				HashMap<String, String> reverse_list = new HashMap<String,String>();
				Set<String> reverse = huffmanCode.keySet();
				
				for(String a : reverse) {											  
					reverse_list.put(huffmanCode.get(a),a);
				}
				
				int ch2;
				FileReader fr2=null; 
				String star="";
				String check=null;
				String flag="";
				// check if File exists or not 
				try
				{ 
					fr2 = new FileReader("encoded.txt");                             //now traversing the encoded file         
					FileWriter fw2=new FileWriter("decoded.txt");					 //and writing the character
					
					while ((ch2=fr2.read())!=-1){									 //corresponding to each code 	
						star=star+Character.toString((char)ch2);
						check=reverse_list.get(star);
						
						if(check!=null){
							fw2.write(check.charAt(0));
							star=flag;
						}
						else
							continue;
					}
					
					fw2.close();
					System.out.println("File decoded");
				}
				
				catch (FileNotFoundException fe) 									 //in case file doesn't exist displays 
				{ 																	 //exception		
					System.out.println("File not found"); 
				} 
			}
			else
				System.out.println("program ended");
			
			s.close();	
		} 
		
		catch (FileNotFoundException fe) 											 //in case file doesn't exist  
		{ 																			 //displays exception		
			System.out.println("File not found"); 
		} 
	}
}
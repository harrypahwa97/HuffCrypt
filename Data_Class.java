class Data_Class {                 //it is class that is used to store each string  
								   //which is character of the input file and its frequency
								   //in the file.
	
	int frequency;                 //it's corresponding frequency
	String string;                 //it's corresponding character as a string
	
	Data_Class left_child=null;
	Data_Class right_child=null;
	
	Data_Class(String c,int f){
		frequency=f;
		string=c;
	}

}

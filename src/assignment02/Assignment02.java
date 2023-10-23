package assignment02;

//najla Alsafadi
//c20312866
//oosdA02


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class Assignment02 {

    public static void main(String[] args) throws IOException{
    	//executor service created
        ExecutorService servicePool = Executors.newSingleThreadExecutor();
        
       //array to store string
        ArrayList<String> arraylist = new ArrayList<String>();
        
        //process builder created
        ProcessBuilder pB = new ProcessBuilder();
        //ping google.com 10 times 
        pB.command("cmd.exe", "/c", "ping -n 10 google.com");  
        Process p = pB.start();
        
        Callable<ArrayList<String>> call = new Read(p);//intiialise call
        
        Future<ArrayList<String>> future = servicePool.submit(call);//get object future
        try {
        	arraylist = future.get();//arraylist is equal to future.get()
     
            for (String s : arraylist) {//this for loop prints out all the values and results withing arraylist
                System.out.println(s);//print out string s
            }
        } catch (InterruptedException | ExecutionException e) {//catch exception
            e.printStackTrace();
        } finally {
        	servicePool.shutdown();//servicePool is shut down when finished running
        }
    }
    public static class Read implements Callable {// instantiate class and implements callable
	    Process p;
	    public Read(Process p) {
            this.p = p;
        }
        @Override
        public ArrayList<String> call() throws Exception{//method which reads the inputstream as a string and returns a string
        	String s;
        	ArrayList<String> string = new ArrayList<String>();
        	BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
        	
        	if((s = br.readLine()) != null)
        	{
        		string.add(s);//add string to the list
        	
        	}
        	return string;//return output
        }
    }
}//END OF PROGRAM

//OUTPUT:]


//Pinging google.com [209.85.203.139] with 32 bytes of data:
//Reply from 209.85.203.139: bytes=32 time=6ms TTL=109
//Reply from 209.85.203.139: bytes=32 time=7ms TTL=109
//Reply from 209.85.203.139: bytes=32 time=8ms TTL=109
//Reply from 209.85.203.139: bytes=32 time=7ms TTL=109
//Reply from 209.85.203.139: bytes=32 time=8ms TTL=109
//Reply from 209.85.203.139: bytes=32 time=8ms TTL=109
//Reply from 209.85.203.139: bytes=32 time=7ms TTL=109
//Reply from 209.85.203.139: bytes=32 time=7ms TTL=109
//Reply from 209.85.203.139: bytes=32 time=8ms TTL=109
//Reply from 209.85.203.139: bytes=32 time=7ms TTL=109

//Ping statistics for 209.85.203.139:
  //  Packets: Sent = 10, Received = 10, Lost = 0 (0% loss),
//Approximate round trip times in milli-seconds:
  //  Minimum = 6ms, Maximum = 8ms, Average = 7ms

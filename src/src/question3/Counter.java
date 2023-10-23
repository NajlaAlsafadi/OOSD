package question3;
//Najla Alsafadi
//c20312866


class Repository {//Repository class
	int index; // Stores the number in the Counter class

	
	public int getIndex() {
		return index;
	}
public void setIndex(int i) {
	// TODO Auto-generated method stub
}
	


class Counter extends Thread {
	Repository reposit; //reference variable to the Repository class

	
	public Counter(Repository reposit) {// Counter constructor 
		this.reposit = reposit;
	}

	
	@Override
	public void run() {//method for running the thread that calls the count()
		this.count();

	}
	
	
	public void count() {//method that counts the numbers and stores them in the repository class
		synchronized (this) {
			for (int i = 0; i <= 10; i++) {
				reposit.setIndex(i);
				

				try {
					Thread.sleep(100);//sleep thread that allows publish to start
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		}
	}

}

class Publisher extends Thread { 
	Repository reposit; //reference variable to the Repository class
	
	
	public Publisher(Repository reposit) {//publisher constructor 
		this.reposit = reposit;
	}
	
	
	public void publish() {//method that prints out the value in repository class
		synchronized (this) {
			for (int i = 0; i <= 10; i++) {
				System.out.println("Number: " + reposit.getIndex());
				//loop that goes from 0 - 10 and prints out the index .

				try {
					Thread.sleep(100);//sleep thread for 100ms
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		}

	}
	
	
	public void run() {//method that run thread, which calls publish() 
		this.publish();

	}
	//Main class 
	public class Main {

		public static void main(String[] args) {
			Repository reposit = new Repository();//instance of Repository
			Counter count = new Counter(reposit);//instance of Counter, repository instance used by counter and publish
			Publisher publish = new Publisher(reposit);//instance of publisher

			count.start();//starts the count thread
			publish.start();//starts the publish thread

		}
	}



	
}}//END

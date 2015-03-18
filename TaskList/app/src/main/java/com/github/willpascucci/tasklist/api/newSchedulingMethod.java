import java.util.ArrayList;
import java.util.Calendar;

public class schedulingMethod {
	
	// class for tasks
	
	public class task{
		public String name;										//input by user
		//public int importance;									//optional input, default '3'
		public String difficulty;								//optional input, default 'easy'
		public String criticalStatus;							//optional input, default 'green'
		public Calendar deadline = Calendar.getInstance();		//input by user
		public Calendar added = Calendar.getInstance();			//used for calculations
	}
	
	// list of tasks
	public static ArrayList<task> taskList= new ArrayList<task>();
	
	// queue hard with 3 sub-queues red, yellow and green
	public static ArrayList<ArrayList<task>> hard = new ArrayList<ArrayList<task>>();
	public static ArrayList<task> hardRed = new ArrayList<task>();
	public static ArrayList<task> hardYellow = new ArrayList<task>();
	public static ArrayList<task> hardGreen = new ArrayList<task>();
		
	//queue medium with 3 sub-queues red, yellow and green
	public static ArrayList<ArrayList<task>> medium = new ArrayList<ArrayList<task>>();
	public static ArrayList<task> mediumRed = new ArrayList<task>();
	public static ArrayList<task> mediumYellow = new ArrayList<task>();
	public static ArrayList<task> mediumGreen = new ArrayList<task>();
	
	//queue easy with 3 sub-queues red, yellow and green
	public static ArrayList<ArrayList<task>> easy = new ArrayList<ArrayList<task>>();
	public static ArrayList<task> easyRed = new ArrayList<task>();
	public static ArrayList<task> easyYellow = new ArrayList<task>();
	public static ArrayList<task> easyGreen = new ArrayList<task>();
	
	// from the tasklist, create 3 sorted queues- hard, easy and medium  
	public static void createQueues(){
		
		hard.add(hardRed);
		hard.add(hardYellow);
		hard.add(hardGreen);
		
		easy.add(easyRed);
		easy.add(easyYellow);
		easy.add(easyGreen);
		
		medium.add(mediumRed);
		medium.add(mediumYellow);
		medium.add(mediumGreen);
		
		
		//for each task
		for (int i = 0; i< taskList.size(); i++){
			
			// if difficulty of task is medium
			if (taskList.get(i).difficulty == "medium"){
				if(taskList.get(i).criticalStatus == "red")
					mediumRed.add(taskList.get(i));
				else if(taskList.get(i).criticalStatus == "yellow")
					mediumYellow.add(taskList.get(i));
				else
					mediumGreen.add(taskList.get(i));
				
			}
			
			// if difficulty is hard
			else if (taskList.get(i).difficulty == "hard"){
				if(taskList.get(i).criticalStatus == "red")
					hardRed.add(taskList.get(i));
				else if(taskList.get(i).criticalStatus == "yellow")
					hardYellow.add(taskList.get(i));
				else
					hardGreen.add(taskList.get(i));
			}
			
			//if difficulty is easy
			else {
				if(taskList.get(i).criticalStatus == "red")
					easyRed.add(taskList.get(i));
				else if(taskList.get(i).criticalStatus == "yellow")
					easyYellow.add(taskList.get(i));
				else
					easyGreen.add(taskList.get(i));
			}
		}
	}
	
	
	public static void updateCriticalStatus(){
		for (int i=0; i<taskList.size(); i++){
			//for green objects
			if (taskList.get(i).criticalStatus == "green"){
				Calendar today = Calendar.getInstance();
				float t = taskList.get(i).deadline.get(Calendar.DATE) - taskList.get(i).added.get(Calendar.DATE);
				float d= today.get(Calendar.DATE) - taskList.get(i).added.get(Calendar.DATE);
				if (d/t > 0.5)
					taskList.get(i).criticalStatus = "yellow";
				if (d/t > 0.75)
					taskList.get(i).criticalStatus = "red";
			}
			
			//for yellow objects
			if (taskList.get(i).criticalStatus == "yellow"){
				Calendar today = Calendar.getInstance();
				float t = taskList.get(i).deadline.get(Calendar.DATE) - taskList.get(i).added.get(Calendar.DATE);
				float d= today.get(Calendar.DATE) - taskList.get(i).added.get(Calendar.DATE);
				if (d/t > 0.5)
					taskList.get(i).criticalStatus = "red";
			}
		}
	}
	
	//main for testing
	public static void main(String[] args){
		
		// new objects
		schedulingMethod s = new schedulingMethod();
		schedulingMethod.task t = s.new task();
		t.name = "task1";
		t.criticalStatus = "red";
		t.difficulty = "easy";
		taskList.add(t);
		
		createQueues();
		System.out.println(taskList.get(0).name);
		//System.out.println(hard.get(0).get(0).name);
		System.out.println(easyRed.get(0).name);
		//System.out.println(medium.get(0).get(0).name);
		
	}

}

import java.util.*;

/*
	Runs two threads; one to pump out the time it is every second
	and another to allow user input to terminate the timer and output
	the final count. If running on windows, run via .bat file with a
	'pause' statement at the end.
*/
class TimeWorked implements Runnable {

	//start time
	static long startTimeStatic = System.currentTimeMillis();

	/*
		main entry point
	*/
	public static void main(String...args) throws Exception
	{
		//instantiate self and create new thread and start it
		new Thread(new TimeWorked()).start();

		//run timer at fixed rate to calculate elapsed time and display it to user
		//tick every 1s
		new Timer().scheduleAtFixedRate(new TimerTask() {
      			public void run() {
				//time elapsed
				long elapsedTime = System.currentTimeMillis() - startTimeStatic;
				long elapsedSeconds = elapsedTime / 1000;
				long secondsDisplay = elapsedSeconds % 60;
				long elapsedMinutes = elapsedSeconds / 60;


				//time elapsed broken down into hours, minutes and seconds
				int hours = (elapsedMinutes>59) ? (int) elapsedMinutes/60 : 0;
				int minutes = (elapsedMinutes>59) ? (int) elapsedMinutes%60 : (int)elapsedMinutes;
				int seconds = (elapsedSeconds>59) ? (int) elapsedSeconds%60 : (int) elapsedSeconds;

				//display elapsed time in readable format to user
				System.out.println(hours + " hours, " + minutes + " minutes and " + seconds + " seconds");              			
			}
    		}
    		, 0, 1000);
	}

	/*
		thread run()
		
		This will instantiat a scanner object and wait
		for user input. If user inputs x the loop will
		break and the final total time will be calculated
		and displayed to the user before exiting the program.
	*/
	public void run()
	{
		//instantiate scanner
		Scanner s = new Scanner(System.in);

		//input string
		String x="";

		while(s.hasNext()) 				//while the scanner is waiting for input...
		{
			x=s.nextLine();				//get next line
			if(x.equalsIgnoreCase("x")) break;	//if nexxt line of input equals X, regardless of case 
		}						//exit the while loop


		//close the scanner object
		s.close();


		//calculate final elapsed time
		long elapsedTime = System.currentTimeMillis() - startTimeStatic;
		long elapsedSeconds = elapsedTime / 1000;
		long secondsDisplay = elapsedSeconds % 60;
		long elapsedMinutes = elapsedSeconds / 60;

		//calculate final elapsed time in hours and minutes (seconds are discarded)
		int hours = (elapsedMinutes>59) ? (int) elapsedMinutes/60 : 0;
		int minutes = (elapsedMinutes>59) ? (int) elapsedMinutes%60 : (int)elapsedMinutes;

		//display to user
		System.out.println(hours + " hours and " + minutes + " minutes");

		//exit the application
		System.exit(0);
	}

}

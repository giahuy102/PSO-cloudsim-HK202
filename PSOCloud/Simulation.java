package PSOCloud;

import java.util.*;

public class Simulation {

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.println(
		"Enter the Number of times you want to compare results of PSO based vs Priority vs SJF");
		int n = sc.nextInt();
		double arrres[][] = new double[n][3];
		FileReader file = new FileReader("dataset.xlsx");
		for (int i = 0; i < n; i++) {
			/*
			 * For Static Case of Fixed 10 tasks on 8 vms with fixed specifications
			 * 
			 */
			file.readFile();
			// int tasklength[]= {3000,2000,1000,5000,4000,3500,2500,1500,6000,1300};
			int[] tasklength = new int[Constants.NoOfTasks];
			tasklength = file.getRunTime();
			int[] pesNumber = new int[Constants.NoOfTasks];
			pesNumber = file.getPesNumber();
			// int outputfilesize[] = {300,400,100,500,350,700,400,800,1000,550};
			Random rand = new Random();
			int[] outputfilesize = new int[Constants.NoOfTasks];
			for (int j = 0; j < Constants.NoOfTasks; j++) {
				outputfilesize[j] = rand.nextInt((1000 - 100) + 1) + 100;
			}

			int mips[] = {80, 50, 10, 10, 5, 15, 20, 60};
			// int mips[]= {8000,5000,1000,1000,500,1500,2000,6000};
			double execcost[] = {6, 10, 2, 0.5, 0.5, 4.5, 2, 7};
			double waitcost[] = {6, 10, 2, 0.5, 0.5, 4.5, 2, 7};
			// int graph[][] = { {0,1,1,0,0,0,0,0,0,0},
			// {0,0,0,1,0,0,0,0,0,0},
			// {0,0,0,1,0,0,0,0,0,0},
			// {0,0,0,0,0,0,0,1,0,0},
			// {0,0,0,0,0,0,0,0,0,0},
			// {0,0,0,0,0,0,0,0,0,0},
			// {0,0,0,0,0,0,0,0,0,0},
			// {0,0,0,0,0,0,0,0,1,0},
			// {0,0,0,0,0,0,0,0,0,0},
			// {0,0,0,0,0,0,0,0,0,0} };

			int[] precedingJob = file.getPrecedingJob();
			int[][] graph = new int[Constants.NoOfTasks][Constants.NoOfTasks];
			for (int j = 0; j < Constants.NoOfTasks; j++)
				for (int k = 0; k < Constants.NoOfTasks; k++)
					graph[j][k] = 0;
			for (int j = 0; j < Constants.NoOfTasks; j++) {
				if (precedingJob[j] != -1)
					graph[precedingJob[j] - 1][j] = 1;
			}
			
			TaskScheduler obj = new TaskScheduler();
			// double ans[]=obj.func(tasklength,outputfilesize,mips,execcost,graph);
			double ans[] = obj.func(tasklength, outputfilesize, mips, execcost, waitcost, graph,
					pesNumber);
			arrres[i][0] = ans[0];
			arrres[i][1] = ans[1];
			arrres[i][2] = ans[2];
			System.out.println("Cost of PSO Based Scheduling:" + ans[0]
					+ "\nCost of Priority Scheduling:" + ans[1]
					+ "\nCost of SJF Scheduling:" + ans[2]);
		}
		System.out.println("------------------------------------------------------------------------------------------");
		System.out.println("PSO" + "\t\t\t" + "Priority" + "\t\t\t" + "SJF");
		for (int i = 0; i < n; i++) {
			System.out.println(arrres[i][0] + "\t\t" + arrres[i][1] + "\t\t" + arrres[i][2]);
		}
	}

}

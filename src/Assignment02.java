// CSE 110      : 90548 / 08/19/21 - 12/03/21
// Assignment   : assignment #2
// Author       : kai ohsawa id# 1216436000
// Description  : i must write a program that computes the materials and costs
//                required for a specific road construction project.

import java.util.Scanner;

import java.text.DecimalFormat;

public class Assignment02 {

    private static DecimalFormat df2 = new DecimalFormat("#.00");
    public static void main(String[] args) throws Exception {
        
        Scanner in  = new Scanner(System.in);

        // four user inputs are necessary, the variables associated are declared and initialized here.
        double lengthRoad;
        int numLanes, depthAsphalt, daysCompletion;

        // prompts for user input assign values to the variables.
        System.out.println("What is the length of the road project (in miles)?");
        lengthRoad = in.nextDouble();
        
        System.out.println("How many lanes will there be?");
        numLanes = in.nextInt();
        
        System.out.println("What is the depth of the asphalt (in inches)?");
        depthAsphalt = in.nextInt();
        
        System.out.println("How many days to complete the project?");
        daysCompletion = in.nextInt();

        // calculates number of crew members needed (needs a round up, as you can't have half a person)
        double crewMembers = ((50 * lengthRoad * numLanes) / daysCompletion);

        double costLabor = Math.ceil(crewMembers) * (8 * 25 * daysCompletion);

        // calculates number of intersections and rounds down
        double numIntersections = (Math.floor(lengthRoad));

        // calculates number of stoplights at each intersection (total stoplights)
        double numStoplights = ((2 * numIntersections) + (numLanes * numIntersections));

        // calculates total cost of stoplights
        double costStoplights = (25000 * numStoplights);

        // calculates number of conduits required
        double numConduits = ((lengthRoad * 5280) / 24);

        // calculates total cost of conduits
        double costConduits = (numConduits * 500);

        // ----------- TRICKY STUFF --------------
        // in order to calculate this equation, all variables need to have the same metric
        // here we know asphalt weighs 150 lbs per cubic foot
        // we need to convert the variables into feet
        // lengthRoad is in MILES, so to convert to feet we multiply by 5280
        // numLanes is is already in FEET, so you just multiply by number of lanes.
        // depthAsphalt is in INCHES, so to convert to feet you divide by 12

        double roadLength = lengthRoad * 5280; // miles to feet
        double roadWidth = numLanes * 12; // feet already
        double roadHeight = depthAsphalt / 12; // inches to feet

        double asphaltNeeded = roadLength * roadWidth * roadHeight;

        double numTrucks = lengthRoad * depthAsphalt * numLanes * 79.2;

        double costAsphalt = Math.ceil(numTrucks) * 1000;

        double costTotal = costAsphalt + costStoplights + costConduits + costLabor;

        // -------- PLEASE NOTE -------- IMPORTANT --------
        // I know I can format this using printf("prompt: $%.2f%n" + variable), but I wanted to learn a different way to do it
        // and it works quite well! I think it looks more clean too.
        
        System.out.println("==== Amount of materials needed ===="); 
        System.out.println("Truckloads of Asphalt   : " + (int)Math.ceil(numTrucks));
        System.out.println("Stoplights              : " + (int)numStoplights);
        System.out.println("Conduit pipes           : " + (int)Math.ceil(numConduits)); 
        System.out.println("Crew members needed     : " + (int)Math.ceil(crewMembers)); 

        System.out.println("==== Cost of Materials =============="); 
        System.out.println("Cost of Asphalt         : $" + df2.format(costAsphalt)); 
        System.out.println("Cost of Stoplights      : $" + df2.format(costStoplights)); 
        System.out.println("Cost of Conduit pipes   : $" + df2.format(costConduits));
        System.out.println("Cost of Labor           : $" + df2.format(costLabor)); 

        System.out.println("==== Total Cost of Project ==========");
        System.out.println("Total cost of project   : $" + df2.format(costTotal)); 
    }
}
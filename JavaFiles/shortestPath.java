import java.util.Hashtable;
import java.util.Scanner;

public class shortestPath {
    /*
     * Inserts Example Tanks, Pipes, and Connections and tests program
     */
    public static void runTest() {
        Graph mnfld = new Graph();
        float destTank = 7f;

        // Adding Tanks
        mnfld.addPipe( new Node( 0f, 5.0f ) );
        mnfld.addPipe( new Node( 1f, 5.0f ) );
        mnfld.addPipe( new Node( 7f, 5.0f ) );

        // Adding Pipes
        mnfld.addPipe( new Node( 2f, 22f, 100f ) );
        mnfld.addPipe( new Node( 3f, 33f, 150f ) );
        mnfld.addPipe( new Node( 4f, 44f, 200f ) );
        mnfld.addPipe( new Node( 5f, 55f, 250f ) );
        mnfld.addPipe( new Node( 6f, 66f, 300f ) );
        mnfld.addPipe( new Node( 8f, 88f, 200f ) );
        mnfld.addPipe( new Node( 9f, 99f, 150f ) );
        mnfld.addPipe( new Node( 10f, 1010f, 150f ) );
        mnfld.addPipe( new Node( 20f, 2020f, 150f ) );

        // Inserting Edges to the graph
        mnfld.insertConnection( new Edge( mnfld.getPipe( 0f ), mnfld.getPipe( 2f ), 1f ) );
        mnfld.insertConnection( new Edge( mnfld.getPipe( 0f ), mnfld.getPipe( 3f ), 1f ) );
        mnfld.insertConnection( new Edge( mnfld.getPipe( 1f ), mnfld.getPipe( 2f ), 1f ) );
        mnfld.insertConnection( new Edge( mnfld.getPipe( 22f ), mnfld.getPipe( 4f ), 1f ) );
        mnfld.insertConnection( new Edge( mnfld.getPipe( 33f ), mnfld.getPipe( 5f ), 1f ) );
        mnfld.insertConnection( new Edge( mnfld.getPipe( 44f ), mnfld.getPipe( 5f ), 1f ) );
        mnfld.insertConnection( new Edge( mnfld.getPipe( 44f ), mnfld.getPipe( 7f ), 500f ) );
        mnfld.insertConnection( new Edge( mnfld.getPipe( 55f ), mnfld.getPipe( 6f ), 1f ) );
        mnfld.insertConnection( new Edge( mnfld.getPipe( 66f ), mnfld.getPipe( 7f ), 100f ) );
        mnfld.insertConnection( new Edge( mnfld.getPipe( 66f ), mnfld.getPipe( 8f ), 1f ) );
        mnfld.insertConnection( new Edge( mnfld.getPipe( 88f ), mnfld.getPipe( 7f ), 1f ) );
        mnfld.insertConnection( new Edge( mnfld.getPipe( 33f ), mnfld.getPipe( 9f ), 1f ) );
        mnfld.insertConnection( new Edge( mnfld.getPipe( 99f ), mnfld.getPipe( 5f ), 1f ) );
        mnfld.insertConnection( new Edge( mnfld.getPipe( 33f ), mnfld.getPipe( 1010f ), 1f ) );
        mnfld.insertConnection( new Edge( mnfld.getPipe( 1010f ), mnfld.getPipe( 7f ), 1f ) );
        mnfld.insertConnection( new Edge( mnfld.getPipe( 1f ), mnfld.getPipe( 20f ), 1f ) );
        mnfld.insertConnection( new Edge( mnfld.getPipe( 2020f ), mnfld.getPipe( 3f ), 1f ) );

        // -- Running Dijkstra & Finding shortest Paths -- //
//        Dijkstra.findPaths( mnfld, 10, "0.0", destTank );
//
//        mnfld.restoreDroppedConnections();
//
//        System.out.println( "\n\n" );
        Dijkstra.findMinPaths( mnfld, mnfld.getPipe( 0f ) );
        Dijkstra.mergePaths( mnfld, 1f, mnfld.getPipe( destTank ).getPath(), mnfld.getPipe( destTank ) );

    }


    public static void runTest2() {
        Graph mnfld = new Graph();
        Hashtable<Character, Node> nTable = new Hashtable<Character, Node>();
        float destTank = 8f;
        Character c = 'A';

        // Adding Tanks
        for (int id = 0; id < 11; id += 2) {
            nTable.put( c, new Node( (float) id, 5.0f ) );
            c++;
        }

        // Adding Pipes
        nTable.put( 'G', new Node( 1000f, 1001f, 100f ) );
        nTable.put( 'H', new Node( 1002f, 1003f, 10f ) );
        nTable.put( 'I', new Node( 1004f, 1005f, 120f ) );
        nTable.put( 'J', new Node( 1006f, 1007f, 100f ) );
        nTable.put( 'K', new Node( 1008f, 1009f, 170f ) );
        nTable.put( 'L', new Node( 1010f, 1011f, 100f ) );
        nTable.put( 'M', new Node( 1012f, 1013f, 100f ) );
        nTable.put( 'N', new Node( 1014f, 1015f, 100f ) );
        nTable.put( 'O', new Node( 1016f, 1017f, 100f ) );
        nTable.put( 'P', new Node( 1018f, 1019f, 100f ) );
        nTable.put( 'Q', new Node( 1020f, 1021f, 190f ) );
        nTable.put( 'R', new Node( 1022f, 1023f, 100f ) );
        nTable.put( 'S', new Node( 1024f, 1025f, 130f ) );
        nTable.put( 'T', new Node( 1026f, 1027f, 100f ) );
        nTable.put( 'U', new Node( 1028f, 1029f, 100f ) );
        nTable.put( 'V', new Node( 1030f, 1031f, 100f ) );
        nTable.put( 'W', new Node( 1032f, 1033f, 100f ) );
        nTable.put( 'X', new Node( 1034f, 1035f, 120f ) );
        nTable.put( 'Y', new Node( 1036f, 1037f, 140f ) );
        nTable.put( 'Z', new Node( 1038f, 1039f, 100f ) );

        for (Character ch = 'A'; ch <= 'Z'; ch++) {
            mnfld.addPipe( nTable.get( ch ) );
        }

        // Inserting Edges to the graph
        mnfld.insertConnection( new Edge( nTable.get( 'A' ), nTable.get( 'G' ), 300f ) );
        mnfld.insertConnection( new Edge( nTable.get( 'A' ), nTable.get( 'H' ), 300f ) );
        mnfld.insertConnection( new Edge( nTable.get( 'B' ), nTable.get( 'H' ), 300f ) );
        mnfld.insertConnection( new Edge( nTable.get( 'B' ), nTable.get( 'K' ), 300f ) );
        mnfld.insertConnection( new Edge( nTable.get( 'B' ), nTable.get( 'Z' ), 300f ) );
        mnfld.insertConnection( new Edge( nTable.get( 'C' ), nTable.get( 'Z' ), 300f ) );
        mnfld.insertConnection( new Edge( nTable.get( 'C' ), nTable.get( 'L' ), 300f ) );
        mnfld.insertConnection( new Edge( nTable.get( 'G' ), nTable.get( 'I' ), 1f ) );
        mnfld.insertConnection( new Edge( nTable.get( 'G' ), nTable.get( 'J' ), 1f ) );
        mnfld.insertConnection( new Edge( nTable.get( 'G' ), nTable.get( 'H' ), 1f ) );
        mnfld.insertConnection( new Edge( nTable.get( 'H' ), nTable.get( 'K' ), 1f ) );
        mnfld.insertConnection( new Edge( nTable.get( 'K' ), nTable.get( 'Z' ), 1f ) );
        mnfld.insertConnection( new Edge( nTable.get( 'K' ), nTable.get( 'J' ), 1f ) );
        mnfld.insertConnection( new Edge( nTable.get( 'Z' ), nTable.get( 'L' ), 1f ) );
        mnfld.insertConnection( new Edge( nTable.get( 'L' ), nTable.get( 'M' ), 1f ) );
        mnfld.insertConnection( new Edge( nTable.get( 'I' ), nTable.get( 'N' ), 1f ) );
        mnfld.insertConnection( new Edge( nTable.get( 'J' ), nTable.get( 'M' ), 1f, true ) );
        mnfld.insertConnection( new Edge( nTable.get( 'J' ), nTable.get( 'Y' ), 1f ) );
        mnfld.insertConnection( new Edge( nTable.get( 'M' ), nTable.get( 'O' ), 1f ) );
        mnfld.insertConnection( new Edge( nTable.get( 'M' ), nTable.get( 'X' ), 1f ) );
        mnfld.insertConnection( new Edge( nTable.get( 'N' ), nTable.get( 'Y' ), 1f ) );
        mnfld.insertConnection( new Edge( nTable.get( 'N' ), nTable.get( 'Q' ), 1f ) );
        mnfld.insertConnection( new Edge( nTable.get( 'Y' ), nTable.get( 'O' ), 1f ) );
        mnfld.insertConnection( new Edge( nTable.get( 'Y' ), nTable.get( 'P' ), 1f ) );
        mnfld.insertConnection( new Edge( nTable.get( 'Q' ), nTable.get( 'T' ), 1f ) );
        mnfld.insertConnection( new Edge( nTable.get( 'Q' ), nTable.get( 'S' ), 1f ) );
        mnfld.insertConnection( new Edge( nTable.get( 'P' ), nTable.get( 'U' ), 1f ) );
        mnfld.insertConnection( new Edge( nTable.get( 'P' ), nTable.get( 'S' ), 1f ) );
        mnfld.insertConnection( new Edge( nTable.get( 'O' ), nTable.get( 'U' ), 1f, true ) );
        mnfld.insertConnection( new Edge( nTable.get( 'S' ), nTable.get( 'V' ), 1f ) );
        mnfld.insertConnection( new Edge( nTable.get( 'U' ), nTable.get( 'V' ), 1f ) );
        mnfld.insertConnection( new Edge( nTable.get( 'U' ), nTable.get( 'W' ), 1f, true ) );
        mnfld.insertConnection( new Edge( nTable.get( 'X' ), nTable.get( 'R' ), 1f ) );
        mnfld.insertConnection( new Edge( nTable.get( 'R' ), nTable.get( 'W' ), 1f ) );
        mnfld.insertConnection( new Edge( nTable.get( 'U' ), nTable.get( 'E' ), 300f ) );
        mnfld.insertConnection( new Edge( nTable.get( 'V' ), nTable.get( 'E' ), 300f ) );
        mnfld.insertConnection( new Edge( nTable.get( 'T' ), nTable.get( 'D' ), 300f ) );
        mnfld.insertConnection( new Edge( nTable.get( 'W' ), nTable.get( 'F' ), 300f ) );


        System.out.println( "True shortest with alt paths" );
        Dijkstra.findAltPaths( mnfld, 3, 0f, 8f );
        mnfld.restoreDroppedConnections();
        Dijkstra.resetCosts( mnfld );

        System.out.println( "Lineup Considering used connections" );
        Dijkstra.findMinPaths( mnfld, nTable.get( 'A' ), true );
        mnfld.getPipe( 8f ).printLine();
        mnfld.restoreDroppedConnections();
        Dijkstra.resetCosts( mnfld );

        System.out.println( "Lineup Considering only open connections" );
        Dijkstra.findMinPaths( mnfld, nTable.get( 'A' ), false );
        mnfld.getPipe( 8f ).printLine();
        mnfld.restoreDroppedConnections();
        Dijkstra.resetCosts( mnfld );

        System.out.println( "Merge Lineup" );
        Dijkstra.findMinPaths( mnfld, mnfld.getPipe( 4f ) );
        System.out.println( "\t Original Lineup: " );
        mnfld.getPipe( 8f ).printLine();
        System.out.println( "\t Merged Lineup: " );
        Dijkstra.mergePaths( mnfld, 2f, mnfld.getPipe( destTank ).getPath(), mnfld.getPipe( 8f ) );


//        Dijkstra.findPaths( mnfld, 3, 0f, 8f );

        // -- Running Dijkstra & Finding shortest Paths -- //
//        Dijkstra.findMinPaths( mnfld, nTable.get( 'A' ), true);
//        mnfld.getPipe( 8f ).printLine();
//        Dijkstra.findPaths( mnfld, 10, 0f, destTank );
//
//        mnfld.restoreDroppedConnections();
//
//        System.out.println( "\n\n" );
//        Dijkstra.findMinPaths( mnfld, mnfld.getPipe( 0f ) );
//        Dijkstra.mergePaths( mnfld, 1f, mnfld.getPipe( destTank ).getPath(), mnfld.getPipe( destTank ) );

    }

    public static void loadGraph(Graph mnfld) {
        Hashtable<Character, Node> nTable = new Hashtable<Character, Node>();
//        float destTank = 8f;
        Character c = 'A';

        // Adding Tanks
        for (int id = 0; id < 11; id += 2) {
            nTable.put( c, new Node( (float) id, 5.0f ) );
            c++;
        }

        // Adding Pipes
        nTable.put( 'G', new Node( 1000f, 1001f, 100f ) );
        nTable.put( 'H', new Node( 1002f, 1003f, 10f ) );
        nTable.put( 'I', new Node( 1004f, 1005f, 120f ) );
        nTable.put( 'J', new Node( 1006f, 1007f, 100f ) );
        nTable.put( 'K', new Node( 1008f, 1009f, 170f ) );
        nTable.put( 'L', new Node( 1010f, 1011f, 100f ) );
        nTable.put( 'M', new Node( 1012f, 1013f, 100f ) );
        nTable.put( 'N', new Node( 1014f, 1015f, 100f ) );
        nTable.put( 'O', new Node( 1016f, 1017f, 100f ) );
        nTable.put( 'P', new Node( 1018f, 1019f, 100f ) );
        nTable.put( 'Q', new Node( 1020f, 1021f, 190f ) );
        nTable.put( 'R', new Node( 1022f, 1023f, 100f ) );
        nTable.put( 'S', new Node( 1024f, 1025f, 130f ) );
        nTable.put( 'T', new Node( 1026f, 1027f, 100f ) );
        nTable.put( 'U', new Node( 1028f, 1029f, 100f ) );
        nTable.put( 'V', new Node( 1030f, 1031f, 100f ) );
        nTable.put( 'W', new Node( 1032f, 1033f, 100f ) );
        nTable.put( 'X', new Node( 1034f, 1035f, 120f ) );
        nTable.put( 'Y', new Node( 1036f, 1037f, 140f ) );
        nTable.put( 'Z', new Node( 1038f, 1039f, 100f ) );

        for (Character ch = 'A'; ch <= 'Z'; ch++) {
            mnfld.addPipe( nTable.get( ch ) );
        }

        // Inserting Edges to the graph
        mnfld.insertConnection( new Edge( nTable.get( 'A' ), nTable.get( 'G' ), 300f ) );
        mnfld.insertConnection( new Edge( nTable.get( 'A' ), nTable.get( 'H' ), 300f ) );
        mnfld.insertConnection( new Edge( nTable.get( 'B' ), nTable.get( 'H' ), 300f ) );
        mnfld.insertConnection( new Edge( nTable.get( 'B' ), nTable.get( 'K' ), 300f ) );
        mnfld.insertConnection( new Edge( nTable.get( 'B' ), nTable.get( 'Z' ), 300f ) );
        mnfld.insertConnection( new Edge( nTable.get( 'C' ), nTable.get( 'Z' ), 300f ) );
        mnfld.insertConnection( new Edge( nTable.get( 'C' ), nTable.get( 'L' ), 300f ) );
        mnfld.insertConnection( new Edge( nTable.get( 'G' ), nTable.get( 'I' ), 1f ) );
        mnfld.insertConnection( new Edge( nTable.get( 'G' ), nTable.get( 'J' ), 1f ) );
        mnfld.insertConnection( new Edge( nTable.get( 'G' ), nTable.get( 'H' ), 1f ) );
        mnfld.insertConnection( new Edge( nTable.get( 'H' ), nTable.get( 'K' ), 1f ) );
        mnfld.insertConnection( new Edge( nTable.get( 'K' ), nTable.get( 'Z' ), 1f ) );
        mnfld.insertConnection( new Edge( nTable.get( 'K' ), nTable.get( 'J' ), 1f ) );
        mnfld.insertConnection( new Edge( nTable.get( 'Z' ), nTable.get( 'L' ), 1f ) );
        mnfld.insertConnection( new Edge( nTable.get( 'L' ), nTable.get( 'M' ), 1f ) );
        mnfld.insertConnection( new Edge( nTable.get( 'I' ), nTable.get( 'N' ), 1f ) );
        mnfld.insertConnection( new Edge( nTable.get( 'J' ), nTable.get( 'M' ), 1f, true ) );
        mnfld.insertConnection( new Edge( nTable.get( 'J' ), nTable.get( 'Y' ), 1f ) );
        mnfld.insertConnection( new Edge( nTable.get( 'J' ), nTable.get( 'N' ), 1f ) );
        mnfld.insertConnection( new Edge( nTable.get( 'M' ), nTable.get( 'O' ), 1f ) );
        mnfld.insertConnection( new Edge( nTable.get( 'M' ), nTable.get( 'Y' ), 1f ) );
        mnfld.insertConnection( new Edge( nTable.get( 'J' ), nTable.get( 'Y' ), 1f ) );
        mnfld.insertConnection( new Edge( nTable.get( 'N' ), nTable.get( 'Y' ), 1f ) );
        mnfld.insertConnection( new Edge( nTable.get( 'N' ), nTable.get( 'Q' ), 1f ) );
        mnfld.insertConnection( new Edge( nTable.get( 'Y' ), nTable.get( 'O' ), 1f ) );
        mnfld.insertConnection( new Edge( nTable.get( 'Y' ), nTable.get( 'P' ), 1f ) );
        mnfld.insertConnection( new Edge( nTable.get( 'Q' ), nTable.get( 'T' ), 1f ) );
        mnfld.insertConnection( new Edge( nTable.get( 'S' ), nTable.get( 'T' ), 1f ) );
        mnfld.insertConnection( new Edge( nTable.get( 'Q' ), nTable.get( 'S' ), 1f ) );
        mnfld.insertConnection( new Edge( nTable.get( 'P' ), nTable.get( 'U' ), 1f ) );
        mnfld.insertConnection( new Edge( nTable.get( 'P' ), nTable.get( 'S' ), 1f ) );
        mnfld.insertConnection( new Edge( nTable.get( 'O' ), nTable.get( 'U' ), 1f, true ) );
        mnfld.insertConnection( new Edge( nTable.get( 'S' ), nTable.get( 'V' ), 1f ) );
        mnfld.insertConnection( new Edge( nTable.get( 'U' ), nTable.get( 'V' ), 1f ) );
        mnfld.insertConnection( new Edge( nTable.get( 'U' ), nTable.get( 'W' ), 1f, true ) );
        mnfld.insertConnection( new Edge( nTable.get( 'X' ), nTable.get( 'R' ), 1f ) );
        mnfld.insertConnection( new Edge( nTable.get( 'R' ), nTable.get( 'W' ), 1f ) );
        mnfld.insertConnection( new Edge( nTable.get( 'U' ), nTable.get( 'E' ), 300f ) );
        mnfld.insertConnection( new Edge( nTable.get( 'V' ), nTable.get( 'E' ), 300f ) );
        mnfld.insertConnection( new Edge( nTable.get( 'T' ), nTable.get( 'D' ), 300f ) );
        mnfld.insertConnection( new Edge( nTable.get( 'W' ), nTable.get( 'F' ), 300f ) );
    }

    public static void liveDemo() {
        // 0->8
        // Tanks 0, 2, 4
        Scanner in = new Scanner( System.in );
        float srcTank1, srcTank2, destTank;
        int alternetPaths;
        Graph mnfld = new Graph();
        boolean runSearch = true;

        //might need to limit query results, too large maybe?
        //newnodes = JDBC.graphInformation(); //returns arraylist of nodes
        //mnfld.setPipes(newnodes); //set nodes to the manifold
        //JDBC.insertConnections(mnfld);

//		 /*
//			for (int i = 0; i < newnodes.length(); i++) { //length might be wrong
//				//need to look for way to add edges by looking for neighbor nodes i think
//				//loop through nodes and create Edges
//				//might need new query
//				newedges.addEdge(node1,node2,cost);
//			}
//
//			mnfld.setConnections(newedges);
//
//			up to this point, graph should be global to Dijkstra and unused.
//			loop until user leaves page
//			get input from user for tanks to transfer
//			find shortest path between both tanks
//			end loop
//			this will change depending how html works so not spending any time now on it
//		*/
        //shortestPath.runTest();

        shortestPath.loadGraph( mnfld );
        while (runSearch) {
            // Gets user input
            System.out.print( "First Source Tank: " );
            srcTank1 = in.nextFloat();
            System.out.print( "Second Source Tank: " );
            srcTank2 = in.nextFloat();
            System.out.print( "Destination Tank: " );
            destTank = in.nextFloat();
            System.out.print( "Desired Number of Alternate Paths: " );
            alternetPaths = in.nextInt();

            // Prints outputs
            Dijkstra.resetCosts( mnfld );
            System.out.println( "True shortest with alt paths" );
            Dijkstra.findAltPaths( mnfld, alternetPaths, srcTank1, destTank );
            mnfld.restoreDroppedConnections();

            Dijkstra.resetCosts( mnfld );
            System.out.println( "Lineup Considering used connections" );
            Dijkstra.findMinPaths( mnfld, mnfld.getPipe( srcTank1 ), true );
            mnfld.getPipe( destTank ).printLine();
            mnfld.restoreDroppedConnections();

            Dijkstra.resetCosts( mnfld );
            System.out.println( "Lineup Considering only open connections" );
            Dijkstra.findMinPaths( mnfld, mnfld.getPipe( srcTank1 ), false );
            mnfld.getPipe( destTank ).printLine();
            mnfld.restoreDroppedConnections();

            Dijkstra.resetCosts( mnfld );
            System.out.println( "Merge Lineup" );
            Dijkstra.findMinPaths( mnfld, mnfld.getPipe( srcTank1 ) );
            System.out.println( "\t Original Lineup: " );
            mnfld.getPipe( destTank ).printLine();
            System.out.println( "\t Merged Lineup: " );
            Dijkstra.mergePaths( mnfld, srcTank2, mnfld.getPipe( destTank ).getPath(), mnfld.getPipe( destTank ) );
            System.out.print( "\nRun another search [1:Yes, 0:No] :: " );
            if (in.nextInt() == 0) runSearch = false;
            else System.out.println( "\n\n\n" );
        }


    }

}
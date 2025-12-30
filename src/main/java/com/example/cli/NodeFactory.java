package com.example.cli;

import java.util.HashMap;
import java.util.Map;

public class NodeFactory {


    public Node rollDice(String next, Node last) {

        // main roll
        // 1-2 straight
        // 3-5 door
        // 6-10 side passage
        // 11-13 turns
        // 14-16 room
        // 17 stairs
        // 18 end
        // 19 trick/trap
        // 20 monster
        

        System.out.println("Rolling d20...");

        Node node = null;

        while (node == null) {

            int roll = java.util.concurrent.ThreadLocalRandom.current().nextInt(1, 21);
            System.out.println("Rolled a d20: " + roll);
        
            if (roll == 1 || roll == 2) {
                node = createCorridorNode(next, last);
            } 
            else if (roll >= 6 && roll <= 10) {
                node = sidePassages(next, last);
            } 
        }

        return node;
    }

    public Node sidePassages( String next, Node last) {

        // choose one of several side passage types
        int choice = java.util.concurrent.ThreadLocalRandom.current().nextInt(2);
        System.out.println("Side Passage Type: " + choice);
        switch (choice) {
            case 0:
                return createLeft90_Corridor(next, last);
            case 1:
                return createRight90_Corridor(next, last);
            default:
                return null;
        }
    }
/* 
    public Node createTIntersection(String cmd, Node last) {
        String desc = "";
        String[] exits = null;

        switch (cmd) {
            case "north":
                desc = "You head north. There is a T intersection ahead.";
                exits = new String[]{"south", "east", "west"}; 
                break;
            case "south":
                desc = "You head south. There is a T intersection ahead.";
                exits = new String[]{"north", "east", "west"};
                break;
            case "east":
                desc = "You head east. There is a T intersection ahead.";
                exits = new String[]{"west", "north", "south"};
                break;
            case "west":
                desc = "You head west. There is a T intersection ahead.";
                exits = new String[]{"east", "north", "south"};
                break;
            default:
                break;
        }

        Node newNode = new Node(desc, exits);
        String entry = playerRoomEntry(cmd); newNode.addNode(entry, last);
        return newNode;
    }

    public Node createYIntersection(String cmd, Node last) {
        String desc = "";
        String[] exits = null;

        switch (cmd) {
            case "north":
                desc = "You head north. There is a Y intersection ahead.";
                exits = new String[]{"south", "east", "west"}; 
                break;
            case "south":
                desc = "You head south. There is a Y intersection ahead.";
                exits = new String[]{"north", "east", "west"};
                break;
            case "east":
                desc = "You head east. There is a Y intersection ahead.";
                exits = new String[]{"west", "north", "south"};
                break;
            case "west":
                desc = "You head west. There is a Y intersection ahead.";
                exits = new String[]{"east", "north", "south"};
                break;
            default:
                break;
        }

        Node newNode = new Node(desc, exits);
        String entry = playerRoomEntry(cmd);
        newNode.addNode(entry, last);
        return newNode;
    }
*/
    public Node createCorridorNode(String next, Node last) {

        System.out.println("Creating straight corridor...");
        System.out.println("Player moved: " + next);

        // determine exits in the room
        String[] exits; 
        if (next.equals("east") || next.equals("west")) {
            exits = new String[]{"east", "west"};
        } else {
            exits = new String[]{"north", "south"};
        }

        // The key is the entry direction, the value is the description seen when entering from that direction.
        Map<String,String> desc = new HashMap<>();
        desc.put("south","You are in a corridor that runs north-south.");
        desc.put("north","You are in a corridor that runs north-south.");
        desc.put("east","You are in a corridor that runs east-west.");
        desc.put("west","You are in a corridor that runs east-west.");

        Node newNode = new Node(desc, exits);

        String entry = playerRoomEntry(next);

        // add the node the player is coming from
        newNode.addNode(entry, last);

        return newNode;
    }

    public Node createLeft90_Corridor(String next, Node last) {

        String[] exits = null;

        Map<String,String> descMap = new HashMap<>();

        String entry = playerRoomEntry(next);

        switch(entry) {
            case "south":
                descMap.put("south","You are in a corridor that runs north-south. There is a passage to the west.");
                descMap.put("north","You are in a corridor that runs north-south. There is a passage to the west.");
                descMap.put("west","You have reached a T intersection. You can go north or south..");
                exits = new String[]{"north", "south","west"};
                break;
            case "north":
                descMap.put("south","You are in a corridor that runs north-south. There is a passage to the east.");
                descMap.put("north","You are in a corridor that runs north-south. There is a passage to the east.");
                descMap.put("east","You have reached a T intersection. You can go north or south..");
                exits = new String[]{"north", "south","east"};
                break;
            case "west":
                descMap.put("east","You are in a corridor that runs west-east. There is a passage to the north. ");
                descMap.put("north","You have reached a T intersection. You can go east or west.");
                descMap.put("west","You are in a corridor that runs west-east. There is a passage to the north.");
                exits = new String[]{"west", "east","north"};
                break;
            case "east":
                descMap.put("east","You are in a corridor that runs west-east. There is a passage to the south. ");
                descMap.put("south","You have reached a T intersection. You can go east or west.");
                descMap.put("west","You are in a corridor that runs west-east. There is a passage to the south.");
                exits = new String[]{"west", "east","south"};
                break;
            default:
                break;    
                
        }

        Node newNode = new Node(descMap, exits);

        newNode.addNode(entry, last);

        return newNode;
    }

    public Node createRight90_Corridor(String next, Node last) {

        String[] exits = null;



        Map<String,String> descMap = new HashMap<>();

        String entry = playerRoomEntry(next);

        switch(entry) {
            case "south":
                descMap.put("south","You are in a corridor that runs north-south. There is a passage to the east.");
                descMap.put("north","You are in a corridor that runs north-south. There is a passage to the east.");
                descMap.put("east","You have reached a T intersection. You can go north or south..");
                exits = new String[]{"north", "south","east"};
                break;
            case "north":
                descMap.put("south","You are in a corridor that runs north-south. There is a passage to the west.");
                descMap.put("north","You are in a corridor that runs north-south. There is a passage to the west.");
                descMap.put("west","You have reached a T intersection. You can go north or south..");
                exits = new String[]{"north", "south","west"};
                break;
            case "west":
                descMap.put("east","You are in a corridor that runs west-east. There is a passage to the south. ");
                descMap.put("south","You have reached a T intersection. You can go west or east.");
                descMap.put("west","You are in a corridor that runs west-east. There is a passage to the south.");
                exits = new String[]{"west", "east","south"};
                break;
            case "east":
                descMap.put("east","You are in a corridor that runs west-east. There is a passage to the north ");
                descMap.put("north","You have reached a T intersection. You can go west or east.");
                descMap.put("west","You are in a corridor that runs west-east. There is a passage to the north.");
                exits = new String[]{"west", "east","north"};
                break;
            default:
                break;    
                
        }

        Node newNode = new Node(descMap, exits);

        newNode.addNode(entry, last);

        return newNode;
    }   

    /**
     * Determine location that the player is at when entering the room.
     * 
     * @param cmd
     * @return
     */
    public String playerRoomEntry(String next) {

        String entry = "";

        switch (next) {
            case "north":
                entry = "south";
                break;
            case "south":
                entry = "north";
                break;
            case "east":
                entry = "west";
                break;
            case "west":
                entry = "east";
                break;
            default:
                // leave entry empty for unknown/unsupported directions
                break;
        }
        return entry;
    }   

    public Node createStartingNode() {
        
        String[] exits  = new String[]{"north"};

        Map<String,String> desc = new HashMap<>();
        desc.put("north","You are in a dead end corrior. There is a passage to the north.");

        Node node = new Node(desc, exits);
        node.setEntry("north");
        return node;
    }
}

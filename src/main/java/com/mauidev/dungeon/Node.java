package com.mauidev.dungeon;

import java.util.Map;
import java.util.HashMap;

public class Node {
    

    private Map<String, String> descMap = new HashMap<>();
    private Map<String, Node> room = new HashMap<>();

    private String entry;

    public Node(Map<String,String> descMap, String ...movements) {
        this.descMap = descMap;
        for (String movement : movements) {
            room.put(movement, null); // null indicates unexplored direction
        }

    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    /**
     * Return a description from the perspective of where the room is entered.
     * 
     * @return
     */
    public String getDesc() {
        System.out.println("getDesc() entry: " + entry);
        return descMap.get(entry);
    }

    public boolean validateDirection(String direction) {
        return room.containsKey(direction);
    }

   public boolean hasNode(String next) {
        return room.get(next) != null;
   }

   public void addNode(String next, Node node) {
       room.put(next, node);
   }   

   public Node enter(String next) {
       System.out.println("enter() next: " + next);

//       this.next = next;
       entry = Util.playerRoomEntry(next);
       System.out.println("  The entry is now: " + entry);
       Node node = room.get(next);
       // this is where the player enters the room
       node.setEntry(entry);
       return node;
   }
}
package com.example.cli;

public class Util {

    /**
     * Determine location that the player is at when entering the room.
     * 
     * @param cmd
     * @return
     */
    public static String playerRoomEntry(String next) {

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

    
}

package com.example.cli;

import java.util.Scanner;

public class Loop {

    private final Scanner scanner = new Scanner(System.in);
    private final NodeFactory nodeFactory = new NodeFactory();

    public void run() {

        System.out.println("Dungeon Crawl Started!");
        System.out.println("Version 1.0\n\n");

        Node currentNode = nodeFactory.createStartingNode();

        boolean done = false;

        while (!done) {

            System.out.println(currentNode.getDesc());

            String next = input();
            if (next == null || next.isEmpty())
                continue;

            if (next.equals("q") || next.equals("quit") || next.equals("exit")) {
                System.out.println("Exiting Dungeon Crawl. Goodbye!");
                done  = true;
                continue;
            }

            if (!currentNode.validateDirection(next)) {
                System.out.println("I don't understand that direction.");
                continue;
            }

            // create the new node if needed
            if (!currentNode.hasNode(next)) {
                Node newNode = nodeFactory.rollDice(next, currentNode);
                currentNode.addNode(next, newNode);
            }

            // return the node that the player walks into
            currentNode = currentNode.enter(next);

        }

        close();

    }

    public void close() {
        try {
            scanner.close();
        } catch (Exception ignored) {
        }
    }

    public String input() {
        System.out.print("> ");
        try {
            String line = scanner.nextLine();
            if (line == null)
                return "";
            return line.trim().toLowerCase();
        } catch (Exception e) {
          e.printStackTrace();
          return "q";
        }
    }
}

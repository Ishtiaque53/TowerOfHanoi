package BFS;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Queue;
import java.util.Scanner;

public class TowerOfHanoi {
    static int countVisited=0;
    static Queue<State>  queue = new LinkedList<>();
    static LinkedList<State> queued = new LinkedList<>();
    static LinkedList<State> visited = new LinkedList<>();
    static LinkedList<State> shortPath = new LinkedList<>();

    public static void main(String[] args) {
        System.out.print("Destination poll number(1/2/3): ");
        Scanner scanner = new Scanner(System.in);
        int destPoll = Integer.parseInt(scanner.next());
        State initStage = new State();
        State finalStage = new State();
        finalStage.setArray(destPoll,destPoll,destPoll);
        System.out.println("BFS Parsed Nodes:");
        System.out.println("\t\tDiskA\tDiskB\tDiskC");
        enqueue(initStage, null);
        State nextStage = dequeue();
        boolean onLoop = true;
        while(onLoop) {
            if (nextStage.array[0]== finalStage.array[0] && nextStage.array[1]== finalStage.array[1] && nextStage.array[2]== finalStage.array[2]) {
                onLoop = false;
                State pathState = nextStage;
                while (pathState!=null){
                    shortPath.addFirst(pathState);
                    pathState = pathState.previousState;
                }
            }
            else {
                changeState(nextStage);
                nextStage = dequeue();
            }
        }
        System.out.println("Total Nodes Visited for solution: "+countVisited);
        System.out.println("Solution:");
        for (int i=0; i<shortPath.size(); i++){
            State printState = shortPath.get(i);
            System.out.print("[" + printState.array[0]+ "," + printState.array[1]+ "," + printState.array[2]+"]");
            if (i+1<shortPath.size()){
                System.out.print("===>>");
            }
        }
        System.out.println("\nShortest distance to solution: " + (shortPath.size()-1));
    }

    public static void enqueue(State state, State previousState) {
        if (!checkQueued(state)) {
            state.setPreviousState(previousState);
            queue.add(state);
            queued.add(state);
        }
    }

    public static State dequeue(){
        State state = queue.poll();
        if(!checkVisited(state)){
            visited.add(state);
            countVisited++;
            System.out.println("PollNo:\t[" + state.array[0]+ ",\t\t" + state.array[1]+ ",\t\t" + state.array[2]+"]");
            return state;
        }

        return dequeue();
    }

    public static boolean checkQueued(State state){
        boolean explored = false;
        ListIterator<State> listIterator = queued.listIterator();
        while (listIterator.hasNext()){
            State compareState = listIterator.next();
            if(compareState.array[0]==state.array[0] && compareState.array[1]==state.array[1] && compareState.array[2]==state.array[2]){
                explored = true;
            }
        }
        return explored;
    }

    public static boolean checkVisited(State state){
        boolean explored = false;
        ListIterator<State> listIterator = visited.listIterator();
        while (listIterator.hasNext()){
            State compareState = listIterator.next();
            if(compareState.array[0]==state.array[0] && compareState.array[1]==state.array[1] && compareState.array[2]==state.array[2]){
                explored = true;
            }
        }
        return explored;
    }


    public static void changeState(State state){
        if(state.array[0]==state.array[1] && state.array[1]==state.array[2]){
            int i = (state.array[0] % 3) + 1;
            int j = (i % 3) +1;
            State newState1 = new State();
            newState1.setArray(i,state.array[1],state.array[2]);
            enqueue(newState1, state);
            State newState2 = new State();
            newState2.setArray(j,state.array[1],state.array[2]);
            enqueue(newState2, state);
        }
        else if(state.array[0]==state.array[1] && state.array[0]!=state.array[2]){
            int i = (state.array[0] % 3) + 1;
            int j = (i % 3) +1;
            State newState1 = new State();
            newState1.setArray(state.array[0],state.array[1],i);
            enqueue(newState1, state);
            State newState2 = new State();
            newState2.setArray(state.array[0],state.array[1],j);
            enqueue(newState2, state);
            State newState3 = new State();
            newState3.setArray(i,state.array[1],state.array[2]);
            enqueue(newState3, state);
            State newState4 = new State();
            newState4.setArray(j,state.array[1],state.array[2]);
            enqueue(newState4, state);
        }
        else {
            int i = (state.array[0] % 3) + 1;
            int j = (i % 3) +1;
            State newState1 = new State();
            newState1.setArray(state.array[0],i,state.array[2]);
            enqueue(newState1, state);
            State newState2 = new State();
            newState2.setArray(state.array[0],j,state.array[2]);
            enqueue(newState2, state);
            State newState3 = new State();
            newState3.setArray(i,state.array[1],state.array[2]);
            enqueue(newState3, state);
            State newState4 = new State();
            newState4.setArray(j,state.array[1],state.array[2]);
            enqueue(newState4, state);
        }
    }
}


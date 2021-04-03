package BFS;

public class State {
    public int array[]= {1,1,1};
    public State previousState = null;

    public void setArray(int a, int b, int c) {
        this.array[0]=a;
        this.array[1]=b;
        this.array[2]=c;
    }

    public void setPreviousState(State previousState) {
        this.previousState = previousState;
    }
}

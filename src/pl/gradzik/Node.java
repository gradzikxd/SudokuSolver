package pl.gradzik;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Node
{
    private BoardButton button;

    public Node[] nextNode = new Node[9];
    private Node prev;

    private Queue<Node> queue = new LinkedList<>();

    private int value;

    private boolean alreadyCreatedNext;

    public Node(BoardButton button, Node prev,int value)
    {
        this.button = button;
        this.value = value;
        this.prev = prev;
        this.alreadyCreatedNext = false;

        for (int i = 0;i<9;i++)
        {
            nextNode[i] = null;
        }

    }

    public BoardButton getButton() {
        return button;
    }

    public Node getPrev() {
        return prev;
    }

    public void addToQueue(Node n)
    {
        queue.add(n);
    }

    public Node getFromQueue()
    {
        return queue.poll();
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Node{" +

                //", nextNode=" + Arrays.toString(nextNode) +
                ", prev=" + prev +
               // ", queue=" + queue +
                ", value=" + value +
                '}';
    }

    public boolean isAlreadyCreatedNext()
    {
        return alreadyCreatedNext;
    }

    public void setAlreadyCreatedNext(boolean alreadyCreatedNext) {
        this.alreadyCreatedNext = alreadyCreatedNext;
    }
}

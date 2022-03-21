package pl.gradzik;

import java.awt.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class Algorithm implements Runnable
{
    private Board board;

    public Algorithm(Board board)
    {
        this.board = board;


    }

    private static Tree tree;

    private int loops = 0;

    private BoardButton findNextButton()
    {
        for (int r = 0;r<9;r++)
        {
            for (int c = 0; c < 9; c++)
            {
                if(board.buttons[r][c].isButtonLock()) continue;

                return board.buttons[r][c];
            }
        }

        return null;
    }

    private Node findSolution(Node currentNode)
    {
        /*
        try
        {
            Thread.sleep(5);
        }
        catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }

         */

        System.out.println(loops);
        loops++;


        if (currentNode == null) return null;

        if (currentNode.getButton() == null) return null;

        currentNode.getButton().lockButton();

        if(!currentNode.isAlreadyCreatedNext()) //sprawdza czy Node nie został wcześniej stworzony
        {
            for (int i = 0;i<9;i++)
            {
                if(currentNode.getButton().isPossible(i+1)) //sprawdza co możemy podać w danym miejscu
                {
                    currentNode.nextNode[i] = new Node(findNextButton(),currentNode,i + 1);
                    currentNode.addToQueue(currentNode.nextNode[i]);
                }
            }
            currentNode.setAlreadyCreatedNext(true);
        }

        Node nextNode = currentNode.getFromQueue();

        if(nextNode == null) // resetujemy obecny button
        {
            currentNode.getButton().setValue(0);
            currentNode.getButton().unlockButton();
            return findSolution(currentNode.getPrev());  // wracamy sie button wczesniej
        }

        currentNode.getButton().setValue(nextNode.getValue());


        if(board.isSolutionPossible())  return findSolution(nextNode);
        else
        {
            currentNode.getButton().setValue(0);
            return findSolution(currentNode);
        }

    }

    private void findSolutionIteratively(Node node)
    {
        Node currentNode = node;

        while(true)
        {

        try
        {
            Thread.sleep(5);
        }
        catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }

            System.out.println(loops);
            loops++;


            if (currentNode == null) break;

            if (currentNode.getButton() == null) break;

            currentNode.getButton().lockButton();

            if(!currentNode.isAlreadyCreatedNext()) //sprawdza czy Node nie został wcześniej stworzony
            {
                for (int i = 0;i<9;i++)
                {
                    if(currentNode.getButton().isPossible(i+1)) //sprawdza co możemy podać w danym miejscu
                    {
                        currentNode.nextNode[i] = new Node(findNextButton(),currentNode,i + 1);
                        currentNode.addToQueue(currentNode.nextNode[i]);
                    }
                }
                currentNode.setAlreadyCreatedNext(true);
            }

            Node nextNode = currentNode.getFromQueue();

            if(nextNode == null) // resetujemy obecny button
            {
                currentNode.getButton().setValue(0);
                currentNode.getButton().unlockButton();
                currentNode = currentNode.getPrev();  // wracamy sie button wczesniej
                continue;
            }

            currentNode.getButton().setValue(nextNode.getValue());


            if(board.isSolutionPossible())
            {
                currentNode = nextNode;
                continue;
            }

        }
    }



    @Override
    public void run()
    {
         tree = new Tree(new Node(findNextButton(),null,0));

        //findSolution(tree.getStartPoint());  //recursive version
        findSolutionIteratively(tree.getStartPoint()); //iteratively version

    }

}

package pl.gradzik;

import javax.swing.*;
import java.awt.*;

public class BoardButton extends JButton
{
    private JLabel smallText = new JLabel();

    private boolean[] possibilities = new boolean[10];

    private int currentNumber;

    private boolean lockButton;

    Group row;
    Group column;
    Group rect;


    public BoardButton(Group row, Group column, Group rect)
    {
        this.row = row;
        this.column = column;
        this.rect = rect;
        this.lockButton = false;

        this.setBackground(Color.WHITE);
        this.setBorderPainted(false);
        this.setFont(new Font("Arial",Font.PLAIN,40));
        this.add(smallText);
        this.setLayout(null);
        this.setFocusable(false);

        smallText.setHorizontalTextPosition(JLabel.LEFT); //set text LEFT,CENTER,RIGHT
        smallText.setVerticalTextPosition(JButton.TOP); //set text BOTTOM,CENTER,TOP
        smallText.setVerticalAlignment(JLabel.TOP);
        smallText.setHorizontalAlignment(JButton.LEFT);
        smallText.setBounds(0,0,90,90);
        smallText.setFont(new Font("Arial",Font.PLAIN,10));

        currentNumber = 0;


        for (int i = 0;i<10;i++)
        {
            possibilities[i] = true;
        }
        updateSmallText();

    }



    public void click()
    {

        if(currentNumber == 9)
        {
            currentNumber = 0;
            this.setText("");
            row.updateGroup();
            column.updateGroup();
            rect.updateGroup();
            return;
        }

        while (!possibilities[currentNumber + 1])
        {
            currentNumber++;

            if(currentNumber == 9)
            {
                currentNumber = 0;
                this.setText("");
                row.updateGroup();
                column.updateGroup();
                rect.updateGroup();
                return;
            }
        }

        currentNumber++;
        this.setText(String.valueOf(currentNumber));

        row.updateGroup();
        column.updateGroup();
        rect.updateGroup();
    }

    public void updateSmallText()
    {
        String text = "";
        for (int i = 1;i<10;i++)
        {
            if(possibilities[i])
            {
                text += i;
            }
        }
        smallText.setText(text);
    }

    public void updatePossibilities()
    {
        for(int i = 1;i<10;i++)
        {
            if (!row.isPossible(i) || !column.isPossible(i) || !rect.isPossible(i))
            {
                possibilities[i] = false;
            }
            else  possibilities[i] = true;
        }

    }

    public void setValue(int n)
    {
        currentNumber = n;
        if (n != 0) this.setText(String.valueOf(n));
        else this.setText("");
        row.updateGroup();
        column.updateGroup();
        rect.updateGroup();
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public int getCurrentNumber() {
        return currentNumber;
    }

    public boolean isButtonLock()
    {
        return  lockButton;
    }

    public void lockButton()
    {
        lockButton = true;
    }

    public void unlockButton()
    {
        lockButton = false;
    }

    protected void resetCurrentNumber()
    {
        currentNumber = 0;
        this.setText("");
        row.updateGroup();
        column.updateGroup();
        rect.updateGroup();
    }

    public int getNumberOfPossibilities()
    {
        int n = 0;

        for (int i = 1;i<10;i++)
        {
            if(possibilities[i])
            {
                n += i;
            }
        }

        return n;
    }

    public boolean isPossible(int x)
    {
        return possibilities[x];
    }


}

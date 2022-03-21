package pl.gradzik;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Board implements ActionListener
{
    private JPanel panel;

    protected BoardButton[][] buttons;

    private Group[] row = new Group[9];
    private Group[] column = new Group[9];
    private Group[] rect = new Group[9];

    public Board(JPanel panel)
    {
        this.panel = panel;
        buttons = new BoardButton[9][9];

        int x = 5;
        int y = 5;

        for (int i = 0;i<9;i++)
        {
            row[i] = new Group();
            column[i] = new Group();
            rect[i] = new Group();
        }

        int counter = 0;

        int rectCounter = 0;
        int rectCounterValue = 0;

        int positionInRectCounter = 0;
        int positionInRectCounterValue = 0;

        for (int r = 0;r<9;r++)
        {
            for (int c = 0;c<9;c++)  //main loop
            {
                buttons[r][c] = new BoardButton(row[r],column[c],rect[rectCounter]);
                buttons[r][c].setBounds(x,y,90,90);
                panel.add( buttons[r][c]);
                buttons[r][c].addActionListener(this);

                row[r].setButton(c,buttons[r][c]);
                column[c].setButton(r,buttons[r][c]);

                rect[rectCounter].setButton(positionInRectCounter, buttons[r][c]);

                counter++;
                positionInRectCounter++;

                if (counter % 27 == 0)
                {
                    rectCounterValue += 3;
                    positionInRectCounterValue = 0;
                    positionInRectCounter = 0;
                    rectCounter = rectCounterValue;

                    break;
                }

                if (counter % 9 == 0)
                {
                    positionInRectCounterValue += 3;
                    rectCounter = rectCounterValue;
                    positionInRectCounter = positionInRectCounterValue;
                    break;
                }

                if (counter % 3 == 0)
                {
                    rectCounter += 1;
                    positionInRectCounter = positionInRectCounterValue;
                }


                x += 95;

                if(c == 2 || c == 5 || c == 8)
                {
                    x += 5;
                }
            }

            x = 5;
            y += 95;

            if(r == 2 || r == 5 || r == 8)
            {
                y += 5;
            }
        }

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        for (int r = 0;r<9;r++)
        {
            for (int c = 0; c < 9; c++)
            {
                if(e.getSource() == buttons[r][c] && !buttons[r][c].isButtonLock())
                {
                    buttons[r][c].click();
                }
            }
        }
    }

    public void lockButtons()
    {
        for (int r = 0;r<9;r++)
        {
            for (int c = 0; c < 9; c++)
            {
                if(buttons[r][c].getCurrentNumber() != 0)
                {
                    buttons[r][c].lockButton();
                    buttons[r][c].setBackground(Color.lightGray);
                }
            }
        }

    }

    public void claerBoard()
    {
        for (int r = 0;r<9;r++)
        {
            for (int c = 0; c < 9; c++)
            {
                    buttons[r][c].unlockButton();;
                    buttons[r][c].setBackground(Color.WHITE);
                    buttons[r][c].resetCurrentNumber();

            }
        }
    }


    public boolean isSolutionPossible()
    {
        for (int r = 0;r<9;r++)
        {
            for (int c = 0; c < 9; c++)
            {
                if(buttons[r][c].getCurrentNumber() == 0 && buttons[r][c].getNumberOfPossibilities() == 0) return false;
            }
        }

        return true;

    }
}

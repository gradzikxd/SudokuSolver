package pl.gradzik;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame implements ActionListener
{
    private JPanel topPanel;
    private JPanel sudokuPanel;

    private JButton solveAutomaticallyButton;
    private JButton lockAndSolveButton;
    private JButton clearButton;

    private Board board;

    private Algorithm algorithm;

    public MainFrame() throws HeadlessException
    {
        this.setTitle("Sudoku");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(886,939);
        this.setResizable(false);
        this.setLayout(null);
        this.setVisible(true);

        createPanels();
        createButtons();

        board = new Board(sudokuPanel);

        algorithm = new Algorithm(board);

        SwingUtilities.updateComponentTreeUI(this);        //refresh frame
    }

    private void createPanels()
    {
        topPanel = new JPanel();
        topPanel.setBounds(0,0,886,30);
        topPanel.setBackground(Color.gray);
        topPanel.setLayout(null);
        this.add(topPanel);

        sudokuPanel = new JPanel();
        sudokuPanel.setBounds(0,30,886,900);
        sudokuPanel.setBackground(Color.BLACK);
        sudokuPanel.setLayout(null);
        this.add(sudokuPanel);


    }

    private void createButtons()
    {
        solveAutomaticallyButton = new JButton();
        solveAutomaticallyButton.setBounds(3,3,150,25);
        solveAutomaticallyButton.setBackground(Color.white);
        solveAutomaticallyButton.addActionListener(this);
        solveAutomaticallyButton.setText("Solve Automatically");
        solveAutomaticallyButton.setFocusable(false);

        lockAndSolveButton = new JButton();
        lockAndSolveButton.setBounds(156,3,150,25);
        lockAndSolveButton.setBackground(Color.white);
        lockAndSolveButton.addActionListener(this);
        lockAndSolveButton.setText("Lock and solve");
        lockAndSolveButton.setFocusable(false);

        clearButton = new JButton();
        clearButton.setBounds(767,3,100,25);
        clearButton.setBackground(Color.white);
        clearButton.addActionListener(this);
        clearButton.setText("Clear");
        clearButton.setFocusable(false);

        topPanel.add(solveAutomaticallyButton);
        topPanel.add(lockAndSolveButton);
        topPanel.add(clearButton);

    }


    @Override
    public void actionPerformed(ActionEvent e)
    {

        if(e.getSource() == solveAutomaticallyButton)
        {
            new Thread(algorithm).start();
        }

        if(e.getSource() == lockAndSolveButton)
        {
            board.lockButtons();
        }

        if(e.getSource() == clearButton)
        {
            board.claerBoard();
        }

    }
}

package pl.gradzik;

public class Group
{
    public BoardButton[] group = new BoardButton[9];

    private boolean[] possibilitiesInGroup = new boolean[10];

    public Group()
    {
        for(int i = 0;i<10;i++)
        {
            possibilitiesInGroup[i] = true;
        }
    }

    public boolean isPossible(int x)
    {
        return possibilitiesInGroup[x];
    }

    public void updateGroup()
    {
        for(int i = 0;i<10;i++)
        {
            possibilitiesInGroup[i] = true;
        }

        for(int i = 0;i<9;i++)
        {
            if(group[i].getCurrentNumber() != 0)
            {
                possibilitiesInGroup[group[i].getCurrentNumber()] = false;
            }
        }

        for(int i = 0;i<9;i++)
        {
            group[i].updatePossibilities();
            group[i].updateSmallText();
        }

    }

    public void setButton(int x, BoardButton button)
    {
        group[x] = button;
    }

}

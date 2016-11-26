/*
 *  (c) Copyright 2016 Dougal Seeley.  All rights reserved.
 */
package DrawGen.Draw;

/**
 * 4-Way Formation Skydiving Draw
 */
public class Draw4WayFS extends Draw
{
    public Draw4WayFS(String DrawName, int MinPointsPerRound, int NumRounds, int[] invalidBlocks, String[] invalidRandoms)
    {
        super(DrawName, MinPointsPerRound, NumRounds, invalidBlocks, invalidRandoms);
    }

    protected void loadDivePool(java.util.ArrayList<Formation> inDivePool)
    {
        inDivePool.clear();

        for (int i = 0; i < Formation.FOURWAYFSBLOCKS.length; i++)
        {
            Boolean isValidBlock = true;
            for (int j = 0; j < this.invalidBlocks.length; j++)
            {
                if (Formation.FOURWAYFSBLOCKS[i].index.equals(String.valueOf(this.invalidBlocks[j])))
                {
                    isValidBlock = false;
                    break;
                }
            }
            if (isValidBlock == true)
            {
                inDivePool.add(Formation.FOURWAYFSBLOCKS[i]);
            }
        }

        for (int i = 0; i < Formation.FOURWAYFSRANDOMS.length; i++)
        {
            Boolean isValidRandom = true;
            for (int j = 0; j < this.invalidRandoms.length; j++)
            {
                if (Formation.FOURWAYFSRANDOMS[i].index.equals(String.valueOf(this.invalidRandoms[j])))
                {
                    isValidRandom = false;
                    break;
                }
            }
            if (isValidRandom == true)
            {
                inDivePool.add(Formation.FOURWAYFSRANDOMS[i]);
            }
        }
    }

}

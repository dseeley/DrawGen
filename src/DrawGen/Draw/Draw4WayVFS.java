/*
 *  (c) Copyright 2016 Dougal Seeley.  All rights reserved.
 */
package DrawGen.Draw;

/**
 * 4-Way Vertical Formation Skydiving Draw
 */
public class Draw4WayVFS extends Draw
{
    public Draw4WayVFS(String DrawName, int MinPointsPerRound, int NumRounds, int[] invalidBlocks, String[] invalidRandoms)
    {
        super(DrawName, MinPointsPerRound, NumRounds, invalidBlocks, invalidRandoms);
    }

    protected void loadDivePool(java.util.ArrayList<Formation> inDivePool)
    {
        inDivePool.clear();

        for (int i = 0; i < Formation.FOURWAYVFSBLOCKS.length; i++)
        {
            Boolean isValidBlock = true;
            for (int j = 0; j < this.invalidBlocks.length; j++)
            {
                if (Formation.FOURWAYVFSBLOCKS[i].index.equals(String.valueOf(this.invalidBlocks[j])))
                {
                    isValidBlock = false;
                    break;
                }
            }
            if (isValidBlock == true)
            {
                inDivePool.add(Formation.FOURWAYVFSBLOCKS[i]);
            }
        }

        for (int i = 0; i < Formation.FOURWAYVFSRANDOMS.length; i++)
        {
            Boolean isValidRandom = true;
            for (int j = 0; j < this.invalidRandoms.length; j++)
            {
                if (Formation.FOURWAYVFSRANDOMS[i].index.equals(String.valueOf(this.invalidRandoms[j])))
                {
                    isValidRandom = false;
                    break;
                }
            }
            if (isValidRandom == true)
            {
                inDivePool.add(Formation.FOURWAYVFSRANDOMS[i]);
            }
        }
    }

}

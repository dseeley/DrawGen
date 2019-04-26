/*
 *  (c) Copyright 2016 Dougal Seeley.  All rights reserved.
 */
package DrawGen.Draw;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Base class definition of a skydiving draw.  A draw consists of rounds, each of which consists of formations.  Depending
 * on the governing body, the skydiving discipline and the category of the draw, the rules defining the draw are different.
 */
public abstract class Draw
{
    public static final int ROUNDSPACING = 15;

    protected ArrayList<Round> draw = new ArrayList<>();            // The list of rounds that constitute a skydiving draw.
    private ArrayList<Formation> divePool = new ArrayList<>();      // The pool of skydives for this subclass of Draw
    protected int numRounds;                //Number of rounds in this draw
    protected String drawName = "";         //Name of the draw
    protected int minPointsPerRound;        //Minimum number of points (1 less than the maximum)
    protected int invalidBlocks[];          //Blocks that aren't valid for this draw
    protected String invalidRandoms[];      //Randoms that aren't valid for this draw

    public Draw(String drawName, int minPointsPerRound, int numRounds, int invalidBlocks[], String invalidRandoms[])
    {
        this.drawName = drawName;
        this.minPointsPerRound = minPointsPerRound;
        this.numRounds = numRounds;
        this.invalidBlocks = invalidBlocks;
        this.invalidRandoms = invalidRandoms;

        this.initialiseDraw();
    }

    /**
     * This is needed for the PrepopulateComboBoxModel, which does a toString() on each of its elements in order to populate the dropdown.
     */
    public String toString()
    {
        return this.drawName;
    }

    public ArrayList<Formation> getDivePool()
    {
        return this.divePool;
    }

    public ArrayList<Round> getDraw()
    {
        return this.draw;
    }

    public String getDrawName()
    {
        return this.drawName;
    }

    public void initialiseDraw()
    {
        for (Round roundElem : this.draw)
        {
            roundElem.ClearRound();
        }

        this.draw.clear();

        this.loadDivePool(this.divePool);
    }

    public void addRound(Round inRound)
    {
        this.draw.add(inRound);
    }

    /**
     * Overridden by all - each derivative loads specific formations into the pool.
     */
    protected abstract void loadDivePool(java.util.ArrayList<Formation> inDivePool);


    /**
     * Creates a single image consisting of all the rounds, arranged vertically.
     * Writes a round title above each round.
     */
    public BufferedImage getImg()
    {
        BufferedImage img = null;
        if (this.draw.size() > 0)
        {
            int xPos = 0, yPos = 0;
            int roundNumberSpacer = 20;    //pixels

            img = new BufferedImage((int) (this.getTotalDrawWidthPx()), (int) (this.getMaxRoundHeightPx()) + roundNumberSpacer, BufferedImage.TYPE_INT_ARGB);
            Graphics2D buf_g2d = img.createGraphics();

            buf_g2d.setColor(java.awt.Color.WHITE);
            buf_g2d.fillRect(0, 0, this.getTotalDrawWidthPx(), this.getMaxRoundHeightPx() + roundNumberSpacer);
            buf_g2d.setColor(java.awt.Color.BLACK);

            for (int RoundCount = 0; RoundCount < this.getDraw().size(); RoundCount++)
            {
                Round RoundElem = this.getDraw().get(RoundCount);

                buf_g2d.setFont(buf_g2d.getFont().deriveFont((float) (20.0)));
                buf_g2d.drawString("Round " + (RoundCount + 1), xPos, yPos + roundNumberSpacer - 3);
                buf_g2d.drawImage(RoundElem.getImg(), xPos, yPos + roundNumberSpacer, null);
                xPos += RoundElem.GetRoundWidthPx() + Draw.ROUNDSPACING;
            }
        }

        return img;
    }

    /**
     * Get the draw as a line-separated list of rounds, each of which is a comma-separated list of formations.
     */
    public String getDrawText()
    {
        String drawText = "";

        for (int RoundCount = 0; RoundCount < this.getDraw().size(); RoundCount++)
        {
            for (int RoundIndex = 0; RoundIndex < this.getDraw().get(RoundCount).getNumDives() - 1; RoundIndex++)
            {
                Formation CurFormation = this.getDraw().get(RoundCount).getFormationAt(RoundIndex);
                drawText += CurFormation.index + ", ";
            }
            drawText += this.getDraw().get(RoundCount).getFormationAt(this.getDraw().get(RoundCount).getNumDives() - 1).index + "\n";
        }

        return drawText;
    }

    public int getMaxRoundHeightPx()
    {
        int MaxRoundHeightPx = 0;

        for (Round roundElem : this.draw)
        {
            int CurRoundHeight = roundElem.GetRoundHeightPx();

            if (CurRoundHeight > MaxRoundHeightPx)
            {
                MaxRoundHeightPx = CurRoundHeight;
            }
        }

        return MaxRoundHeightPx;
    }

    public int getTotalDrawWidthPx()
    {
        int TotalDrawWidthPx = 0;

        for (Round roundElem : this.draw)
        {
            TotalDrawWidthPx += roundElem.GetRoundWidthPx() + Draw.ROUNDSPACING;
        }

        return TotalDrawWidthPx - Draw.ROUNDSPACING;
    }

    /*
     * Create a Draw consisting of all the valid blocks and randoms.
     *
     * Used by the Manual draw type for prepopulating the text area.
     */
    public void generateAll()
    {
        this.initialiseDraw();

        while (this.divePool.size() > 0)
        {
            Round newRound = new Round();

            while ((newRound.GetNumPoints() < this.minPointsPerRound) && this.divePool.size() > 0)
            {
                newRound.AddFormation(this.divePool.get(0));

                this.divePool.remove(0);
            }
            this.draw.add(newRound);
        }
    }

    /**
     * Perform the generation of a randomised draw according to the minPointsPerRound, numRounds and valid formations.
     */
    public void generateRandomised()
    {
        this.initialiseDraw();

        for (int roundCount = 0; roundCount < this.numRounds; roundCount++)
        {
            Round newRound = new Round();

            while (newRound.GetNumPoints() < this.minPointsPerRound)
            {
                if (this.divePool.isEmpty())
                {
                    this.loadDivePool(this.divePool);
                }

                int FormationIndex = getNewRandNum(this.divePool.size());

                newRound.AddFormation(this.divePool.get(FormationIndex));

                this.divePool.remove(FormationIndex);
            }

            this.draw.add(newRound);
        }
    }

    /**
     * Perform the generation of a randomised draw according to the minPointsPerRound, numRounds and valid formations, but
     * using an existing draw as a template. Use all the formations from the existing draw that are valid for the new one,
     * remove those that aren't valid, then top up with valid formations.
     */
    public void generateRandomisedFromDraw(Draw inDraw)
    {
        this.initialiseDraw();
        this.loadDivePool(this.divePool);

        /* Copy the inDraw skydive, exclude the formations not in this.divePool, into this.draw */
        for (int roundCount = 0; roundCount < this.numRounds; roundCount++)
        {
            Round roundToCopy = inDraw.draw.get(roundCount);
            Round roundCopy = new Round();

            for (int RoundIndex = 0; RoundIndex < roundToCopy.getNumDives(); RoundIndex++)
            {
                Formation element = roundToCopy.getFormationAt(RoundIndex);

                if (roundCopy.GetNumPoints() < this.minPointsPerRound)
                {
                    /* If the pool becomes emtpy, refill it. */
                    if (this.divePool.isEmpty())
                    {
                        this.loadDivePool(this.divePool);
                    }

                    if (this.divePool.contains(element))
                    {
                        roundCopy.AddFormation(element);

                        this.divePool.remove(element);
                    }
                }
            }

            this.draw.add(roundCopy);
        }

        /* For each of the new rounds, add elements as necessary. NOTE: This stage has to be done AFTER the CopyRound, otherwise the pool wont be repopulated */
        for (Round roundElem : this.draw)
        {
            /* Add dives to the round until minPointsPerRound is reached. */
            while (roundElem.GetNumPoints() < this.minPointsPerRound)
            {
                if (this.divePool.isEmpty())
                {
                    this.loadDivePool(this.divePool);
                }

                int FormationIndex = getNewRandNum(this.divePool.size());

                roundElem.AddFormation(this.divePool.get(FormationIndex));

                this.divePool.remove(FormationIndex);
            }
        }
    }

    /**
     * Utility function to create a random number within a specified range.
     */
    private int getNewRandNum(int range)
    {
        return (Math.abs(new java.util.Random().nextInt() % range));
    }
}

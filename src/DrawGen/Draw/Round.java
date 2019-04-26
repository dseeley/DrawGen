/*
 *  (c) Copyright 2016 Dougal Seeley.  All rights reserved.
 */
package DrawGen.Draw;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author dseeley
 */
public class Round
{
    private static int FormationSpacing = 10;
    public ArrayList<Formation> formationList = new ArrayList<>();

    public Round()
    {
    }

    public Round(ArrayList<Formation> vFormations)
    {
        for (Formation f : vFormations)
        {
            this.AddFormation(f);
        }
    }

    public static int GetFormationSpacing()
    {
        return Round.FormationSpacing;
    }

    public int getNumDives()
    {
        return this.formationList.size();
    }

    public int GetNumPoints()
    {
        int NumPoints = 0;
        for (Formation formationElem : this.formationList)
        {
            if (formationElem instanceof Formation.cBlock)
            {
                NumPoints += 2;
            }
            else
            {
                NumPoints += 1;
            }
        }
        return NumPoints;
    }

    public int GetRoundHeightPx()
    {
        int RoundHeight = 0;

        for (Formation formationElem : this.formationList)
        {
            RoundHeight += formationElem.buffImg.getHeight(null) + Round.FormationSpacing;
        }
        return RoundHeight + 2 - Round.FormationSpacing;
    }

    public int GetRoundWidthPx()
    {
        int RoundWidth = 0;
        for (Formation formationElem : this.formationList)
        {
            BufferedImage buffImg = formationElem.buffImg;
            if (buffImg.getWidth(null) > RoundWidth)
            {
                RoundWidth = buffImg.getWidth(null);
            }
        }
        return RoundWidth;
    }

    public Formation getFormationAt(int index)
    {
        return this.formationList.get(index);
    }

    public void AddFormation(Formation Formation)
    {
        this.formationList.add(Formation);
    }

    public void RemoveFormation(Formation Formation)
    {
        this.formationList.remove(Formation);
    }

    public void ClearRound()
    {
        this.formationList.clear();
    }

    public BufferedImage getImg()
    {
        int xPos = 0, yPos = 0;

        BufferedImage img = new BufferedImage(this.GetRoundWidthPx(), this.GetRoundHeightPx(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();

        for (Formation formationElem : this.formationList)
        {
            BufferedImage formationImg = formationElem.buffImg;

            /* Draw the formation */
            g2d.setColor(java.awt.Color.BLACK);
            g2d.drawImage(formationImg, xPos, yPos, null);

            /* Draw a box around it */
            g2d.draw(new java.awt.Rectangle(xPos, yPos, formationImg.getWidth()-1, formationImg.getHeight()-1));

            /* Draw the letter or number of the random/block
             * This is not a great idea - font scaling would need to change because the image is scaled if it doesn't fit the height.
             */
//            g2d.drawString(elem.Index, xOrigin+5, yPos + g2d.getFontMetrics().getHeight());

            /* drawLine the lines between the inters (two lines) */
            if (formationElem instanceof Formation.cBlock)
            {
                int ThirdHeight = (int) (((formationImg.getHeight(null) / 3.0)));

                /* Lines */
                g2d.drawLine(xPos + 1, yPos + ThirdHeight, xPos + formationImg.getWidth() - 1, yPos + ThirdHeight);           //First Divider
                g2d.drawLine(xPos + 1, yPos + ThirdHeight * 2, xPos + formationImg.getWidth() - 1, yPos + ThirdHeight * 2);   //Lower divider

                /* drawStr the Formation names
                 * This is not a great idea - font scaling would need to change because the image is scaled if it doesn't fit the height.
                 */
//                String Name1Str = ((cFormations.cBlock) elem).Name1;
//                String Name2Str = ((cFormations.cBlock) elem).Name2;
//                String InterStr = "(Inter)";
//
//                g2d.drawString(Name1Str, xOrigin + ((formationImg.getWidth() - g2d.getFontMetrics().stringWidth(Name1Str)) / 2) + 1, yPos + ThirdHeight - 3);
//                g2d.drawString(InterStr, xOrigin + ((formationImg.getWidth() - g2d.getFontMetrics().stringWidth(InterStr)) / 2) + 1, yPos + ThirdHeight * 2 - 3);
//                g2d.drawString(Name2Str, xOrigin + ((formationImg.getWidth() - g2d.getFontMetrics().stringWidth(Name2Str)) / 2) + 1, yPos + ThirdHeight * 3 - 3);
            }
//            else
//            {
//                String NameStr = ((cFormations.cRandom) elem).Name;
//                g2d.drawString(NameStr, xOrigin + ((formationImg.getWidth() - g2d.getFontMetrics().stringWidth(NameStr)) / 2) + 1, yPos + formationImg.getHeight() - 3);
//            }
            yPos += (formationImg.getHeight() + Round.FormationSpacing);
        }

        return img;
    }
}

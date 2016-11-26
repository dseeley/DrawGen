/*
 *  (c) Copyright 2016 Dougal Seeley.  All rights reserved.
 */
package DrawGen.Draw.DrawHieararchy;

import DrawGen.Draw.Draw;

/**
 *
 * @author dougal
 */
public class Category
{
    private String name = "";
    private Draw draw;
    private String drawID;

    public Category(String name)
    {
        this.name = name;
    }

    public void addDraw(String ID, Draw newDraw)
    {
        this.drawID = ID;
        this.draw = newDraw;
    }

    public Draw getDraw()
    {
        return this.draw;
    }
}

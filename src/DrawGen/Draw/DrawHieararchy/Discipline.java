/*
 *  (c) Copyright 2016 Dougal Seeley.  All rights reserved.
 */
package DrawGen.Draw.DrawHieararchy;

import java.util.LinkedHashMap;

/**
 *
 * @author dougal
 */
public class Discipline
{
    private String name = "";
    private final LinkedHashMap<String, Category> categoryHash = new LinkedHashMap<>();

    public Discipline(String name)
    {
        this.name = name;
    }

    public Category addCategory(String ID, Category newCategory)
    {
        categoryHash.put(ID, newCategory);
        return newCategory;
    }

    public Category getCategory(String ID)
    {
        return categoryHash.get(ID);
    }

    public void empty()
    {
        for (String aCategoryKey : categoryHash.keySet())
        {
            categoryHash.get(aCategoryKey).getDraw().initialiseDraw();
        }
    }

    public LinkedHashMap<String, Category> getCategoryHash()
    {
        return categoryHash;
    }
}


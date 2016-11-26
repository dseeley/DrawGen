/*
 *  (c) Copyright 2016 Dougal Seeley.  All rights reserved.
 */
package DrawGen.Draw.DrawHieararchy;

import java.util.LinkedHashMap;

/**
 *
 * @author dougal
 */
public class GovBody
{
    private String name = "";
    private final LinkedHashMap<String, Discipline> disciplineHash = new LinkedHashMap<>();

    public GovBody(String name)
    {
        this.name = name;
    }

    public Discipline addDiscipline(String ID, Discipline newDiscipline)
    {
        disciplineHash.put(ID, newDiscipline);
        return newDiscipline;
    }

    public Discipline getDiscipline(String ID)
    {
        return disciplineHash.get(ID);
    }

    public void empty()
    {
        for (String aDisciplineKey : disciplineHash.keySet())
        {
            disciplineHash.get(aDisciplineKey).empty();
        }

    }
}

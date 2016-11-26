/*
 *  (c) Copyright 2016 Dougal Seeley.  All rights reserved.
 */
package DrawGen.Draw.DrawHieararchy;

import DrawGen.Draw.Draw4WayFS;
import DrawGen.Draw.Draw4WayVFS;
import DrawGen.Draw.Draw8WayFS;
import java.util.LinkedHashMap;

/**
 *
 * @author dougal
 */
public class DrawHieararchy
{
    private LinkedHashMap<String, GovBody> govBodyHash = new LinkedHashMap<>();

    public LinkedHashMap<String, GovBody> getGovBodyHash()
    {
        return govBodyHash;
    }

    public DrawHieararchy()
    {
        GovBody IPCGovBody = new GovBody("IPC");
        IPCGovBody.addDiscipline("FOURWAYFS", new Discipline("FOURWAYFS")).addCategory("OPEN", new Category("OPEN")).addDraw("IPCFOURWAYFSOPEN", new Draw4WayFS("IPC 4 Way FS", 5, 10, new int[0], new String[0]));
        IPCGovBody.addDiscipline("FOURWAYVFS", new Discipline("FOURWAYVFS")).addCategory("OPEN", new Category("OPEN")).addDraw("IPCFOURWAYVFSOPEN", new Draw4WayVFS("IPC 4 Way VFS", 5, 8, new int[0], new String[0]));
        IPCGovBody.addDiscipline("EIGHTWAYFS", new Discipline("EIGHTWAYFS")).addCategory("OPEN", new Category("OPEN")).addDraw("IPCEIGHTWAYFSOPEN", new Draw8WayFS("IPC 8 Way FS", 5, 10, new int[0], new String[0]));
        this.govBodyHash.put("IPC", IPCGovBody);

        /* "UKSL 4 Way Rules 2016" document: http://www.bpa.org.uk/forms/download/301/pdf
         * Rookies: All randoms, no blocks.
         * A: All randoms, valid blocks:  2, 4, 6, 7, 8, 9, 19, 21
         * AA: All randoms, valid blocks:  1, 2, 4, 6, 7, 8, 9, 11, 13, 14, 15, 18, 19, 20, 21, 22
         * AAA: All randoms, all blocks.
         */
        GovBody BPAGovBody = new GovBody("BPA");
        Discipline BPAFOURWAYFSDiscipline = BPAGovBody.addDiscipline("FOURWAYFS", new Discipline("FOURWAYFS"));
        BPAFOURWAYFSDiscipline.addCategory("AAA", new Category("AAA")).addDraw("BPAFOURWAYFSAAA", new Draw4WayFS("BPA 4 Way FS 'AAA'", 5, 10, new int[0], new String[0]));
        BPAFOURWAYFSDiscipline.addCategory("AA", new Category("AA")).addDraw("BPAFOURWAYFSAA", new Draw4WayFS("BPA 4 Way FS 'AA'", 4, 8, new int[]{3,5,10,12,16,17}, new String[0]));
        BPAFOURWAYFSDiscipline.addCategory("A", new Category("A")).addDraw("BPAFOURWAYFSA", new Draw4WayFS("BPA 4 Way FS 'A'", 3, 8, new int[]{1,3,5,10,11,12,13,14,15,16,17,18,20,22}, new String[0]));
        BPAFOURWAYFSDiscipline.addCategory("ROOKIES", new Category("ROOKIES")).addDraw("BPAFOURWAYFSROOKIES", new Draw4WayFS("BPA 4 Way FS 'Rookies'", 3, 8, new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22}, new String[0]));

        Discipline BPAFOURWAYVFSDiscipline = BPAGovBody.addDiscipline("FOURWAYVFS", new Discipline("FOURWAYVFS"));
        BPAFOURWAYVFSDiscipline.addCategory("OPEN", new Category("OPEN")).addDraw("BPAFOURWAYVFSOPEN", new Draw4WayVFS("BPA 4 Way VFS", 5, 10, new int[0], new String[0]));

        /* No documentation for this, found on facebook 'about' page: https://www.facebook.com/BPA-8-way-Intermediate-1681750252098733/about/?ref=page_internal
         * "The dive pool is Blocks 1, 4, 6, 13, 14, 18, 19, 21 with Randoms B, F, G, J, K, M, N, P."
         */
        Discipline BPAEIGHTWAYFSDiscipline = BPAGovBody.addDiscipline("EIGHTWAYFS", new Discipline("EIGHTWAYFS"));
        BPAEIGHTWAYFSDiscipline.addCategory("SENIOR", new Category("SENIOR")).addDraw("BPAFEIGHTWAYFSFSSENIOR", new Draw8WayFS("BPA 8 Way FS 'Senior'", 5, 10, new int[0], new String[0]));
        BPAEIGHTWAYFSDiscipline.addCategory("INTERMEDIATE", new Category("INTERMEDIATE")).addDraw("BPAFEIGHTWAYFSFSINTERMEDIATE", new Draw8WayFS("BPA 8 Way FS 'Intermediate'", 3, 8, new int[]{2,3,5,7,8,9,10,11,12,15,16,17,20,22}, new String[]{"A","C","D","E","H","L","O","Q"}));
        this.govBodyHash.put("BPA", BPAGovBody);

        /* Taken direct from the "U.S. National Formation Skydiving Championships Competition Rules, Chapter 9" document */
        GovBody USPAGovBody = new GovBody("USPA");
        Discipline USPAFOURWAYFSDiscipline = USPAGovBody.addDiscipline("FOURWAYFS", new Discipline("FOURWAYFS"));
        USPAFOURWAYFSDiscipline.addCategory("OPEN", new Category("OPEN")).addDraw("USPAFFOURWAYFSFSOPEN", new Draw4WayFS("USPA 4 Way FS 'Open'", 5, 10, new int[0], new String[0]));
        USPAFOURWAYFSDiscipline.addCategory("ADVANCED", new Category("ADVANCED")).addDraw("USPAFFOURWAYFSFSADVANCED", new Draw4WayFS("USPA 4 Way FS 'Advanced'", 5, 10, new int[0], new String[0]));
        USPAFOURWAYFSDiscipline.addCategory("INTERMEDIATE", new Category("INTERMEDIATE")).addDraw("USPAFFOURWAYFSFSINTERMEDIATE", new Draw4WayFS("USPA 4 Way FS 'Intermediate'", 4, 10, new int[]{3,5,10,12,16,17}, new String[0]));

        Discipline USPAFOURWAYVFSDiscipline = USPAGovBody.addDiscipline("FOURWAYVFS", new Discipline("FOURWAYVFS"));
        USPAFOURWAYVFSDiscipline.addCategory("OPEN", new Category("OPEN")).addDraw("USPAFFOURWAYVFSFSOPEN", new Draw4WayVFS("USPA 4 Way VFS 'Open'", 5, 8, new int[0], new String[0]));
        USPAFOURWAYVFSDiscipline.addCategory("ADVANCED", new Category("ADVANCED")).addDraw("USPAFFOURWAYVFSFSADVANCED", new Draw4WayVFS("USPA 4 Way FS 'Advanced'", 3, 8, new int[]{4,5,6,10,11,17}, new String[]{"D","F","G","H","I","M"}));

        Discipline USPAEIGHTWAYFSDiscipline = USPAGovBody.addDiscipline("EIGHTWAYFS", new Discipline("EIGHTWAYFS"));
        USPAEIGHTWAYFSDiscipline.addCategory("OPEN", new Category("OPEN")).addDraw("USPAFEIGHTWAYFSFSOPEN", new Draw8WayFS("USPA 8 Way FS 'Open'", 5, 10, new int[0], new String[0]));
        USPAEIGHTWAYFSDiscipline.addCategory("INTERMEDIATE", new Category("INTERMEDIATE")).addDraw("USPAFEIGHTWAYFSFSINTERMEDIATE", new Draw8WayFS("USPA 8 Way FS 'Intermediate'", 4, 10, new int[]{2,9,11,12,15,20,22}, new String[0]));
        this.govBodyHash.put("USPA", USPAGovBody);
    }

    public GovBody getGovBody(String ID)
    {
        return this.govBodyHash.get(ID);
    }

    public void empty()
    {
        for (String aGovBodyKey : govBodyHash.keySet())
        {
            govBodyHash.get(aGovBodyKey).empty();
        }
    }

}

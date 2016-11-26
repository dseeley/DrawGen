/*
 *  (c) Copyright 2016 Dougal Seeley.  All rights reserved.
 */
package DrawGen.Draw;

import DrawGen.DrawGen;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

/**
 * Definition of a formation.
 */
public class Formation
{
    public String imageString;
    public String index;
    public BufferedImage buffImg;

    public Formation(String inImageString, String inIndex)
    {
        this.imageString = inImageString;
        this.index = inIndex;
        this.buffImg = getBufferedImg();    //Cache the loaded image.  Doing this is time-consuming, so only do it once, at startup.  Not too expensive on memory.
    }

    private BufferedImage getBufferedImg()
    {
        BufferedImage CurImg = null;
        URL ImgUrl = DrawGen.class.getResource("/DrawGen/resources/formations/" + this.imageString);
        if (ImgUrl != null)
        {
            try
            {
                CurImg = ImageIO.read(ImgUrl);
            }
            catch (IOException e)
            {
                System.out.println("ImageIO.read: IOException: " + e.getMessage());
            }
        }
        return CurImg;
    }

    static public cRandom[] FOURWAYFSRANDOMS =
    {
        new cRandom("A", "Unipod", "4_a.png"),
        new cRandom("B", "Stairstep Diamond", "4_b.png"),
        new cRandom("C", "Murphy Flake", "4_c.png"),
        new cRandom("D", "Yuan", "4_d.png"),
        new cRandom("E", "Meeker", "4_e.png"),
        new cRandom("F", "Open Accordian", "4_f.png"),
        new cRandom("G", "Cataccord", "4_g.png"),
        new cRandom("H", "Bow", "4_h.png"),
        new cRandom("J", "Donut", "4_j.png"),
        new cRandom("K", "Hook", "4_k.png"),
        new cRandom("L", "Adder", "4_l.png"),
        new cRandom("M", "Star", "4_m.png"),
        new cRandom("N", "Crank", "4_n.png"),
        new cRandom("O", "Satellite", "4_o.png"),
        new cRandom("P", "Sidebody", "4_p.png"),
        new cRandom("Q", "Phalanx", "4_q.png")
    };
    static public cRandom[] EIGHTWAYFSRANDOMS =
    {
        new cRandom("A", "Caterpillar", "8_a.png"),
        new cRandom("B", "Stairstep", "8_b.png"),
        new cRandom("C", "Hour Glass", "8_c.png"),
        new cRandom("D", "Hope Diamond", "8_d.png"),
        new cRandom("E", "Rubik", "8_e.png"),
        new cRandom("F", "Diamond Flake", "8_f.png"),
        new cRandom("G", "Arrowhead", "8_g.png"),
        new cRandom("H", "Iriquois", "8_h.png"),
        new cRandom("J", "Springbok", "8_j.png"),
        new cRandom("K", "Crossbow", "8_k.png"),
        new cRandom("L", "Open Facing Diamond", "8_l.png"),
        new cRandom("M", "Double Spiders", "8_m.png"),
        new cRandom("N", "Zipper Flake", "8_n.png"),
        new cRandom("O", "Compressed Accordian", "8_o.png"),
        new cRandom("P", "Venus", "8_p.png"),
        new cRandom("Q", "Compass", "8_q.png")
    };
    static public cRandom[] FOURWAYVFSRANDOMS =
    {
        new cRandom("A", "Cross", "4vfs_a.png"),
        new cRandom("B", "Gulley", "4vfs_b.png"),
        new cRandom("C", "Shoeshine / Foot Fetish", "4vfs_c.png"),
        new cRandom("D", "Sitline", "4vfs_d.png"),
        new cRandom("E", "Wave", "4vfs_e.png"),
        new cRandom("F", "Double Joker", "4vfs_f.png"),
        new cRandom("G", "Mixed Star", "4vfs_g.png"),
        new cRandom("H", "T-Bird", "4vfs_h.png"),
        new cRandom("I", "Trident", "4vfs_i.png"),
        new cRandom("J", "Flock", "4vfs_j.png"),
        new cRandom("K", "Anchor", "4vfs_k.png"),
        new cRandom("L", "Rebel", "4vfs_l.png"),
        new cRandom("M", "Dentri", "4vfs_m.png"),
        new cRandom("N", "Double Rebel", "4vfs_n.png")
    };
    static public cBlock[] FOURWAYFSBLOCKS =
    {
        new cBlock("1", "Snowflake", "Snowflake", "4_1.png"),
        new cBlock("2", "Sidebody Donut", "Sideflake Donut", "4_2.png"),
        new cBlock("3", "Side flake opal", "Turf", "4_3.png"),
        new cBlock("4", "Monopod", "Monopod", "4_4.png"),
        new cBlock("5", "Opal", "Opal", "4_5.png"),
        new cBlock("6", "Stardian", "Stardian", "4_6.png"),
        new cBlock("7", "Sidebuddies", "Sidebuddies", "4_7.png"),
        new cBlock("8", "Canadian Tee", "Canadian Tee", "4_8.png"),
        new cBlock("9", "Cat+Accordian", "Cat+Accordian", "4_9.png"),
        new cBlock("10", "Diamond", "Bunyip", "4_10.png"),
        new cBlock("11", "Photon", "Photon", "4_11.png"),
        new cBlock("12", "Bundy", "Bundy", "4_12.png"),
        new cBlock("13", "Offset", "Spinner", "4_13.png"),
        new cBlock("14", "Bipole", "Bipole", "4_14.png"),
        new cBlock("15", "Caterpillar", "Caterpillar", "4_15.png"),
        new cBlock("16", "Compressed Accordian", "Box", "4_16.png"),
        new cBlock("17", "Danish Tee", "Murphy", "4_17.png"),
        new cBlock("18", "Zircon", "Zircon", "4_18.png"),
        new cBlock("19", "Ritz", "Icepick", "4_19.png"),
        new cBlock("20", "Piver", "Viper", "4_20.png"),
        new cBlock("21", "ZigZag", "Marquis", "4_21.png"),
        new cBlock("22", "Tee", "Chinese Tee", "4_22.png")
    };
    static public cBlock[] EIGHTWAYFSBLOCKS =
    {
        new cBlock("1", "Donut Flake", "Donut Flake", "8_1.png"),
        new cBlock("2", "Swiss Bear", "Swiss Bear", "8_2.png"),
        new cBlock("3", "Double Chinese Tees", "Double Donuts", "8_3.png"),
        new cBlock("4", "Snowflake", "In-out", "8_4.png"),
        new cBlock("5", "Opposed Crank", "Opposed Crank", "8_5.png"),
        new cBlock("6", "Star", "Star", "8_6.png"),
        new cBlock("7", "Nacho", "Nacho", "8_7.png"),
        new cBlock("8", "Frisbee", "Frisbee", "8_8.png"),
        new cBlock("9", "Taj", "Mahal", "8_9.png"),
        new cBlock("10", "Donut", "Donut", "8_10.png"),
        new cBlock("11", "Norwegian Box", "Norwegian Donut", "8_11.png"),
        new cBlock("12", "Stereo Bipole", "Stereo Bipole", "8_12.png"),
        new cBlock("13", "Double Satellite", "Double Satellite", "8_13.png"),
        new cBlock("14", "Accordian", "Opposed Stairsteps", "8_14.png"),
        new cBlock("15", "Opal & Zipper", "Zipper & Opal", "8_15.png"),
        new cBlock("16", "Canadian Tees", "Monopods", "8_16.png"),
        new cBlock("17", "Buzzard", "Buzzard", "8_17.png"),
        new cBlock("18", "Sidebody Donut", "Sidebody Donut", "8_18.png"),
        new cBlock("19", "Compressed Diamonds", "Compressed Diamonds", "8_19.png"),
        new cBlock("20", "Cat Diamond", "Cat Accordion", "8_20.png"),
        new cBlock("21", "Stereopod", "Stereopod", "8_21.png"),
        new cBlock("22", "Old Bone", "Compressed Stairstep Diamonds", "8_22.png")
    };
    static public cBlock[] FOURWAYVFSBLOCKS =
    {
        new cBlock("1", "Arrowhead", "Arrowhead", "4vfs_1.png"),
        new cBlock("2", "Claw", "Claw", "4vfs_2.png"),
        new cBlock("3", "HD Accordian", "HD Accordian", "4vfs_3.png"),
        new cBlock("4", "Chain Gang", "Chain Gang", "4vfs_4.png"),
        new cBlock("5", "Mixed Accordian", "Mixed Accordian", "4vfs_5.png"),
        new cBlock("6", "Snowflake", "End of Snowflake", "4vfs_6.png"),
        new cBlock("7", "Flower", "Flower", "4vfs_7.png"),
        new cBlock("8", "Buddy", "Buddy", "4vfs_8.png"),
        new cBlock("9", "Shorty", "Shorty", "4vfs_9.png"),
        new cBlock("10", "Bill", "Bill", "4vfs_10.png"),
        new cBlock("11", "Fun Buddies", "Fun Buddies", "4vfs_11.png"),
        new cBlock("12", "Pinwheel", "Pinwheel", "4vfs_12.png"),
        new cBlock("13", "HD Star", "HD Star", "4vfs_13.png"),
        new cBlock("14", "Satellite", "Satellite", "4vfs_14.png"),
        new cBlock("15", "Bipole", "Bolepi", "4vfs_15.png"),
        new cBlock("16", "Chimmy", "Chimmy", "4vfs_16.png"),
        new cBlock("17", "Zin", "Zan", "4vfs_17.png"),
    };

    /*
     * ########################
     */
    static public class cBlock extends Formation
    {
        public String Name1;
        public String Name2;

        cBlock(String inIndex, String inName1, String inName2, String inImageString)
        {
            super(inImageString, inIndex);
            this.Name1 = inName1;
            this.Name2 = inName2;
        }
    }

    /*
     * ########################
     */
    static public class cRandom extends Formation
    {
        public String Name;

        cRandom(String inIndex, String inName, String inImageString)
        {
            super(inImageString, inIndex);
            this.Name = inName;
        }
    }
}

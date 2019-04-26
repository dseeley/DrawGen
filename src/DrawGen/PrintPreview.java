/*
 *  (c) Copyright 2016 Dougal Seeley.  All rights reserved.
 */
package DrawGen;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import javax.print.DocFlavor;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.*;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 */
public class PrintPreview extends javax.swing.JDialog
{
    public static final long serialVersionUID = 1L;
    private DrawGen pDrawGenView = null;
    private Printable printTarget = null;
    private int scalePercent = 100;

    /**
     * Creates new form NewJDialog
     */
    public PrintPreview(java.awt.Frame parent, boolean modal, DrawGen inDrawGenView, Printable inPrintTarget)
    {
        super(parent, modal);
        this.pDrawGenView = inDrawGenView;
        this.printTarget = inPrintTarget;
        initComponents();

        Image iconImg = Toolkit.getDefaultToolkit().getImage(DrawGen.class.getResource("/DrawGen/resources/DrawGen_AppIcon.png"));
        this.setIconImage(iconImg);

        this.DoPrintPreview();
    }

    
    private void DoPrintPreview()
    {
        if (this.pDrawGenView.getMyPrintService() == null)
        {
            PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
            PrintService[] PrtServices = PrintServiceLookup.lookupPrintServices(DocFlavor.SERVICE_FORMATTED.PRINTABLE, aset);
            PrintService SelectedPrtService = (PrintService) JOptionPane.showInputDialog(
                    this,
                    "No default printer is set. Print Preview needs a printer in order to set the page dimensions",
                    "Select default printer",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    PrtServices,
                    null);

            if (SelectedPrtService != null)
            {
                this.pDrawGenView.setMyPrintService(SelectedPrtService);
                this.pDrawGenView.configDefaultPrintServices();
            }
        }

        if (this.pDrawGenView.getMyPrintService() != null)
        {
            PageFormat myPageFormat = new PageFormat();
            float PaperWidthPts = MediaSize.getMediaSizeForName((MediaSizeName) this.pDrawGenView.getPrintattrs().get(Media.class)).getX(MediaSize.INCH) * 72;
            float PaperHeightPts = MediaSize.getMediaSizeForName((MediaSizeName) this.pDrawGenView.getPrintattrs().get(Media.class)).getY(MediaSize.INCH) * 72;

            MediaPrintableArea myPrintArea = (MediaPrintableArea) this.pDrawGenView.getPrintattrs().get(MediaPrintableArea.class);

            Paper myPaper = new Paper();
            myPaper.setSize(PaperWidthPts, PaperHeightPts);
            myPaper.setImageableArea(myPrintArea.getX(MediaPrintableArea.INCH) * 72, myPrintArea.getY(MediaPrintableArea.INCH) * 72, myPrintArea.getWidth(MediaPrintableArea.INCH) * 72, myPrintArea.getHeight(MediaPrintableArea.INCH) * 72);

            myPageFormat.setPaper(myPaper);

            OrientationRequested myOrientationRequested = (OrientationRequested) this.pDrawGenView.getPrintattrs().get(OrientationRequested.class);
            if (myOrientationRequested == OrientationRequested.PORTRAIT)
            {
                myPageFormat.setOrientation(PageFormat.PORTRAIT);
            }
            else
            {
                myPageFormat.setOrientation(PageFormat.LANDSCAPE);
            }

            int pageIndex = 0;
            while (true)
            {
//              double xPx2PtScaleFactor = this.pDrawGenView.getPx2PtScaleFactor().get("x");
                double xPx2PtScaleFactor = 2.0D;
                Float scale = (float) ((scalePercent / 100.0) * xPx2PtScaleFactor);
                BufferedImage img = new BufferedImage((int) (myPageFormat.getWidth() * scale), (int) (myPageFormat.getHeight() * scale), BufferedImage.TYPE_INT_RGB);
                Graphics2D g2d = img.createGraphics();
//                g2d.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON));
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
                g2d.scale(scale, scale);

                /*Fill in the background with white paint.*/
                g2d.setColor(java.awt.Color.WHITE);
                g2d.fillRect(0, 0, (int) (myPageFormat.getWidth()), (int) (myPageFormat.getHeight()));
                g2d.setColor(java.awt.Color.BLACK);

                try
                {
                    if (this.printTarget.print(g2d, myPageFormat, pageIndex) != Printable.PAGE_EXISTS)
                    {
                        break;
                    }
                    else
                    {
                        pageIndex++;
                    }
                }
                catch (Exception ex)
                {
                    System.out.println("ex");
                }

                this.jPanelPrintPreview.add(new JLabel(new ImageIcon(img)));
            }
        }
    }

    /** This method is called from within the constructor to
     * initialise the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jToolBar1 = new javax.swing.JToolBar();
        jButtonPrint = new javax.swing.JButton();
        jButtonPageSetup = new javax.swing.JButton();
        jLabelZoom = new javax.swing.JLabel();
        jComboBoxZoom = new javax.swing.JComboBox();
        jScrollPanePrintPreview = new javax.swing.JScrollPane();
        jPanelPrintPreview = new cjPanelPrintPreviewOutput();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("DrawGen - Print Preview");

        jToolBar1.setRollover(true);

        jButtonPrint.setText("Print");
        jButtonPrint.setFocusable(false);
        jButtonPrint.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonPrint.setPreferredSize(new java.awt.Dimension(60, 27));
        jButtonPrint.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonPrint.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButtonPrintActionPerformed(evt);
            }
        });
        jToolBar1.add(jButtonPrint);

        jButtonPageSetup.setText("Page Setup");
        jButtonPageSetup.setFocusable(false);
        jButtonPageSetup.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonPageSetup.setPreferredSize(new java.awt.Dimension(100, 27));
        jButtonPageSetup.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonPageSetup.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jButtonPageSetupActionPerformed(evt);
            }
        });
        jToolBar1.add(jButtonPageSetup);

        jLabelZoom.setText("Zoom");
        jLabelZoom.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 3));
        jToolBar1.add(jLabelZoom);

        jComboBoxZoom.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "200%", "150%", "100%", "80%", "50%" }));
        jComboBoxZoom.setSelectedIndex(2);
        jComboBoxZoom.setBorder(null);
        jComboBoxZoom.setPreferredSize(new java.awt.Dimension(83, 26));
        jComboBoxZoom.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jComboBoxZoomActionPerformed(evt);
            }
        });
        jToolBar1.add(jComboBoxZoom);

        jScrollPanePrintPreview.setViewportView(jPanelPrintPreview);
        jScrollPanePrintPreview.getVerticalScrollBar().setUnitIncrement(25);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 462, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPanePrintPreview, javax.swing.GroupLayout.PREFERRED_SIZE, 724, Short.MAX_VALUE)
                    .addGap(14, 14, 14)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 565, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(40, 40, 40)
                    .addComponent(jScrollPanePrintPreview, javax.swing.GroupLayout.PREFERRED_SIZE, 544, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonPageSetupActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonPageSetupActionPerformed
    {//GEN-HEADEREND:event_jButtonPageSetupActionPerformed
        this.pDrawGenView.callPageSetupAction();

        this.jPanelPrintPreview.removeAll();
        this.DoPrintPreview();
        this.jPanelPrintPreview.repaint();
        this.validate();
    }//GEN-LAST:event_jButtonPageSetupActionPerformed

    private void jButtonPrintActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButtonPrintActionPerformed
    {//GEN-HEADEREND:event_jButtonPrintActionPerformed
        this.pDrawGenView.callPrintAction();
    }//GEN-LAST:event_jButtonPrintActionPerformed

    private void jComboBoxZoomActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jComboBoxZoomActionPerformed
    {//GEN-HEADEREND:event_jComboBoxZoomActionPerformed
        javax.swing.JComboBox cb = (javax.swing.JComboBox) evt.getSource();
        this.scalePercent = Integer.parseInt(cb.getSelectedItem().toString().substring(0, cb.getSelectedItem().toString().length() - 1));;
        this.jPanelPrintPreview.removeAll();
        this.DoPrintPreview();
        this.jPanelPrintPreview.repaint();
        this.validate();
    }//GEN-LAST:event_jComboBoxZoomActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonPageSetup;
    private javax.swing.JButton jButtonPrint;
    private javax.swing.JComboBox jComboBoxZoom;
    private javax.swing.JLabel jLabelZoom;
    private javax.swing.JPanel jPanelPrintPreview;
    private javax.swing.JScrollPane jScrollPanePrintPreview;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables


    /**
     * This is used instead of the default
     */
    private class cjPanelPrintPreviewOutput extends javax.swing.JPanel
    {
//        @Override
//        public void paintComponent(Graphics graphics)
//        {
//            Graphics2D g2d = (Graphics2D) graphics;
//            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
//            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
//            g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
//            g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
//            super.paintComponent(g2d);
//        }
    }
}

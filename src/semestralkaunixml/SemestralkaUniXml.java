/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semestralkaunixml;

import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.util.regex.Pattern;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class SemestralkaUniXml extends javax.swing.JFrame {
    
    private String encoding;
    private String xmlVersion;
    private String standalone;
    private boolean xmlLoaded = false;
    Document savedDocument; 
    StringTree xmlList;
    private String aXmlFilePath = "main.xml";
    Pattern INVALID_XML_CHARS = Pattern.compile("[^\\u0009\\u000A\\u000D\\u0020-\\uD7FF\\uE000-\\uFFFD\uD800\uDC00-\uDBFF\uDFFF]");
    
    public SemestralkaUniXml() {
        initComponents();
        this.setDefaultCloseOperation(this.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent we){ 
              String ObjButtons[] = {"No","Yes"};
              int PromptResult = JOptionPane.showOptionDialog(null, 
                  "Are you sure you want to exit? Don't forget to save your work !", "", 
                  JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, 
                  ObjButtons,ObjButtons[0]);
              
              if(PromptResult==1){
                  System.out.println(PromptResult);
                System.exit(0);          
              }
            }
        });
        
        deleteNodeButton.setMnemonic(KeyEvent.VK_D);
        addChildButton.setMnemonic(KeyEvent.VK_E);
        if(!parseFileToXmlTree(aXmlFilePath)){
            menuItemViewXmlInfo.setEnabled(false);
        } else {
            xmlLoaded = true;
            menuItemViewXmlInfo.setEnabled(true);
        }
        nameText.setEnabled(false);
    }

    private boolean parseFileToXmlTree(String xmlFilePath){
        try{
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(xmlFilePath));
            Node objects = doc.getDocumentElement();
            
            doc.normalize();
            encoding = doc.getInputEncoding();
            xmlVersion = doc.getXmlVersion();
            if(doc.getXmlStandalone()){
                standalone = "yes";
            }else{
                standalone = "no";
            }
            
            DefaultMutableTreeNode root = new DefaultMutableTreeNode("");
            
            xmlList  = new StringTree(doc.getDocumentElement().getNodeName());
            
            searchNode(objects, xmlList);
            
            //testVypis(xmlList);
            
            setTreeModel(xmlList,root);
            
            DefaultTreeModel model = new DefaultTreeModel(root);
            treeXml.setModel(model);
            return true;
        } catch(ParserConfigurationException | SAXException | IOException e){
            System.out.println(e);
            DefaultMutableTreeNode root = new DefaultMutableTreeNode("");
            DefaultTreeModel model = new DefaultTreeModel(root);
            treeXml.setModel(model);
            return false;
        }
    }
    
    private void testVypis(StringTree xmlList){
        if(xmlList.isValue){
            System.out.println("VALUE " + xmlList.name);
        }else{
            System.out.println("NODE " + xmlList.name);
        }
        for(int i = 0; i < xmlList.getStringList().size(); i++){
            testVypis(xmlList.getStringList().get(i));
        }
    }
    
    private void setTreeModel(StringTree xmlList, DefaultMutableTreeNode newRoot){
        DefaultMutableTreeNode tempRoot = new DefaultMutableTreeNode(xmlList.getName());
        newRoot.add(tempRoot);
        for(int i = 0; i < xmlList.getStringList().size(); i++){
            setTreeModel(xmlList.getStringList().get(i),tempRoot);
        }
    }
    
    private void searchNode(Node object, StringTree xmlList){
        NodeList nodes = object.getChildNodes();
        for(int i = 0; i < nodes.getLength(); i++){
            if (nodes.item(i) instanceof Element) {
                StringTree newXmlList  = new StringTree(nodes.item(i).getNodeName());
                xmlList.getStringList().add(newXmlList);
                String trimStrin = nodes.item(i).getTextContent().split(" ")[0].trim();
                if(!trimStrin.split("   ")[0].equals("")){
                   StringTree child = new StringTree(nodes.item(i).getTextContent().split("   ")[0]);
                   child.setIsValue(true);
                   newXmlList.getStringList().add(child);
                }
                
                if(nodes.item(i).hasChildNodes()){
                   searchNode(nodes.item(i),newXmlList);
                }
            }
            
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        treeXml = new javax.swing.JTree();
        jLabel1 = new javax.swing.JLabel();
        nameText = new javax.swing.JTextField();
        addChildButton = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        addSiblingButton = new javax.swing.JButton();
        addSiblingCopy = new javax.swing.JButton();
        deleteNodeButton = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuFile = new javax.swing.JMenu();
        menuItemNewXml = new javax.swing.JMenuItem();
        menuItemOpenXml = new javax.swing.JMenuItem();
        menuItemSaveXml = new javax.swing.JMenuItem();
        menuItemSaveAsXml = new javax.swing.JMenuItem();
        menuItemViewXmlInfo = new javax.swing.JMenuItem();
        menuHelp = new javax.swing.JMenu();
        menuItemAbout = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("root");
        javax.swing.tree.DefaultMutableTreeNode treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Companies");
        javax.swing.tree.DefaultMutableTreeNode treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Company");
        javax.swing.tree.DefaultMutableTreeNode treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("One");
        treeNode3.add(treeNode4);
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Two");
        treeNode3.add(treeNode4);
        treeNode4 = new javax.swing.tree.DefaultMutableTreeNode("Three");
        treeNode3.add(treeNode4);
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("People");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("One");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Two");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeXml.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        treeXml.setAutoscrolls(true);
        treeXml.setScrollsOnExpand(true);
        treeXml.setVerifyInputWhenFocusTarget(false);
        treeXml.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                treeXmlMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(treeXml);

        jLabel1.setFont(new java.awt.Font("Cantarell", 0, 18)); // NOI18N
        jLabel1.setText("Edit attribute: ");

        nameText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nameTextKeyReleased(evt);
            }
        });

        addChildButton.setText("Add Child");
        addChildButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addChildButtonActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Cantarell", 0, 18)); // NOI18N
        jLabel4.setText("Name");

        addSiblingButton.setText("Add Sibling");
        addSiblingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addSiblingButtonActionPerformed(evt);
            }
        });

        addSiblingCopy.setText("Add Sibling Copy");
        addSiblingCopy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addSiblingCopyActionPerformed(evt);
            }
        });

        deleteNodeButton.setText("Delete Node");
        deleteNodeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteNodeButtonActionPerformed(evt);
            }
        });

        menuFile.setText("File");

        menuItemNewXml.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        menuItemNewXml.setText("New Xml");
        menuItemNewXml.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemNewXmlActionPerformed(evt);
            }
        });
        menuFile.add(menuItemNewXml);

        menuItemOpenXml.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        menuItemOpenXml.setText("Open Xml");
        menuItemOpenXml.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemOpenXmlActionPerformed(evt);
            }
        });
        menuFile.add(menuItemOpenXml);

        menuItemSaveXml.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        menuItemSaveXml.setText("Save Xml");
        menuItemSaveXml.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemSaveXmlActionPerformed(evt);
            }
        });
        menuFile.add(menuItemSaveXml);

        menuItemSaveAsXml.setText("Save Xml as");
        menuItemSaveAsXml.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemSaveAsXmlActionPerformed(evt);
            }
        });
        menuFile.add(menuItemSaveAsXml);

        menuItemViewXmlInfo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
        menuItemViewXmlInfo.setText("View/Edit XML Info");
        menuItemViewXmlInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemViewXmlInfoActionPerformed(evt);
            }
        });
        menuFile.add(menuItemViewXmlInfo);

        jMenuBar1.add(menuFile);

        menuHelp.setText("Help");

        menuItemAbout.setText("About");
        menuHelp.add(menuItemAbout);

        jMenuBar1.add(menuHelp);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLabel4))
                        .addComponent(nameText))
                    .addComponent(addSiblingCopy)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(addChildButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addSiblingButton))
                    .addComponent(deleteNodeButton))
                .addGap(136, 136, 136))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nameText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(addChildButton)
                            .addComponent(addSiblingButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addSiblingCopy)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteNodeButton))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 456, Short.MAX_VALUE))
                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuItemNewXmlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemNewXmlActionPerformed
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("");
        DefaultTreeModel model = new DefaultTreeModel(root);
        treeXml.setModel(model);
    }//GEN-LAST:event_menuItemNewXmlActionPerformed

    private void menuItemViewXmlInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemViewXmlInfoActionPerformed
        XmlInformationPanel viewXmlInfoPanel = new XmlInformationPanel(xmlVersion,encoding,standalone);
            int reply = JOptionPane.showConfirmDialog(this, viewXmlInfoPanel, "Xml File Information", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if(reply == JOptionPane.OK_OPTION){
                
            }else{
                
            }
    }//GEN-LAST:event_menuItemViewXmlInfoActionPerformed

    private void menuItemOpenXmlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemOpenXmlActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("XML File", "xml");
        fileChooser.setFileFilter(filter);
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            aXmlFilePath = fileChooser.getSelectedFile().getAbsolutePath();
            parseFileToXmlTree(aXmlFilePath);
            if(!xmlLoaded){
                menuItemViewXmlInfo.setEnabled(xmlLoaded);
            }
        }
    }//GEN-LAST:event_menuItemOpenXmlActionPerformed

    private void treeXmlMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_treeXmlMouseClicked
        if(!treeXml.isSelectionEmpty()){
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) treeXml.getLastSelectedPathComponent();
            if(!node.isRoot()){
                nameText.setEnabled(true);
            }else{
                nameText.setEnabled(false);
            }
            if(node.isLeaf()){
                jLabel4.setText("Attribute");
            }else{
                jLabel4.setText("Name");
            }
            nameText.setText(node.toString());
        }
    }//GEN-LAST:event_treeXmlMouseClicked

    private void nameTextKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nameTextKeyReleased
         if(!treeXml.isSelectionEmpty()){
             DefaultMutableTreeNode node = (DefaultMutableTreeNode) treeXml.getLastSelectedPathComponent();
             node.setUserObject(nameText.getText());
             treeXml.updateUI();
         }
    }//GEN-LAST:event_nameTextKeyReleased

    
   private Element createXmlFile(TreeModel model, Object node,Element elem) throws Exception {
    try{
       if(model.getChildCount(node) == 0){
           elem.setTextContent(node.toString());
           return elem;
    }else{
        Element el = savedDocument.createElement(node.toString());
        for(int i=0;i<model.getChildCount(node);i++){
            Object child = model.getChild(node, i);
            if(((DefaultMutableTreeNode) child).getChildCount() == 0){
                createXmlFile(model,child,el);
            }else{
                Element tempElem = createXmlFile(model,child,el);
                el.appendChild(tempElem);
            }
        }
        return el;
    }
    }catch(DOMException e){
        throw  new Exception("chyba");
    }
   }
    
    private void menuItemSaveXmlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemSaveXmlActionPerformed
        DefaultMutableTreeNode root = ((DefaultMutableTreeNode) treeXml.getModel().getRoot()).getNextNode();
        try {

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            savedDocument = docBuilder.newDocument();
            Element rootElement = createXmlFile(treeXml.getModel(), root,null);
            savedDocument.appendChild(rootElement);
            
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            DOMSource source = new DOMSource(savedDocument);
            StreamResult result = new StreamResult(new File(aXmlFilePath));

            transformer.transform(source, result);

            JOptionPane.showMessageDialog(this, "File successfully saved !");

      } catch (ParserConfigurationException | TransformerException pce) {
      } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "File coludn't be saved beacause you used invalid XML characters in node name");
        }
        
    }//GEN-LAST:event_menuItemSaveXmlActionPerformed

    private void addChildButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addChildButtonActionPerformed
         if(!treeXml.isSelectionEmpty()){
             DefaultMutableTreeNode node = (DefaultMutableTreeNode) treeXml.getLastSelectedPathComponent();
             node.add(new DefaultMutableTreeNode("New_Child"));
             treeXml.updateUI();
         }
    }//GEN-LAST:event_addChildButtonActionPerformed

    private void addSiblingButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addSiblingButtonActionPerformed
        if(!treeXml.isSelectionEmpty()){
             DefaultMutableTreeNode node = (DefaultMutableTreeNode) treeXml.getLastSelectedPathComponent();
             if(!node.isRoot()){
                ((DefaultMutableTreeNode)node.getParent()).add(new DefaultMutableTreeNode("New_Sibling"));
                treeXml.updateUI();
             }
            
         }
    }//GEN-LAST:event_addSiblingButtonActionPerformed

    private void addSiblingCopyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addSiblingCopyActionPerformed
        if(!treeXml.isSelectionEmpty()){
             DefaultMutableTreeNode node = (DefaultMutableTreeNode) treeXml.getLastSelectedPathComponent();
             if(!node.isRoot()){
                ((DefaultMutableTreeNode)node.getParent()).add(new DefaultMutableTreeNode(node.toString()));
                treeXml.updateUI();
             }
            
         }
    }//GEN-LAST:event_addSiblingCopyActionPerformed

    private void menuItemSaveAsXmlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemSaveAsXmlActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogType(JFileChooser.SAVE_DIALOG);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("XML File", "xml");
        fileChooser.setFileFilter(filter);
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int resultt = fileChooser.showSaveDialog(this);
        if (resultt == JFileChooser.APPROVE_OPTION) {
            aXmlFilePath = fileChooser.getSelectedFile().getAbsolutePath();
            DefaultMutableTreeNode root = ((DefaultMutableTreeNode) treeXml.getModel().getRoot()).getNextNode();
            try {

                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

                savedDocument = docBuilder.newDocument();
                Element rootElement = createXmlFile(treeXml.getModel(), root,null);
                savedDocument.appendChild(rootElement);

                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
                DOMSource source = new DOMSource(savedDocument);
                StreamResult result;
                if(aXmlFilePath.contains(".xml")){
                   result = new StreamResult(new File(aXmlFilePath));
                }else{
                   result = new StreamResult(new File(aXmlFilePath+".xml"));
                }
                

                transformer.transform(source, result);

                JOptionPane.showMessageDialog(this, "File successfully saved !");

          } catch (ParserConfigurationException | TransformerException pce) {
          } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "File coludn't be saved beacause you used invalid XML characters in node name");
            }
        }
    }//GEN-LAST:event_menuItemSaveAsXmlActionPerformed

    private void deleteNodeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteNodeButtonActionPerformed
        if(!treeXml.isSelectionEmpty()){
             DefaultMutableTreeNode node = (DefaultMutableTreeNode) treeXml.getLastSelectedPathComponent();
             node.removeAllChildren();
             node.removeFromParent();
             treeXml.updateUI();
         }
    }//GEN-LAST:event_deleteNodeButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SemestralkaUniXml.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SemestralkaUniXml.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SemestralkaUniXml.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SemestralkaUniXml.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SemestralkaUniXml().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addChildButton;
    private javax.swing.JButton addSiblingButton;
    private javax.swing.JButton addSiblingCopy;
    private javax.swing.JButton deleteNodeButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenu menuFile;
    private javax.swing.JMenu menuHelp;
    private javax.swing.JMenuItem menuItemAbout;
    private javax.swing.JMenuItem menuItemNewXml;
    private javax.swing.JMenuItem menuItemOpenXml;
    private javax.swing.JMenuItem menuItemSaveAsXml;
    private javax.swing.JMenuItem menuItemSaveXml;
    private javax.swing.JMenuItem menuItemViewXmlInfo;
    private javax.swing.JTextField nameText;
    private javax.swing.JTree treeXml;
    // End of variables declaration//GEN-END:variables
}
